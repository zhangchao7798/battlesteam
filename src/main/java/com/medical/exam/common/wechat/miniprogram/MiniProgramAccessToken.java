/**
 * Copyright (C), 2015-2019
 * FileName: MiniProgramAccessToken
 * Author:   Wu
 * Date:     2019/7/4 21:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.common.wechat.miniprogram;

import com.alibaba.fastjson.JSONObject;
import com.medical.exam.common.wechat.AbstractAccessToken;
import com.medical.exam.util.RestTemplateUtils;
import com.medical.exam.util.SpringUtil;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.medical.exam.common.constant.SystemConstant.APPID;
import static com.medical.exam.common.constant.SystemConstant.SECRET;

/**
 * <br>
 * 〈〉
 *
 * @author Wu
 * @create 2019/7/4
 * @since 1.0.0
 */
public class MiniProgramAccessToken extends AbstractAccessToken {
    private static final String ACCESS_TOKEN_KEY = "accessToken";
    private static final long ACCESS_TOKEN_TIMEOUT_OFFSET = 60 * 10;
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private static volatile MiniProgramAccessToken instance;

    public static MiniProgramAccessToken getInstance() {
        if (null == instance) {
            synchronized (MiniProgramAccessToken.class) {
                if (null == instance) {
                    instance = new MiniProgramAccessToken();
                }
            }

        }
        return instance;
    }


    private MiniProgramAccessToken() {
        super(APPID, SECRET);
    }

    @Override
    protected String doGetAccessToken() {
        String accessToken = null;
        READ_WRITE_LOCK.readLock().lock();
        try {
            StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
            if (null == stringRedisTemplate) {
                throw new IllegalStateException("Can't get the bean of StringRedisTemplate.");
            }
            accessToken = stringRedisTemplate.opsForValue().get(ACCESS_TOKEN_KEY);
            if (null == accessToken) {
                READ_WRITE_LOCK.readLock().unlock();
                READ_WRITE_LOCK.writeLock().lock();
                try {
                    accessToken = stringRedisTemplate.opsForValue().get(ACCESS_TOKEN_KEY);
                    if (null == accessToken) {
                        String reqUrl = MessageFormat.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}", appid, secret);
                        JSONObject returnInfo = RestTemplateUtils.getInstance().getForObject(reqUrl, JSONObject.class);
                        if (null != returnInfo) {
                            if (Objects.isNull(returnInfo.get("expires_in"))) {
                                throw new IllegalStateException(returnInfo.toString());
                            }
                            accessToken = returnInfo.getString("access_token");
                            stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_KEY, accessToken, returnInfo.getLongValue("expires_in") - ACCESS_TOKEN_TIMEOUT_OFFSET, TimeUnit.SECONDS);

                        }
                    }

                } finally {
                    READ_WRITE_LOCK.readLock().lock();
                    READ_WRITE_LOCK.writeLock().unlock();
                }
            }

        } finally {
            READ_WRITE_LOCK.readLock().unlock();
        }
        return accessToken;
    }

}

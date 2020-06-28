package com.medical.exam.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.medical.exam.bean.po.User;
import com.medical.exam.bean.po.Wechat;
import com.medical.exam.common.annotation.logger.Logger;
import com.medical.exam.common.annotation.logger.LoggerHolder;
import com.medical.exam.common.exception.ServiceException;
import com.medical.exam.dao.UserMapper;
import com.medical.exam.dao.WechatMapper;
import com.medical.exam.service.WechatService;
import com.medical.exam.util.MD5Utils;
import com.medical.exam.util.MiniProgramUtils;
import com.medical.exam.util.WechatUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

import static com.medical.exam.common.constant.SystemConstant.DEFAULT_PASSWORD;


/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/02
 */
@Logger
@Service
public class WechatServiceImpl implements WechatService {
    private final WechatMapper wechatMapper;
    private final UserMapper userMapper;

    public WechatServiceImpl(WechatMapper wechatMapper, UserMapper userMapper) {
        this.wechatMapper = wechatMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Wechat loginMiniProgram(@NotNull String code, @NotNull String encryptedData, @NotNull String iv) {
        try {
            JSONObject result = WechatUtils.loginMiniProgram(code);
            LoggerHolder.getLogger().info("code:{},encryptedData:{},iv,{},req raw result:{}", code, encryptedData, iv, result);
            if (null != result) {
                String openId = result.getString("openid");
                String sessionKey = result.getString("session_key");
                result = MiniProgramUtils.decrypt(sessionKey, iv, encryptedData);
                LoggerHolder.getLogger().info("decrypt result:{}", result);
                if (null != result) {
                    String phone = result.getString("purePhoneNumber");
                    if (null != phone) {
                        //检查用户是否已经注册,如果没有注册则需要帮用户注册
                        User user = new User();
                        user.setPhone(phone);
                        User existUser = this.userMapper.selectOne(user);
                        if (null == existUser) {
                            doRegister(user);
                        } else {
                            user.setUserId(existUser.getUserId());
                        }
                        //更新或新增一条微信绑定信息
                        Wechat wechat = new Wechat();
                        wechat.setUserId(user.getUserId());
                        if (null == wechatMapper.selectOne(wechat)) {
                            wechat.setOpenId(openId);
                            wechatMapper.insertSelective(wechat);
                        } else {
                            wechat.setOpenId(openId);
                            wechatMapper.updateByPrimaryKeySelective(wechat);
                        }
                        return wechat;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            LoggerHolder.getLogger().error("loginMiniProgram:{}", e.getMessage(), e);
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public Wechat preLoginMiniProgram(@NotNull String code) {
        JSONObject result = WechatUtils.loginMiniProgram(code);
        if (null != result) {
            String openid = result.getString("openid");
            Wechat wechat = new Wechat();
            wechat.setOpenId(openid);
            return this.wechatMapper.selectOne(wechat);
        }
        return null;
    }

    private boolean doRegister(User user) {
        user.setPassword(MD5Utils.GetMD5(DEFAULT_PASSWORD));
        return this.userMapper.insertSelective(user) > 0;
    }

    @Override
    public Wechat getByUserId(Long userId) {
        return this.wechatMapper.selectByPrimaryKey(userId);
    }
}

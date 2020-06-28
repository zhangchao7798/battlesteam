/**
 * Copyright (C), 2015-2019
 * FileName: AbstractAccessToken
 * Author:   Wu
 * Date:     2019/7/4 21:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.common.wechat;

/**
 * <br>
 * 〈〉
 *
 * @author Wu
 * @create 2019/7/4
 * @since 1.0.0
 */
public abstract class AbstractAccessToken {
    protected String appid;
    protected String secret;

    public AbstractAccessToken(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    /**
     * get access token
     * @return
     */
    public String getAccessToken() {
        return doGetAccessToken();
    }

    /**
     * do get access token
     *
     * @return
     */
    protected abstract String doGetAccessToken();
}

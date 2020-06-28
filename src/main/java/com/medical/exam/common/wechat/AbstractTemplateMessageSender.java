/**
 * Copyright (C), 2015-2019
 * FileName: AbstractTemplateMessageSender
 * Author:   Wu
 * Date:     2019/7/4 21:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.common.wechat;

import java.util.concurrent.Callable;

/**
 * <br>
 * 〈〉
 *
 * @author Wu
 * @create 2019/7/4
 * @since 1.0.0
 */
public abstract class AbstractTemplateMessageSender implements Callable {
    protected AbstractAccessToken accessToken;
    protected AbstractTemplateMessage templateMessage;

    public AbstractTemplateMessageSender(AbstractAccessToken accessToken, AbstractTemplateMessage templateMessage) {
        this.accessToken = accessToken;
        this.templateMessage = templateMessage;
    }

    public String send() {
        return doSend(accessToken, templateMessage);
    }

    @Override
    public Object call() throws Exception {
        return doSend(accessToken, templateMessage);
    }

    /**
     * do send
     *
     * @param accessToken
     * @param templateMessage
     * @return
     */
    protected abstract String doSend(AbstractAccessToken accessToken, AbstractTemplateMessage templateMessage);
}

/**
 * Copyright (C), 2015-2019
 * FileName: MiniProgramTemplateMessageSender
 * Author:   Wu
 * Date:     2019/7/4 21:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.common.wechat.miniprogram;

import com.medical.exam.common.wechat.AbstractAccessToken;
import com.medical.exam.common.wechat.AbstractTemplateMessage;
import com.medical.exam.common.wechat.AbstractTemplateMessageSender;
import com.medical.exam.util.RestTemplateUtils;

import java.text.MessageFormat;
import java.util.concurrent.Callable;

/**
 * mini program template message sender
 *
 * @author Wu
 * @create 2019/7/4
 * @since 1.0.0
 */
public class MiniProgramTemplateMessageSender extends AbstractTemplateMessageSender{


    public MiniProgramTemplateMessageSender(AbstractAccessToken accessToken, AbstractTemplateMessage templateMessage) {
        super(accessToken, templateMessage);
    }

    @Override
    protected String doSend(AbstractAccessToken accessToken, AbstractTemplateMessage templateMessage) {
        String reqUrl = MessageFormat.format("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token={0}", accessToken.getAccessToken());
        return RestTemplateUtils.getInstance().postForObject(reqUrl, String.class, templateMessage.getParams());
    }
}

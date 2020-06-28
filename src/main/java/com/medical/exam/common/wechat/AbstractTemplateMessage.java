/**
 * Copyright (C), 2015-2019
 * FileName: AbstractTemplateMessage
 * Author:   Wu
 * Date:     2019/7/4 21:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.common.wechat;


import com.medical.exam.common.constant.SystemConstant;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTemplateMessage {

    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>(SystemConstant.INIT_SIZE);
        params.put("touser", getToUser());
        params.put("template_id", getTemplateId());
        params.put("form_id", getFormId());
        params.put("data", getData());
        return params;
    }

    /**
     * get template_id
     *
     * @return
     */
    protected abstract String getTemplateId();

    /**
     * get data
     *
     * @return
     */
    protected abstract Object getData();

    /**
     * get toUser
     *
     * @return
     */
    protected abstract String getToUser();

    /**
     * get formId
     *
     * @return
     */
    protected abstract String getFormId();
}

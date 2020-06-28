/**
 * Copyright (C), 2015-2019
 * FileName: MiniProgramTemplateMessage
 * Author:   Wu
 * Date:     2019/7/4 21:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.common.wechat.miniprogram;

import com.alibaba.fastjson.JSONObject;
import com.medical.exam.bean.po.Order;
import com.medical.exam.common.wechat.AbstractTemplateMessage;
import com.medical.exam.service.CommodityService;
import com.medical.exam.util.SpringUtil;

/**
 * <br>
 * 〈〉
 *
 * @author Wu
 * @create 2019/7/4
 * @since 1.0.0
 */
public class MiniProgramTemplateMessage extends AbstractTemplateMessage {
    private static final String TEMPLATE_ID = "lSueCxE-r76CC0s-xD8rT_MPTQ9uKxYRFxkpXMChTJI";

    private String openid;
    private Order order;

    public
    MiniProgramTemplateMessage(String openid, Order order) {
        this.openid = openid;
        this.order = order;
    }


    @Override
    public String getTemplateId() {
        return TEMPLATE_ID;
    }

    @Override
    public Object getData() {
        JSONObject data = new JSONObject();

        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", this.order.getCreateTime());

        CommodityService commodityService = SpringUtil.getBean(CommodityService.class);
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", commodityService.getCommodity(this.order.getCommodityId()).getTitle());

        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", this.order.getAmount().setScale(2).toString());

        JSONObject keyword4 = new JSONObject();
        keyword4.put("value", this.order.getOrderId());


        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("keyword4", keyword4);

        return data;
    }

    @Override
    public String getToUser() {
        return this.openid;
    }

    @Override
    public String getFormId() {
        return order.getPrepayId();
    }
}

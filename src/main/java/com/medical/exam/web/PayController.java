package com.medical.exam.web;

import com.medical.exam.bean.po.Order;
import com.medical.exam.bean.po.Wechat;
import com.medical.exam.common.annotation.logger.Logger;
import com.medical.exam.common.annotation.logger.LoggerHolder;
import com.medical.exam.dao.OrderMapper;
import com.medical.exam.dao.WechatMapper;
import com.medical.exam.service.OrderService;
import com.medical.exam.util.WechatUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/10
 */
@Logger
@RestController
@RequestMapping("/api/pay")
public class PayController extends BaseController {
    private final OrderService orderService;
    private final WechatMapper wechatMapper;
    private final OrderMapper orderMapper;

    public PayController(OrderService orderService, WechatMapper wechatMapper, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.wechatMapper = wechatMapper;
        this.orderMapper = orderMapper;
    }

    /**
     * callback
     * @param request
     * @return
     */
    @PostMapping(value = "/callback",produces = MediaType.TEXT_XML_VALUE)
    public String callback(HttpServletRequest request) {
        try {
            return orderService.callback(IOUtils.toString(request.getInputStream(), Charset.forName("UTF-8")));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("callback:{}", e.getMessage(), e);
            return null;
        }
    }

    @GetMapping(value = "/test",produces = MediaType.TEXT_XML_VALUE)
    public String test(String orderId,String userId) {
        try {
            Order order = new Order();
            order.setOrderId(orderId);
            order = orderMapper.selectOne(order);

            Wechat wechat = this.wechatMapper.selectByPrimaryKey(order.getUserId());
            //send template message
            WechatUtils.sendTemplateMessage(wechat.getOpenId(), order);

            return null;

        } catch (Exception e) {
            LoggerHolder.getLogger().error("callback:{}", e.getMessage(), e);
            return null;
        }
    }
}

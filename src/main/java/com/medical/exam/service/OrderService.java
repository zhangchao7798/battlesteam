package com.medical.exam.service;

import com.medical.exam.bean.po.Order;
import com.medical.exam.bean.vo.WxPayVo;
import com.medical.exam.common.page.PageInfo;
import com.medical.exam.common.state.OrderState;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
public interface OrderService {
    /**
     * get my order list
     *
     * @param userId
     * @return
     */
    List<?> getMyOrderList(Long userId);

    /**
     * send back
     *
     * @param orderId
     * @param courierNo
     * @return
     */
    Boolean sendBack(String orderId, String courierNo);

    /**
     * create order
     * @param commodityId
     * @param remark
     * @param quantity
     * @param addressee
     * @param phone
     * @param address
     * @param amount
     * @param userId
     * @return
     */
    Order createOrder(Long commodityId, String remark, Integer quantity, String addressee, String phone, String address, BigDecimal amount,Long userId);

    /**
     * pay order
     *
     * @param orderId
     * @param code
     * @return
     */
    WxPayVo payOrder(String orderId, String code) throws Exception;

    /**
     * callback
     *
     * @param notifyData
     * @return
     */
    String callback(String notifyData);

    /**
     * update order status
     *
     * @param orderId
     * @param orderState
     * @return
     */
    Boolean updateOrderStatus(String orderId, OrderState orderState);

    /**
     * find Staff Status by companyid
     * @param companyId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo findStaffStatusByCompanyId(Long companyId,Integer page, Integer pageSize);

    /**
     * get order page
     * @param page
     * @param pageSize
     * @param orderId
     * @param status
     * @return
     */
    PageInfo getOrderPage(Integer page, Integer pageSize, String orderId, Integer status);

    /**
     *
     * @param orderId
     * @return
     */
    Order getByOrderId(String orderId);

    /**
     * cancel order
     * @param orderId
     * @return
     */
    Boolean cancelOrder(String orderId);

    /**
     * modify order status
     * @param orderId
     * @param status
     * @return
     */
    Boolean modifyOrderStatus(String orderId, Integer status);
}

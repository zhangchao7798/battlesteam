package com.medical.exam.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.medical.exam.bean.MyOrderListVo;
import com.medical.exam.bean.po.Addressee;
import com.medical.exam.bean.po.Commodity;
import com.medical.exam.bean.po.Order;
import com.medical.exam.bean.po.Wechat;
import com.medical.exam.bean.vo.StaffStatusVo;
import com.medical.exam.bean.vo.WxPayVo;
import com.medical.exam.common.annotation.logger.LoggerHolder;
import com.medical.exam.common.constant.SystemConstant;
import com.medical.exam.common.exception.ServiceException;
import com.medical.exam.common.page.PageInfo;
import com.medical.exam.common.page.PageInfoFactory;
import com.medical.exam.common.state.OrderState;
import com.medical.exam.config.ExamWxPayConfig;
import com.medical.exam.dao.AddresseeMapper;
import com.medical.exam.dao.CommodityMapper;
import com.medical.exam.dao.OrderMapper;
import com.medical.exam.dao.PersonalMapper;
import com.medical.exam.dao.WechatMapper;
import com.medical.exam.service.OrderService;
import com.medical.exam.util.OrderUtils;
import com.medical.exam.util.StringUtils;
import com.medical.exam.util.WechatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final CommodityMapper commodityMapper;
    private final PersonalMapper personalMapper;
    private final AddresseeMapper addresseeMapper;
    private final WechatMapper wechatMapper;

    public OrderServiceImpl(OrderMapper orderMapper, CommodityMapper commodityMapper, PersonalMapper personalMapper, AddresseeMapper addresseeMapper, WechatMapper wechatMapper) {
        this.orderMapper = orderMapper;
        this.commodityMapper = commodityMapper;
        this.personalMapper = personalMapper;
        this.addresseeMapper = addresseeMapper;
        this.wechatMapper = wechatMapper;
    }

    @Override
    public List<?> getMyOrderList(Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        return this.orderMapper.select(order).stream().map(o -> {
            MyOrderListVo myOrderListVo = new MyOrderListVo();
            myOrderListVo.setOrder(o);
            myOrderListVo.setCommodity(this.commodityMapper.selectByPrimaryKey(o.getCommodityId()));
            myOrderListVo.setPersonal(this.personalMapper.selectByPrimaryKey(o.getUserId()));
            //格式化手机号格式为:156****3372
            if (null != myOrderListVo.getPersonal()) {
                String phone = myOrderListVo.getPersonal().getPhone();
                try {
                    phone = StringUtils.formatPhone(phone);
                } catch (Exception ignore) {

                } finally {
                    myOrderListVo.getPersonal().setPhone(phone);
                }

            }


            return myOrderListVo;
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean sendBack(String orderId, String courierNo) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCourierNo(courierNo);
        return this.orderMapper.updateByPrimaryKeySelective(order) > 0;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public Order createOrder(Long commodityId, String remark, Integer quantity, String addressee, String phone, String address, BigDecimal amount, Long userId) {
        try {
            //save order
            Order order = new Order();
            order.setOrderId(OrderUtils.generateTimeBasedOrderId(userId));
            order.setUserId(userId).setCommodityId(commodityId).setQuantity(quantity).setAddressee(addressee).setPhone(phone).setAddress(address).setAmount(amount).setRemark(remark);
            //save addressee
            Addressee updateAddressee = new Addressee();
            updateAddressee.setUserId(userId);
            updateAddressee.setAddressee(addressee);
            updateAddressee.setPhone(phone);
            updateAddressee.setAddress(address);
            if (null == this.addresseeMapper.selectByPrimaryKey(userId)) {
                this.addresseeMapper.insertSelective(updateAddressee);
            } else {
                this.addresseeMapper.updateByPrimaryKeySelective(updateAddressee);
            }

            return this.orderMapper.insertSelective(order) > 0 ? order : null;

        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public WxPayVo payOrder(String orderId, String code) throws Exception {
        Order order = this.orderMapper.selectByPrimaryKey(orderId);
        if (null == order) {
            throw new IllegalArgumentException("订单号未找到,请确认订单号是否正确.");
        }
        Commodity commodity = this.commodityMapper.selectByPrimaryKey(order.getCommodityId());
        if (null == commodity) {
            throw new IllegalArgumentException("商品信息未找到,请检查商品是否存在.");
        }
        JSONObject result = WechatUtils.loginMiniProgram(code);
        if (null == result) {
            throw new IllegalArgumentException("code 已失效,请重新获取,注意:code超时时间为5分钟,且只能使用一次.");
        }
        String openid = result.getString("openid");

        WxPayVo wxPayVo = WechatUtils.unifiedOrder(order, commodity, openid);
        //update prepay_id for the order.
        order.setPrepayId(wxPayVo.getPackageStr().replace("prepay_id=", ""));
        this.orderMapper.updateByPrimaryKeySelective(order);
        return wxPayVo;
    }

    @Override
    public String callback(String notifyData) {
        try {
            ExamWxPayConfig config = new ExamWxPayConfig();
            WXPay wxpay = new WXPay(config);
            // 转换成map
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);

            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                LoggerHolder.getLogger().info("callback:微信支付接收结果签名正确.");
                Order order = new Order();
                order.setOrderId(notifyMap.get(WechatUtils.ORDER_ID_FIELD));
                order = orderMapper.selectOne(order);
                if (null != order && updateOrderStatus(order.getOrderId(), new OrderState(OrderState.PAID))) {
                    Map<String, String> result = new HashMap<>(SystemConstant.INIT_SIZE);
                    result.put("return_code", WechatUtils.SUCCESS);
                    result.put("return_msg", WechatUtils.OK);
                    return WXPayUtil.mapToXml(result);
                }

            } else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                LoggerHolder.getLogger().error("callback:微信支付接收结果签名错误");
            }
            return null;
        } catch (Exception e) {
            LoggerHolder.getLogger().error("callback:{}", e.getMessage(), e);
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public Boolean updateOrderStatus(String orderId, OrderState orderState) {
        try {
            Example example = new Example(Order.class);
            int newState = orderState.stateValue();
            int currentState = orderState.back();
            example.createCriteria()
                .andEqualTo("orderId", orderId)
                .andEqualTo("status", currentState);
            Order order = new Order();
            //set payTime
            if (orderState.isPaid()) {
                order.setPayTime(new Date());
            }
            order.setStatus(newState);
            return orderMapper.updateByExampleSelective(order, example) > 0;
        } catch (Throwable e) {
            LoggerHolder.getLogger().error("updateOrderStatus:{}", e.getMessage(), e);
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public PageInfo findStaffStatusByCompanyId(Long companyId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<StaffStatusVo> staffStatusVos = this.orderMapper.findStaffStatusByCompanyId(companyId);
        return PageInfoFactory.getPageInfo(staffStatusVos);
    }

    @Override
    public PageInfo getOrderPage(Integer page, Integer pageSize, String orderId, Integer status) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(Order.class);
        example.orderBy("createTime").desc().orderBy("status").asc();
        if (StringUtils.isNotBlank(orderId)) {
            example.and().andEqualTo("orderId", orderId);
        }
        if (status > -1) {
            example.and().andEqualTo("status", status);
        }
        return PageInfoFactory.getPageInfo(this.orderMapper.selectByExample(example));
    }

    @Override
    public Order getByOrderId(String orderId) {
        return this.orderMapper.selectByPrimaryKey(orderId);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public Boolean cancelOrder(String orderId) {
        try {
            AtomicBoolean flag = new AtomicBoolean(false);
            Example example = new Example(Order.class);
            example.setForUpdate(true);
            example.createCriteria().andEqualTo("orderId", orderId);
            Order existOrder = this.orderMapper.selectOneByExample(example);
            if (null == existOrder) {
                throw new IllegalArgumentException("订单信息不存在");

            }
            OrderState orderState = new OrderState(existOrder.getStatus());
            orderState.addObserver((o, arg) -> {
                int newState = (int) arg;
                if (newState == OrderState.CANCELLED) {
                    existOrder.setStatus(newState);
                    flag.set(this.orderMapper.updateByPrimaryKeySelective(existOrder) > 0);
                }
            });
            //cancel the order
            orderState.cancel();

            return flag.get();

        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean modifyOrderStatus(String orderId, Integer status) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(status);
        return this.orderMapper.updateByPrimaryKeySelective(order) > 0;
    }
}

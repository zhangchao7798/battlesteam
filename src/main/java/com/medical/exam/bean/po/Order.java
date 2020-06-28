package com.medical.exam.bean.po;

import com.medical.exam.bean.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Table(name = "sys_order")
public class Order extends BaseEntity {
    @Id
    private String orderId;
    private Long userId;
    private Long commodityId;
    /**
     * 0->未支付,1->已支付,2->运送中,3->回送中,4->待检查(或者说检查中),5->已完成(或者说已出报告),9->已取消
     */
    private Integer status;
    private Date payTime;
    private String courierNo;
    /***
     * 收件人
     */
    private String addressee;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * prepay id
     */
    private String prepayId;
    public String getOrderId() {
        return orderId;
    }

    public Order setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Order setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public Order setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Order setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getPayTime() {
        return payTime;
    }

    public Order setPayTime(Date payTime) {
        this.payTime = payTime;
        return this;
    }

    public String getCourierNo() {
        return courierNo;
    }

    public Order setCourierNo(String courierNo) {
        this.courierNo = courierNo;
        return this;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public Order setPrepayId(String prepayId) {
        this.prepayId = prepayId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (!orderId.equals(order.orderId)) return false;
        if (!userId.equals(order.userId)) return false;
        if (commodityId != null ? !commodityId.equals(order.commodityId) : order.commodityId != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        return payTime != null ? payTime.equals(order.payTime) : order.payTime == null;

    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, commodityId, status, payTime);
    }

    public String getAddressee() {
        return addressee;
    }

    public Order setAddressee(String addressee) {
        this.addressee = addressee;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Order setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Order setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Order setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
            "orderId='" + orderId + '\'' +
            ", userId=" + userId +
            ", commodityId=" + commodityId +
            ", status=" + status +
            ", payTime=" + payTime +
            ", courierNo='" + courierNo + '\'' +
            ", addressee='" + addressee + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", amount=" + amount +
            ", quantity=" + quantity +
            ", prepayId='" + prepayId + '\'' +
            "} " + super.toString();
    }
}

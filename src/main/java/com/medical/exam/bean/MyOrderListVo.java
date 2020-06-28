package com.medical.exam.bean;

import com.medical.exam.bean.po.Commodity;
import com.medical.exam.bean.po.Order;
import com.medical.exam.bean.po.Personal;

import java.io.Serializable;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
public class MyOrderListVo implements Serializable {
    private Order order;
    private Commodity commodity;
    private Personal personal;

    public Order getOrder() {
        return order;
    }

    public MyOrderListVo setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public MyOrderListVo setCommodity(Commodity commodity) {
        this.commodity = commodity;
        return this;
    }

    public Personal getPersonal() {
        return personal;
    }

    public MyOrderListVo setPersonal(Personal personal) {
        this.personal = personal;
        return this;
    }

    @Override
    public String toString() {
        return "MyOrderListVo{" +
            "order=" + order +
            ", commodity=" + commodity +
            ", personal=" + personal +
            '}';
    }
}

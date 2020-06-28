package com.medical.exam.bean.vo;

import com.medical.exam.bean.po.Order;
import com.medical.exam.bean.po.Personal;
import com.medical.exam.bean.po.Result;
import com.medical.exam.bean.po.User;

import java.io.Serializable;
import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/12
 */
public class ReportVo implements Serializable {
    private Personal personal;
    private Order order;
    private List<ResultReportVo> result;

    public Personal getPersonal() {
        return personal;
    }

    public ReportVo setPersonal(Personal personal) {
        this.personal = personal;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public ReportVo setOrder(Order order) {
        this.order = order;
        return this;
    }

    public List<ResultReportVo> getResult() {
        return result;
    }

    public ReportVo setResult(List<ResultReportVo> result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "ReportVo{" +
            "personal=" + personal +
            ", order=" + order +
            ", result=" + result +
            '}';
    }
}

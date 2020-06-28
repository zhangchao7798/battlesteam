package com.medical.exam.bean.vo;


import java.io.Serializable;
import java.util.Date;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/11
 */
public class StaffStatusVo implements Serializable {
    private String realName;
    private Integer status;
    private String phone;
    private Date createTime;

    public String getRealName() {
        return realName;
    }

    public StaffStatusVo setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public StaffStatusVo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public StaffStatusVo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public StaffStatusVo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "StaffStatusVo{" +
            "realName='" + realName + '\'' +
            ", status=" + status +
            ", phone='" + phone + '\'' +
            ", createTime=" + createTime +
            '}';
    }
}

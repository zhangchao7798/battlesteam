/**
 * Copyright (C), 2015-2019
 * FileName: Addressee
 * Author:   Wu
 * Date:     2019/7/10 20:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.bean.po;

import com.medical.exam.bean.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Table(name = "sys_user_addressee")
public class Addressee extends BaseEntity {
    @Id
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Addressee{" +
            "userId=" + userId +
            ", addressee='" + addressee + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addressee addressee = (Addressee) o;
        return Objects.equals(userId, addressee.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }
}

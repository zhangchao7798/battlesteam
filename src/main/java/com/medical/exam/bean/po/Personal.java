package com.medical.exam.bean.po;

import com.medical.exam.bean.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Table(name = "sys_user_personal")
public class Personal extends BaseEntity {
    @Id
    private Long userId;
    private String realName;
    private Integer age;
    private String phone;
    private String contact;
    private String contactPhone;
    /**
     * 当事人近视度数
     */
    private Integer degree;
    /**
     * 父亲近视度数
     */
    private Integer fatherDegree;
    /**
     * 母亲近视度数
     */
    private Integer motherDegree;

    public Long getUserId() {
        return userId;
    }

    public Personal setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public Personal setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Personal setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Personal setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public Personal setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public Personal setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public Integer getFatherDegree() {
        return fatherDegree;
    }

    public Personal setFatherDegree(Integer fatherDegree) {
        this.fatherDegree = fatherDegree;
        return this;
    }

    public Integer getMotherDegree() {
        return motherDegree;
    }

    public Personal setMotherDegree(Integer motherDegree) {
        this.motherDegree = motherDegree;
        return this;
    }

    @Override
    public String toString() {
        return "Personal{" +
            "userId=" + userId +
            ", realName='" + realName + '\'' +
            ", age=" + age +
            ", phone='" + phone + '\'' +
            ", contact='" + contact + '\'' +
            ", contactPhone='" + contactPhone + '\'' +
            ", degree=" + degree +
            ", fatherDegree=" + fatherDegree +
            ", motherDegree=" + motherDegree +
            "} " + super.toString();
    }

    public Integer getDegree() {
        return degree;
    }

    public Personal setDegree(Integer degree) {
        this.degree = degree;
        return this;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personal)) return false;
        Personal that = (Personal) o;
        return Objects.equals(userId, that.userId) &&
            Objects.equals(realName, that.realName) &&
            Objects.equals(age, that.age) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(contact, that.contact) &&
            Objects.equals(contactPhone, that.contactPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, realName, phone);
    }
}

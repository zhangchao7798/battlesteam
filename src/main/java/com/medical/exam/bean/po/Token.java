package com.medical.exam.bean.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.medical.exam.bean.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/01
 */
@Table(name = "sys_token")
public class Token extends BaseEntity {
    /**
     * user id
     */
    @Id
    private Long userId;
    /**
     * token
     */
    private String token;

    /**
     * role type:
     * 0-普通角色,1->企业角色,2->商家角色,3->中间商
     */
    private Integer roleType;

    public Long getUserId() {
        return userId;
    }

    public Token setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Token setToken(String token) {
        this.token = token;
        return this;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public Token setRoleType(Integer roleType) {
        this.roleType = roleType;
        return this;
    }

    @Override
    public String toString() {
        return "Token{" +
            "userId=" + userId +
            ", token='" + token + '\'' +
            ", roleType=" + roleType +
            '}';
    }
}

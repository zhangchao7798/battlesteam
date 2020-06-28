package com.medical.exam.bean.po;


import com.medical.exam.bean.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/7/2
 */
@Table(name = "sys_wechat")
public class Wechat extends BaseEntity {
    @Id
    private Long userId;
    private String appId;
    private String openId;

    public Long getUserId() {
        return userId;
    }

    public Wechat setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public Wechat setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public Wechat setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    @Override
    public String toString() {
        return "Wechat{" +
            "userId=" + userId +
            ", appId='" + appId + '\'' +
            ", openId='" + openId + '\'' +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wechat)) return false;
        Wechat wechat = (Wechat) o;
        return userId.equals(wechat.userId) &&
            Objects.equals(appId, wechat.appId) &&
            Objects.equals(openId, wechat.openId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, appId, openId);
    }
}

package com.medical.exam.bean.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.medical.exam.bean.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/7/2
 */
@Table(name = "sys_user")
public class User extends BaseEntity {

  @Id
  @GeneratedValue(generator = "JDBC")
  private Long userId;
  /**真实姓名*/
  private String realName;
  /**手机号*/
  private String phone;
  /**密码*/
  @JSONField(serialize = false)
  private String password;
  /**
   * 0->正常,1->锁定
   */
  private Boolean userStatus;
  /**
   * role type:
   * 0-普通角色,1->企业角色,2->商家角色,3->中间商
   */
  private Integer roleType;
  /**
   * 企业id
   */
  private Long companyId;
  /**
   * 邀请人用户id
   */
  private Long invitedBy;


  public Long getUserId() {
    return userId;
  }

  public User setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public String getRealName() {
    return realName;
  }

  public User setRealName(String realName) {
    this.realName = realName;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public User setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public Boolean getUserStatus() {
    return userStatus;
  }

  public User setUserStatus(Boolean userStatus) {
    this.userStatus = userStatus;
    return this;
  }

  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  public Long getInvitedBy() {
    return invitedBy;
  }

  public User setInvitedBy(Long invitedBy) {
    this.invitedBy = invitedBy;
    return this;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", realName='" + realName + '\'' +
        ", phone='" + phone + '\'' +
        ", password='" + password + '\'' +
        ", userStatus=" + userStatus +
        ", roleType=" + roleType +
        ", companyId=" + companyId +
        ", invitedBy=" + invitedBy +
        "} " + super.toString();
  }

  public Integer getRoleType() {
    return roleType;
  }

  public User setRoleType(Integer roleType) {
    this.roleType = roleType;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return userId.equals(user.userId) &&
        Objects.equals(realName, user.realName) &&
        Objects.equals(phone, user.phone) &&
        Objects.equals(password, user.password) &&
        Objects.equals(userStatus, user.userStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, realName, phone, password, userStatus);
  }
}

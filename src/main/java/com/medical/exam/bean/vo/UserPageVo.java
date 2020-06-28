package com.medical.exam.bean.vo;

import com.medical.exam.bean.po.User;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/11
 */
public class UserPageVo extends User {
    /**
     * 邀请人
     */
    private String inviter;


    public String getInviter() {
        return inviter;
    }

    public UserPageVo setInviter(String inviter) {
        this.inviter = inviter;
        return this;
    }

    @Override
    public String toString() {
        return "UserPageVo{" +
            "inviter='" + inviter + '\'' +
            "} " + super.toString();
    }
}

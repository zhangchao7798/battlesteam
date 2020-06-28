package com.medical.exam.service;

import com.medical.exam.bean.po.User;
import com.medical.exam.common.page.PageInfo;

import javax.validation.constraints.NotNull;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/02
 */
public interface UserService {
    /**
     * login by password
     *
     * @param phone
     * @param password
     * @return
     */
    User loginByPwd(@NotNull String phone, @NotNull String password);

    /**
     * find by userId
     *
     * @param userId
     * @return
     */
    User findByUserId(Long userId);

    /**
     * get user page
     *
     * @param page
     * @param pageSize
     * @param realName
     * @param roleType
     * @return
     */
    PageInfo getUserPage(Integer page, Integer pageSize, String realName, Integer roleType);

    /**
     * bind user with company
     * @param userId
     * @param companyId
     * @return
     */
    Boolean bindUserWithCompany(Long userId, Long companyId);

    /**
     * modify user
     * @param userId
     * @param realName
     * @param phone
     * @param password
     * @param roleType
     * @return
     */
    Boolean modifyUser(Long userId, String realName, String phone, String password, Integer roleType);

    /**
     * create user
     * @param realName
     * @param phone
     * @param password
     * @param roleType
     * @return
     */
    Long createUser(String realName, String phone, String password, Integer roleType);
}

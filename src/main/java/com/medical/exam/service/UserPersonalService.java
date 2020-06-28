package com.medical.exam.service;

import com.medical.exam.bean.po.Personal;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
public interface UserPersonalService {
    /**
     * get personal
     * @param userId
     * @return
     */
    Personal getPersonal(Long userId);

    /**
     * save personal
     * @param userId
     * @param realName
     * @param age
     * @param phone
     * @param contact
     * @param contactPhone
     * @param degree
     * @param fatherDegree
     * @param motherDegree
     * @return
     */
    Boolean savePersonal(Long userId, String realName, Integer age, String phone, String contact, String contactPhone, Integer degree, Integer fatherDegree,Integer motherDegree);
}

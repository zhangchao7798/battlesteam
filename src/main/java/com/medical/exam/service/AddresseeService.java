/**
 * Copyright (C), 2015-2019
 * FileName: AddresseeService
 * Author:   Wu
 * Date:     2019/7/10 20:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.service;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
public interface AddresseeService {
    /**
     * save addressee
     * @param userId
     * @param addressee
     * @param phone
     * @param address
     * @return
     */
    Boolean saveAddressee(Long userId, String addressee, String phone, String address);
}

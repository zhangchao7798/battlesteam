/**
 * Copyright (C), 2015-2019
 * FileName: AddresseeServiceImpl
 * Author:   Wu
 * Date:     2019/7/10 20:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.service.impl;

import com.medical.exam.bean.po.Addressee;
import com.medical.exam.dao.AddresseeMapper;
import com.medical.exam.service.AddresseeService;
import org.springframework.stereotype.Service;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Service
public class AddresseeServiceImpl implements AddresseeService {
    private final AddresseeMapper addresseeMapper;

    public AddresseeServiceImpl(AddresseeMapper addresseeMapper) {
        this.addresseeMapper = addresseeMapper;
    }

    @Override
    public Boolean saveAddressee(Long userId, String addressee, String phone, String address) {
        Addressee updateAddressee = new Addressee();
        updateAddressee.setUserId(userId);
        updateAddressee.setAddressee(addressee);
        updateAddressee.setPhone(phone);
        updateAddressee.setAddress(address);
        if (null == this.addresseeMapper.selectByPrimaryKey(userId)) {
            return this.addresseeMapper.insertSelective(updateAddressee) > 0;
        } else {
            return this.addresseeMapper.updateByPrimaryKeySelective(updateAddressee) > 0;
        }

    }
}

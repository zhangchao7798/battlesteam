package com.medical.exam.service.impl;

import com.medical.exam.bean.po.Personal;
import com.medical.exam.common.annotation.logger.LoggerHolder;
import com.medical.exam.common.exception.ServiceException;
import com.medical.exam.dao.PersonalMapper;
import com.medical.exam.service.UserPersonalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Service
public class PersonalServiceImpl implements UserPersonalService {
    private final PersonalMapper personalMapper;

    public PersonalServiceImpl(PersonalMapper personalMapper) {
        this.personalMapper = personalMapper;
    }

    @Override
    public Personal getPersonal(Long userId) {
        return personalMapper.selectByPrimaryKey(userId);
    }



    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public Boolean savePersonal(Long userId, String realName, Integer age, String phone, String contact, String contactPhone, Integer degree, Integer fatherDegree,Integer motherDegree) {
        try {
            Personal t = new Personal();
            t.setUserId(userId);
            t.setRealName(realName);
            t.setAge(age);
            t.setPhone(phone);
            t.setContact(contact);
            t.setContactPhone(contactPhone);
            t.setDegree(degree);
            t.setFatherDegree(fatherDegree);
            t.setMotherDegree(motherDegree);
            Personal personal = personalMapper.selectByPrimaryKey(userId);
            if (null == personal) {
                return personalMapper.insertSelective(t) > 0;
            } else {
                return personalMapper.updateByPrimaryKeySelective(t) > 0;
            }
        } catch (Exception e) {
            LoggerHolder.getLogger().error("savePersonal:{}", e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }

    }
}

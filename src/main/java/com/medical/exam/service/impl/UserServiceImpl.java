package com.medical.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.medical.exam.bean.po.User;
import com.medical.exam.common.exception.ServiceException;
import com.medical.exam.common.page.PageInfo;
import com.medical.exam.common.page.PageInfoFactory;
import com.medical.exam.dao.UserMapper;
import com.medical.exam.service.UserService;
import com.medical.exam.util.MD5Utils;
import com.medical.exam.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/02
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User loginByPwd(@NotNull String phone, @NotNull String password) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(MD5Utils.GetMD5(password));
        return this.userMapper.selectOne(user);
    }

    @Override
    public User findByUserId(Long userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public PageInfo getUserPage(Integer page, Integer pageSize, String realName, Integer roleType) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(User.class);
        example.orderBy("createTime").desc();
        if (StringUtils.isNotBlank(realName)) {
            example.and().andEqualTo("realName", realName);
        }
        if (roleType > -1) {
            example.and().andEqualTo("roleType", roleType);
        }
        return PageInfoFactory.getPageInfo(this.userMapper.getUserPage(realName, roleType));
    }

    @Override
    public Boolean bindUserWithCompany(Long userId, Long companyId) {
        User user = new User();
        user.setUserId(userId);
        user.setCompanyId(companyId);
        return this.userMapper.updateByPrimaryKeySelective(user) > 0;
    }


    @Override
    public Boolean modifyUser(Long userId, String realName, String phone, String password, Integer roleType) {
        User user = new User();
        user.setUserId(userId);

        if (StringUtils.isNotBlank(realName)) {
            user.setRealName(realName);
        }
        if (StringUtils.isNotBlank(phone)) {
            user.setPhone(phone);
        }
        if (StringUtils.isNotBlank(password)) {
            user.setPassword(MD5Utils.GetMD5(password));
        }
        if (roleType > -1) {
            user.setRoleType(roleType);
        }
        return this.userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public Long createUser(String realName, String phone, String password, Integer roleType) {
        try {
            Example example = new Example(User.class);
            example.setForUpdate(true);
            example.createCriteria().andEqualTo("phone", phone);
            User existUser = this.userMapper.selectOneByExample(example);
            if (null == existUser) {
                User user = new User();
                if (StringUtils.isNotBlank(realName)) {
                    user.setRealName(realName);
                }
                if (StringUtils.isNotBlank(phone)) {
                    user.setPhone(phone);
                }
                if (StringUtils.isNotBlank(password)) {
                    user.setPassword(MD5Utils.GetMD5(password));
                }
                if (roleType > -1) {
                    user.setRoleType(roleType);
                }
                return this.userMapper.insertSelective(user) > 0 ? user.getUserId() : null;
            } else {
                if (StringUtils.isNotBlank(realName)) {
                    existUser.setRealName(realName);
                }
                if (StringUtils.isNotBlank(password)) {
                    existUser.setPassword(MD5Utils.GetMD5(password));
                }
                if (roleType > -1) {
                    existUser.setRoleType(roleType);
                }
                return this.userMapper.updateByPrimaryKeySelective(existUser) > 0 ? existUser.getUserId() : null;
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());

        }


    }
}

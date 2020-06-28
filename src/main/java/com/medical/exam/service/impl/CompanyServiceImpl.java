/**
 * Copyright (C), 2015-2019
 * FileName: CompanyServiceImpl
 * Author:   Wu
 * Date:     2019/7/10 20:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.medical.exam.bean.po.Company;
import com.medical.exam.common.page.PageInfo;
import com.medical.exam.common.page.PageInfoFactory;
import com.medical.exam.dao.CompanyMapper;
import com.medical.exam.service.CompanyService;
import com.medical.exam.util.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public PageInfo getCompanyPage(Integer page, Integer pageSize, String companyName) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(Company.class);
        example.orderBy("createTime").desc();
        if (StringUtils.isNotBlank(companyName)) {
            example.and().andEqualTo("companyName", companyName);
        }
        return PageInfoFactory.getPageInfo(this.companyMapper.selectByExample(example));
    }

    @Override
    public Boolean createCompany(String companyName) {
        Company company = new Company();
        company.setCompanyName(companyName);
        return this.companyMapper.insertSelective(company) > 0;
    }

    @Override
    public Boolean modifyCompany(Long companyId, String companyName) {
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCompanyName(companyName);
        return this.companyMapper.updateByPrimaryKeySelective(company) > 0;
    }
}

/**
 * Copyright (C), 2015-2019
 * FileName: CompanyService
 * Author:   Wu
 * Date:     2019/7/10 20:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.service;

import com.medical.exam.common.page.PageInfo;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
public interface CompanyService {
    /**
     * get company page
     * @param page
     * @param pageSize
     * @param companyName
     * @return
     */
    PageInfo getCompanyPage(Integer page, Integer pageSize, String companyName);

    /**
     * create company
     * @param companyName
     * @return
     */
    Boolean createCompany(String companyName);

    /**
     * modify company
     * @param companyId
     * @param companyName
     * @return
     */
    Boolean modifyCompany(Long companyId, String companyName);
}

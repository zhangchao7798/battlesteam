/**
 * Copyright (C), 2015-2019
 * FileName: Company
 * Author:   Wu
 * Date:     2019/7/10 20:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.bean.po;

import com.medical.exam.bean.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Table(name = "sys_company")
public class Company extends BaseEntity {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long companyId;
    private String companyName;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Company{" +
            "companyId=" + companyId +
            ", companyName='" + companyName + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyId, company.companyId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(companyId);
    }
}

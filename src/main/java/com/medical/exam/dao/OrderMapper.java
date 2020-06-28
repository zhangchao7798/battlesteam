package com.medical.exam.dao;

import com.medical.exam.bean.po.Order;
import com.medical.exam.bean.po.User;
import com.medical.exam.bean.vo.StaffStatusVo;
import com.medical.exam.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
public interface OrderMapper extends MyMapper<Order> {
    /**
     * find staff status by company id
     * @param companyId
     * @return
     */
    @Select("SELECT u.real_name,u.phone,o.status,o.create_time from sys_user u LEFT JOIN sys_order o ON u.user_id=o.user_id JOIN sys_company c ON u.company_id=c.company_id WHERE c.company_id=#{companyId}")
    List<StaffStatusVo> findStaffStatusByCompanyId(@Param("companyId") Long companyId);
}

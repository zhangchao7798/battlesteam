package com.medical.exam.dao;

import com.medical.exam.bean.po.User;
import com.medical.exam.bean.vo.UserPageVo;
import com.medical.exam.util.MyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/02
 */
public interface UserMapper extends MyMapper<User> {
    /**
     *  get user page
     * @param realName
     * @param roleType
     * @return
     */
    @Select("<script>" +
        "SELECT a.*,b.real_name inviter from sys_user a LEFT JOIN sys_user b ON a.invited_by=b.user_id" +
        "<where>" +
        "<if test=\"realName!=null and realName != ''\">" +
        "a.real_name like CONCAT('%',#{realName},'%')" +
        "</if>" +
        "<if test=\"roleType>-1\">" +
        "and a.role_type=#{roleType}" +
        "</if>" +
        "</where>" +
        "</script>")
    List<UserPageVo> getUserPage(String realName, Integer roleType);

}

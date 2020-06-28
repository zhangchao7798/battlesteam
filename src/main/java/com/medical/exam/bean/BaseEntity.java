package com.medical.exam.bean;


import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Wu
 * @date 2019/7/1
 */
public class BaseEntity implements Serializable {
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改日期
     */
    private Date modifyTime;
    /**
     * 备注
     */
    @JSONField(serialize = false)
    private String remark;
    /**
     * 删除标识
     */
    @JSONField(serialize = false)
    private Boolean delFlag;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
            "createTime=" + createTime +
            ", modifyTime=" + modifyTime +
            ", remark='" + remark + '\'' +
            ", delFlag=" + delFlag +
            '}';
    }
}

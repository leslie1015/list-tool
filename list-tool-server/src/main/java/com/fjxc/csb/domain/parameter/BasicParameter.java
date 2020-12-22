package com.fjxc.csb.domain.parameter;

import java.util.Date;

/**
 * @author wangyong
 */
public class BasicParameter {

    /**
     * 参数组的key
     */
    private String paramType;

    /**
     * 参数名
     */
    private String paramName;

    /**
     * 参数key，param_group_key + param_key确定唯一数据
     */
    private String paramKey;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 语言，1中文，2英文
     */
    private Short lanCode;

    /**
     * 顺序，按照所填数字排序
     */
    private Short orderNum;

    /**
     * 备注
     */
    private String remark;

    private String addNo;

    private String addName;

    private Date addTime;

    private String updateNo;

    private String updateName;

    private Date updateTime;

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Short getLanCode() {
        return lanCode;
    }

    public void setLanCode(Short lanCode) {
        this.lanCode = lanCode;
    }

    public Short getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Short orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddNo() {
        return addNo;
    }

    public void setAddNo(String addNo) {
        this.addNo = addNo;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(String updateNo) {
        this.updateNo = updateNo;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

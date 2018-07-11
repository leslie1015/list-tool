package com.fjxc.csb.domain.resource;

import com.fjxc.csb.domain.parameter.SimpleParam;

import java.util.List;

/**
 * @author wangyong
 * @date 2018/6/23
 */
public class ListToolResourceField {

    /**
     * 资源ID
     */
    private Integer resourceId;

    /**
     * 对应字段
     */
    private String fieldName;

    /**
     * 显示名
     */
    private String showName;

    /**
     * 链接类型。0: 无，1: 普通链接，2: JS方法
     */
    private Short targetType;

    /**
     * target_type为1，填写url，target_type为2，填写JS方法名
     */
    private String targetUrl;

    /**
     * 字段排序,数字小的排在前面
     */
    private Integer indexNo;

    /**
     * 对应basic_parameter.param_group_key，配置后自动带出下拉参数
     */
    private String paramGroupKey;

    /**
     * 0不搜索，1模糊搜索，2精确搜索，3下拉搜索，4时间区间搜索，5数字区间，6多选搜索
     */
    private Integer searchType;

    /**
     * 0不排序，1正序，2倒序
     */
    private Short sortType;

    /**
     * 字段类型。1:字符，2:数字，3:数字
     */
    private Short fieldType;

    /**
     * searchType为3时，下拉查询的参数
     */
    List<SimpleParam> selectParams;

    public List<SimpleParam> getSelectParams() {
        return selectParams;
    }

    public void setSelectParams(List<SimpleParam> selectParams) {
        this.selectParams = selectParams;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getShowName() {
        return showName;
    }

    public Short getTargetType() {
        return targetType;
    }

    public void setTargetType(Short targetType) {
        this.targetType = targetType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getParamGroupKey() {
        return paramGroupKey;
    }

    public void setParamGroupKey(String paramGroupKey) {
        this.paramGroupKey = paramGroupKey;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Short getSortType() {
        return sortType;
    }

    public void setSortType(Short sortType) {
        this.sortType = sortType;
    }

    public Short getFieldType() {
        return fieldType;
    }

    public void setFieldType(Short fieldType) {
        this.fieldType = fieldType;
    }
}

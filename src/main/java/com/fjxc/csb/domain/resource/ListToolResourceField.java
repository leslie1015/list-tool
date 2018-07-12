package com.fjxc.csb.domain.resource;

import com.fjxc.csb.domain.parameter.SimpleParam;

import java.util.List;

/**
 * @author wangyong
 * @date 2018/6/23
 */
public class ListToolResourceField {

    /**
     * 1,：普通字段，2按钮
     */
    public static final Short FIELD_TYPE_BUTTON = 2;

    private Integer resourceId;

    private String fieldName;

    private String showName;

    private Short targetType;

    private String targetUrl;

    private Integer indexNo;

    private String paramGroupKey;

    private Integer searchType;

    private Short sortType;

    private Short fieldType;

    private Short primarykeyFlag;

    /**
     * 下拉参数
     */
    List<SimpleParam> selectParams;

    List<ListToolActionInfo> actions;

    public List<ListToolActionInfo> getActions() {
        return actions;
    }

    public void setActions(List<ListToolActionInfo> actions) {
        this.actions = actions;
    }

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

    public Short getPrimarykeyFlag() {
        return primarykeyFlag;
    }

    public void setPrimarykeyFlag(Short primarykeyFlag) {
        this.primarykeyFlag = primarykeyFlag;
    }
}

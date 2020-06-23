package com.fjxc.csb.domain.resource;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangyong
 */
public class SearchVO {

    private static final String DEFAULT_PAGE_NO = "1";

    private static final String DEFAULT_PAGE_SIZE = "10";

    private String resourceId;

    /**
     * 查询参数json，前台array不能直接传到后台。见鬼了。。所以前台转成json格式传输，后台再转为List
     */
    private String paramsJson;

    private String pageSize;

    private String currentPageNo;

    private String sortField;

    private String sortType;

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getPageSize() {
        return StringUtils.isBlank(pageSize) ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCurrentPageNo() {
        return StringUtils.isBlank(currentPageNo)  ? DEFAULT_PAGE_NO : currentPageNo;
    }

    public void setCurrentPageNo(String currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getParamsJson() {
        return paramsJson;
    }

    public void setParamsJson(String paramsJson) {
        this.paramsJson = paramsJson;
    }
}

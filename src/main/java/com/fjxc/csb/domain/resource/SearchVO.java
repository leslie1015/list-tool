package com.fjxc.csb.domain.resource;

public class SearchVO {

    private String resourceId;

    /**
     * 查询参数json，前台array不能直接传到后台。见鬼了。。所以前台转成json格式传输，后台再转为List
     */
    private String paramsJson;

    private String pageSize;

    private String currentPageNo;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCurrentPageNo() {
        return currentPageNo;
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

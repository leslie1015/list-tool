package com.fjxc.csb.domain.resource;

import java.util.Date;
import java.util.List;

/**
 * @author wangyong
 * @date 2018/6/23
 */
public class ListToolQueue {

    private Integer queueId;

    private Integer resourceId;

    private Integer parentQueueId;

    private String resourceName;

    private Integer resourceLevel;

    private String resourceType;

    private String resourceUrl;

    private Short activeFlag;

    private Integer orderNum;

    private Integer appType;

    private Date addTime;

    private String addNo;

    private String addName;

    private Boolean expanded = false;
    private Boolean selected = false;

    private List<ListToolQueue> childQueues;

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getParentQueueId() {
        return parentQueueId;
    }

    public void setParentQueueId(Integer parentQueueId) {
        this.parentQueueId = parentQueueId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Short getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Short activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(Integer resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public List<ListToolQueue> getChildQueues() {
        return childQueues;
    }

    public void setChildQueues(List<ListToolQueue> childQueues) {
        this.childQueues = childQueues;
    }
}

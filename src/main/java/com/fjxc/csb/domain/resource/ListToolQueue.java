package com.fjxc.csb.domain.resource;

import java.util.Date;
import java.util.List;

/**
 * @author wangyong
 * @date 2018/6/23
 */
public class ListToolQueue {

    /**
     * 队列id
     */
    private Integer queueId;

    /**
     * 资源id
     */
    private Integer resourceId;

    /**
     * 父级菜单ID
     */
    private Integer parentQueueId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 菜单资源等级 1一级标题，2二级标题，3三级标题，4四级标题
     */
    private Integer resourceLevel;

    /**
     * 资源类型：1.按钮，2.资源链接，3.子资源汇总页面链接。为2,3时resource_url不能为空
     */
    private Short resourceType;

    /**
     * 资源链接
     */
    private String resourceUrl;

    /**
     * 0禁用，1启用
     */
    private Short activeFlag;

    /**
     * 排序编号，数字小的菜单在上面，只针对同一父菜单下同级别菜单排序
     */
    private Integer orderNum;

    /**
     * 所属应用,定义在枚举类AppTypeEnum中
     */
    private Integer appType;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     *添加人编号
     */
    private String addNo;

    /**
     * 添加人
     */
    private String addName;

    /**
     * 子队列
     */
    private List<ListToolQueue> childQueues;

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

    public Short getResourceType() {
        return resourceType;
    }

    public void setResourceType(Short resourceType) {
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

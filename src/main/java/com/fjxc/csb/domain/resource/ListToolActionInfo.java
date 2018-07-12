package com.fjxc.csb.domain.resource;

/**
 * @author: wangyong
 * @email: wangyy866@163.com
 */
public class ListToolActionInfo {

    private Integer resourceId;

    private String buttonTag;

    private String showName;

    private String buttonStyle;

    private String clickFunction;

    private Integer orderNum;

    private Integer requestType;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getButtonTag() {
        return buttonTag;
    }

    public void setButtonTag(String buttonTag) {
        this.buttonTag = buttonTag;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getButtonStyle() {
        return buttonStyle;
    }

    public void setButtonStyle(String buttonStyle) {
        this.buttonStyle = buttonStyle;
    }

    public String getClickFunction() {
        return clickFunction;
    }

    public void setClickFunction(String clickFunction) {
        this.clickFunction = clickFunction;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }
}

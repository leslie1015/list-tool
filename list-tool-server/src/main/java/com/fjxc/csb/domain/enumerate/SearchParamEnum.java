package com.fjxc.csb.domain.enumerate;


import com.fjxc.csb.domain.resource.SearchParam;

import java.util.List;

/**
 * @author wangyong
 */
public enum SearchParamEnum {

    NOT_SEARCH(0, "不查询"),
    FUZZY(1, "模糊查询"),
    EXACT(2, "精确查询"),
    DROP_DOWN(3, "下拉查询"),
    DATE_INTERVAL(4, "时间区间"),
    NUMBER_INTERVAL(5, "数字区间"),
    DROP_DOWN_CHOICE(6, "下拉多选");

    private Integer type;

    private String desc;

    SearchParamEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

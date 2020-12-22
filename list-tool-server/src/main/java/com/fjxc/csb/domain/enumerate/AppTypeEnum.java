package com.fjxc.csb.domain.enumerate;


/**
 * @author wangyong
 */

public enum AppTypeEnum {

    XGBJ(1, "xgbj", "徐工备件");

    private Integer enumpType;

    private String appShortName;

    private String appDesc;

    AppTypeEnum(Integer enumpType, String appShortName, String appDesc) {
        this.enumpType = enumpType;
        this.appShortName = appShortName;
        this.appDesc = appDesc;
    }

    AppTypeEnum() {

    }
}

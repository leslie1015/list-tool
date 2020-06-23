package com.fjxc.csb.domain.resource;

import com.fjxc.csb.domain.parameter.SimpleParam;
import lombok.Data;

import java.util.List;

/**
 * @author wangyong
 * @date 2018/6/23
 */
@Data
public class ListToolResourceField {

    /**
     * 1,：普通字段，2按钮
     */
    public static final Short FIELD_TYPE_BUTTON = 2;

    private Integer resourceId;

    private String fieldName;

    private String showName;

    private Short hideFlag;

    private Short targetType;

    private String targetUrl;

    private Integer indexNo;

    private String paramGroupKey;

    private Integer searchType;

    private Short sortType;

    private Short fieldType;

    private Short primarykeyFlag;

    private Short isDate;

    private Integer dateFormatType;

    /**
     * 下拉参数
     */
    List<SimpleParam> selectParams;

    List<ListToolActionInfo> actions;
}

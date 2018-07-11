package com.fjxc.csb.domain.resource;

/**
 * @author wangyong
 */
public class SearchParam {

    // 1大于，2小于
    public static final Short MORE_TYPE = 1, LESS_TYPE = 2;

    /**
     * 查询方式：0不搜索，1模糊搜索，2精确搜索，3下拉搜索，4时间区间搜索，5数字区间，6多选搜索
     */
    private Integer type;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 1.大于，2小于
     */
    private Short moreOrLessType;

    /**
     * 字段值
     */
    private String value;

    /**
     * 字段类型。1:字符，2:数字，3:数字
     */
    private Short fieldType;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Short getMoreOrLessType() {
        return moreOrLessType;
    }

    public void setMoreOrLessType(Short moreOrLessType) {
        this.moreOrLessType = moreOrLessType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Short getFieldType() {
        return fieldType;
    }

    public void setFieldType(Short fieldType) {
        this.fieldType = fieldType;
    }
}

package com.fjxc.csb.domain.resource;

/**
 * @author wangyong
 */
public class SearchParam {

    public static final Short MORE_TYPE = 1, LESS_TYPE = 2;

    private Integer type;

    private String fieldName;

    private Short moreOrLessType;

    private String value;

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

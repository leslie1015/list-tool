package com.fjxc.csb.domain;

import lombok.Data;

@Data
public class SearchCommonVO<T> {

    private int pageNum;

    private int pageSize;

    private T filters;

    private String sort;
}

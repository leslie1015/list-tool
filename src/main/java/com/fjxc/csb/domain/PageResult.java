package com.fjxc.csb.domain;

import java.util.List;

/**
 * @author wangyong
 */
public class PageResult<T> {

    private Long total;

    private List<T>  resultList;

    public PageResult(Long total, List<T> resultList) {
        this.total = total;
        this.resultList = resultList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
}

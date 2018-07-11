package com.fjxc.csb.resultfilter;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public abstract class ResultFilterService {

    /**
     * 对查询结果进行过滤更改
     *
     * @param result
     * @return
     */
    public abstract List<HashMap> resultFilter(List<HashMap> result);
}

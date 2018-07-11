package com.fjxc.csb.dao;

import com.fjxc.csb.domain.resource.SearchParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Mapper
public interface ListToolMapper {

    List<HashMap<String, Object>> executeSearch(String resourceId);

    /**
     * post请求查询
     *
     * @param resourceId
     * @param params
     * @return
     */
    List<HashMap<String, Object>> executeSearch(String resourceId, List<SearchParam> params);
}

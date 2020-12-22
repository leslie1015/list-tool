package com.fjxc.csb.dao;

import com.fjxc.csb.domain.resource.SearchParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Mapper
public interface ListToolMapper {

    /**
     * get请求查询，只在页面加载时候执行
     * @param resourceId
     * @return
     */
    List<HashMap<String, Object>> executeSearch(String resourceId);

    /**
     * post请求查询，点击查询、翻页、切换页码等动态替换此查询返回的数据
     *
     * @param resourceId
     * @param params
     * @return
     */
    List<HashMap<String, Object>> executeSearch(String resourceId, List<SearchParam> params);
}

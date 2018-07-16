package com.fjxc.csb.service.impl;

import com.alibaba.fastjson.JSON;
import com.fjxc.csb.dao.ListToolMapper;
import com.fjxc.csb.dao.parameter.BasicParameterMapper;
import com.fjxc.csb.dao.resource.ListToolResourceFieldMapper;
import com.fjxc.csb.domain.PageResult;
import com.fjxc.csb.domain.resource.ListToolResourceField;
import com.fjxc.csb.domain.resource.SearchParam;
import com.fjxc.csb.domain.resource.SearchVO;
import com.fjxc.csb.service.SearchService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wangyong
 */
@Component
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ListToolMapper listToolMapper;

    @Autowired
    private ListToolResourceFieldMapper listToolResourceFieldMapper;

    @Autowired
    private BasicParameterMapper basicParameterMapper;

    @Override
    public PageResult<HashMap<String, Object>> list(String resourceId) throws Exception {
        PageHelper.startPage(1, 10);
        List<HashMap<String, Object>> list = listToolMapper.executeSearch(resourceId);
        PageInfo<HashMap<String, Object>> pageInfo = new PageInfo<>(reSetBasicParam(Integer.valueOf(resourceId), list));
        return new PageResult<>(pageInfo.getTotal(), list);
    }

    @Override
    public PageResult<HashMap<String, Object>> search(SearchVO searchVO) throws Exception {
        // 分页插件应用
        List<SearchParam> searchParams = JSON.parseArray(searchVO.getParamsJson(), SearchParam.class);

        if (StringUtils.isNotBlank(searchVO.getSortField())) {
            PageHelper.startPage(Integer.valueOf(searchVO.getCurrentPageNo()), Integer.valueOf(searchVO.getPageSize()),searchVO.getSortField() + " " +searchVO.getSortType());
        } else {
            PageHelper.startPage(Integer.valueOf(searchVO.getCurrentPageNo()), Integer.valueOf(searchVO.getPageSize()));
        }
        List<HashMap<String, Object>> list = listToolMapper.executeSearch(searchVO.getResourceId(), searchParams);
        PageInfo<HashMap<String, Object>> pageInfo = new PageInfo<>(reSetBasicParam(Integer.valueOf(searchVO.getResourceId()), list));
        return new PageResult<>(pageInfo.getTotal(), list);
    }


    /**
     * 根据列表配置过滤需要替换参数值为显示值的字段，过滤器里面只能获取到资源路径名，获取resourceId还需要对字符串做处理，考虑到性能问题，
     * 所以放在service层做处理
     *
     * @param resourceId
     * @param mapList
     * @return
     */
    private List<HashMap<String, Object>> reSetBasicParam(Integer resourceId, List<HashMap<String, Object>> mapList) {
        List<ListToolResourceField> fieldList = listToolResourceFieldMapper.listNeedResetParamField(resourceId);
        if (CollectionUtils.isEmpty(fieldList)) {
            return mapList;
        }
        for (HashMap<String, Object> map : mapList) {
            for (ListToolResourceField field : fieldList) {
                //
                String paramKey = String.valueOf(map.get(field.getFieldName()));
                String value = basicParameterMapper.getValueByKey(field.getParamGroupKey(), paramKey);
                map.put(field.getFieldName(), value);
            }
        }
        return mapList;
    }


    @Override
    public String config(String resourceId) throws Exception {

        return null;
    }

}
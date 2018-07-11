package com.fjxc.csb.service.impl.resource;

import com.fjxc.csb.dao.resource.ListToolResourceFieldMapper;
import com.fjxc.csb.domain.enumerate.SearchParamEnum;
import com.fjxc.csb.domain.resource.ListToolResourceField;
import com.fjxc.csb.service.parameter.BasicParameterService;
import com.fjxc.csb.service.resource.ListToolResourceFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ListToolResourceFieldServiceImpl implements ListToolResourceFieldService {


    @Autowired
    private ListToolResourceFieldMapper listToolResourceFieldMapper;

    @Autowired
    private BasicParameterService basicParameterService;

    @Override
    public List<ListToolResourceField> listByResourceId(Integer resourceId) {

        List<ListToolResourceField> fieldInfo = listToolResourceFieldMapper.listByResourceId(resourceId);
        for (ListToolResourceField field : fieldInfo) {
            if (SearchParamEnum.DROP_DOWN.getType().equals(field.getSearchType()) ||
                    SearchParamEnum.DROP_DOWN_CHOICE.getType().equals(field.getSearchType())) {
                field.setSelectParams(basicParameterService.listKeyValByGroupKey(field.getParamGroupKey()));
            }
        }
        sort(fieldInfo);
        return fieldInfo;
    }

    /**
     * 按照字段orderNum排序子菜单
     *
     * @param childs
     */
    private void sort(List<ListToolResourceField> childs) {
        Collections.sort(childs, (c1, c2) -> {
            if (c1.getIndexNo() > c2.getIndexNo()) {
                return 1;
            }
            if (c1.getIndexNo().equals(c2.getIndexNo())) {
                return 0;
            }
            return -1;
        });
    }
}

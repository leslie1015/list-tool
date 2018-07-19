package com.fjxc.csb.service.impl.resource;

import com.fjxc.csb.constants.SystemConstants;
import com.fjxc.csb.dao.resource.ListToolResourceFieldMapper;
import com.fjxc.csb.domain.enumerate.SearchParamEnum;
import com.fjxc.csb.domain.resource.ListToolResourceField;
import com.fjxc.csb.service.parameter.BasicParameterService;
import com.fjxc.csb.service.resource.ListToolActionInfoService;
import com.fjxc.csb.service.resource.ListToolResourceFieldService;
import com.fjxc.csb.util.RedisUtils;
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

    @Autowired
    private ListToolActionInfoService listToolActionInfoService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<ListToolResourceField> listByResourceId(Integer resourceId) {

        String redisKey = SystemConstants.REDIS_KEY_PREFIX_RESOURCE_FIELD + resourceId;
        if (redisUtils.exists(redisKey)) {
            return (List<ListToolResourceField>) redisUtils.get(redisKey);
        }

        List<ListToolResourceField> fieldInfo = listToolResourceFieldMapper.listByResourceId(resourceId);
        sort(fieldInfo);
        for (ListToolResourceField field : fieldInfo) {
            if (SearchParamEnum.DROP_DOWN.getType().equals(field.getSearchType()) ||
                    SearchParamEnum.DROP_DOWN_CHOICE.getType().equals(field.getSearchType())) {
                field.setSelectParams(basicParameterService.listKeyValByGroupKey(field.getParamGroupKey()));
            }
            if (ListToolResourceField.FIELD_TYPE_BUTTON.equals(field.getFieldType())) {
                field.setActions(listToolActionInfoService.listByResourceId(resourceId));
            }
        }
        redisUtils.set(redisKey, fieldInfo, SystemConstants.REDIS_EXPIRED_TIME);
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

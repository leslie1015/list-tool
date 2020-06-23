package com.fjxc.csb.service.impl.resource;

import com.fjxc.csb.constants.SystemConstants;
import com.fjxc.csb.dao.resource.ListToolResourceFieldMapper;
import com.fjxc.csb.domain.enumerate.SearchParamEnum;
import com.fjxc.csb.domain.resource.FieldAddVO;
import com.fjxc.csb.domain.resource.ListToolResourceField;
import com.fjxc.csb.service.parameter.BasicParameterService;
import com.fjxc.csb.service.resource.ListToolActionInfoService;
import com.fjxc.csb.service.resource.ListToolResourceFieldService;
import com.fjxc.csb.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
                if (StringUtils.isBlank(field.getParamGroupKey())) {
                    continue;
                }
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

            if (null == c1.getIndexNo()) {
                return -1;
            }

            if (null == c2.getIndexNo()) {
                return 1;
            }

            if (c1.getIndexNo() > c2.getIndexNo()) {
                return 1;
            }
            if (c1.getIndexNo().equals(c2.getIndexNo())) {
                return 0;
            }
            return -1;
        });
    }

    private static final Short DEFAULT_SHORT_VALUE = 0;
    private static final Integer DEFAULT_INTEGER_VALUE = 0;

    // 普通字段
    private static final Short COMM_FIELD = 1;


    @Override
    public void batchInsert(FieldAddVO fieldAddVO) throws Exception {
        if (null == fieldAddVO || null == fieldAddVO.getResourceId()) {
            throw new Exception("请选择要配置的菜单");
        }
        if (CollectionUtils.isEmpty(fieldAddVO.getResourceFieldList())) {
            throw new Exception("数据不能为空");
        }
        // 删除后重新插入
        listToolResourceFieldMapper.deleteByResourceId(fieldAddVO.getResourceId());

        for (ListToolResourceField field : fieldAddVO.getResourceFieldList()) {
            if (StringUtils.isBlank(field.getShowName())) {
                throw new Exception("字段" + field.getFieldName() + "的显示名不能为空");
            }
            ListToolResourceField field4DB = new ListToolResourceField();
            BeanUtils.copyProperties(field, field4DB);
            field4DB.setResourceId(fieldAddVO.getResourceId());
            // 默认值
            if (null == field.getPrimarykeyFlag()) {
                field4DB.setPrimarykeyFlag(DEFAULT_SHORT_VALUE);
            }
            if (null == field.getTargetType()) {
                field4DB.setTargetType(DEFAULT_SHORT_VALUE);
            }
            if (null == field.getSearchType()) {
                field4DB.setSearchType(DEFAULT_INTEGER_VALUE);
            }
            if (null == field.getSortType()) {
                field4DB.setSortType(DEFAULT_SHORT_VALUE);
            }
            if (null == field.getFieldType()) {
                field4DB.setFieldType(COMM_FIELD);
            }
            if (null == field.getIsDate()) {
                field4DB.setIsDate(DEFAULT_SHORT_VALUE);
            }
            listToolResourceFieldMapper.insert(field4DB);
        }
    }

}

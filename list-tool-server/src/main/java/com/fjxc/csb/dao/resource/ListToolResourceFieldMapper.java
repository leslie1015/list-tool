package com.fjxc.csb.dao.resource;

import com.fjxc.csb.domain.resource.ListToolResourceField;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ListToolResourceFieldMapper {

    List<ListToolResourceField> listByResourceId(@Param("resourceId")Integer resourceId);

    /**
     * 取出需要替换下拉参数的字段
     *
     * @param resourceId
     * @return
     */
    List<ListToolResourceField> listNeedResetParamField(@Param("resourceId")Integer resourceId);

    Integer deleteByResourceId(@Param("resourceId")Integer resourceId);

    Integer insert(ListToolResourceField field);

}

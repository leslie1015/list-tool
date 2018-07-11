package com.fjxc.csb.dao.resource;

import com.fjxc.csb.domain.resource.ListToolActionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangyong
 */
@Mapper
public interface ListToolActionInfoMapper {

    /**
     * 获取列表操作按钮信息
     *
     * @param resourceId
     * @return
     */
    List<ListToolActionInfo> listByResourceId(@Param("resourceId") Integer resourceId);
}

package com.fjxc.csb.dao.resource;

import com.fjxc.csb.domain.resource.ListToolQueue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ListToolQueueMapper {

    /**
     * 获取菜单队列信息
     *
     * @param queueId
     * @return
     */
    ListToolQueue getByQueueId(@Param("queueId") Integer queueId);

    /**
     * 获取子菜单队列信息
     *
     * @param parentQueueId
     * @return
     */
    List<ListToolQueue> listByParentQueueId(@Param("parentQueueId") Integer parentQueueId);

    /**
     * 根据应用类型获取菜单信息
     *
     * @param appType
     * @return
     */
    List<ListToolQueue> listQueuesByAppType(@Param("appType") Integer appType);


    List<ListToolQueue> listMenusByType(@Param("appType") Integer appType, @Param("resourceType") String resourceType);


    Integer insert(ListToolQueue queue);

    Integer getMaxQueueId();

    Integer update(ListToolQueue queue);

    Integer countByResourceId(Integer resourceId);

    List<ListToolQueue> list(ListToolQueue queue);

}

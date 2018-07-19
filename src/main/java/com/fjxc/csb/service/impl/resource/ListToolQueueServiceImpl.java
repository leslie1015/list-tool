package com.fjxc.csb.service.impl.resource;

import com.fjxc.csb.constants.SystemConstants;
import com.fjxc.csb.dao.resource.ListToolQueueMapper;
import com.fjxc.csb.domain.resource.ListToolQueue;
import com.fjxc.csb.service.resource.ListToolQueueService;
import com.fjxc.csb.util.RedisUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangyong
 */
@Component
public class ListToolQueueServiceImpl implements ListToolQueueService {

    @Autowired
    private ListToolQueueMapper listToolQueueMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ListToolQueue getByQueueId(Integer queueId) {
        return listToolQueueMapper.getByQueueId(queueId);
    }

    @Override
    public List<ListToolQueue> listByParentQueueId(Integer parentQueueId) {
        return listToolQueueMapper.listByParentQueueId(parentQueueId);
    }

    @Override
    public String getMenuJson(Integer appType) {

        String redisKey = SystemConstants.REDIS_KEY_PREFIX_MENU + appType;
        if (redisUtils.exists(redisKey)) {
            return (String) redisUtils.get(redisKey);
        }
        List<ListToolQueue> queues = listToolQueueMapper.listQueuesByAppType(appType);
        String menuJson = JSONArray.fromObject(buildMenuList(queues)).toString();
        redisUtils.set(redisKey, menuJson, SystemConstants.REDIS_EXPIRED_TIME);
        return menuJson;
    }

    /**
     * 构造菜单嵌套的数据结构，理论上支持无限层次的菜单
     *
     * @param queues
     * @return
     */
    private List<ListToolQueue> buildMenuList(List<ListToolQueue> queues) {
        List<ListToolQueue> result = new ArrayList<>();
        for(ListToolQueue parent : queues) {
            List<ListToolQueue> childs = new ArrayList<>();
            for(ListToolQueue child : queues) {
                if (child.getParentQueueId().equals(parent.getQueueId())) {
                    childs.add(child);
                }
            }
            // 根据OrderNum给子菜单排序
            sort(childs);
            parent.setChildQueues(childs);
            if (0 == parent.getParentQueueId()) {
                result.add(parent);
            }
        }
        // 根据OrderNum给根菜单排序
        sort(result);
        return result;
    }

    /**
     * 按照字段orderNum排序子菜单
     *
     * @param childs
     */
    private void sort(List<ListToolQueue> childs) {
        Collections.sort(childs, (c1, c2) -> {
            if(c1.getOrderNum() > c2.getOrderNum()){
                return 1;
            }
            if(c1.getOrderNum().equals(c2.getOrderNum())){
                return 0;
            }
            return -1;
        });
    }
}

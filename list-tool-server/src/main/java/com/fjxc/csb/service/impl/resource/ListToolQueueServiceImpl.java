package com.fjxc.csb.service.impl.resource;

import com.fjxc.csb.constants.SystemConstants;
import com.fjxc.csb.dao.resource.ListToolQueueMapper;
import com.fjxc.csb.domain.SearchCommonVO;
import com.fjxc.csb.domain.resource.ListToolQueue;
import com.fjxc.csb.service.resource.ListToolQueueService;
import com.fjxc.csb.util.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    public List<ListToolQueue> getMenuJson(Integer appType) {

        String redisKey = SystemConstants.REDIS_KEY_PREFIX_MENU + appType;
        if (redisUtils.exists(redisKey)) {
            return (List<ListToolQueue>) redisUtils.get(redisKey);
        }
        List<ListToolQueue> queues = listToolQueueMapper.listQueuesByAppType(appType);

        queues = buildMenuList(queues);

//        String menuJson = JSONArray.fromObject().toString();
        redisUtils.set(redisKey, queues, SystemConstants.REDIS_EXPIRED_TIME);
        return queues;
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

    @Override
    public PageInfo list(SearchCommonVO<ListToolQueue> condition) {
        ListToolQueue queueVO = condition.getFilters();
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        PageHelper.orderBy("add_time desc");
        List<ListToolQueue> queueList = listToolQueueMapper.list(queueVO);
        return new PageInfo(queueList);
    }

    @Override
    public List<ListToolQueue> listMenusByType(Integer appType, String resourceType) {
        return listToolQueueMapper.listMenusByType(appType, resourceType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(ListToolQueue queue) throws Exception {
        if (null == queue.getQueueId()) {
            insert(queue);
            return ;
        }
        update(queue);
    }

    private synchronized void insert(ListToolQueue queue) throws Exception {

        validParam(queue);

        ListToolQueue queue4DB = new ListToolQueue();
        BeanUtils.copyProperties(queue, queue4DB);
        Integer maxQueueId = listToolQueueMapper.getMaxQueueId();
        queue4DB.setQueueId(maxQueueId + 2);
        if ("link".equals(queue.getResourceType())) {
            queue4DB.setResourceUrl("/" + queue.getResourceId());
        }
        queue4DB.setAppType(1);
        queue4DB.setResourceLevel(getResourceLevel(queue4DB.getParentQueueId()));
        if (null == queue.getOrderNum()) {
            queue4DB.setOrderNum(0);
        }
        queue4DB.setAddNo("000001");
        queue4DB.setAddName("admin");
        queue4DB.setAddTime(new Date());
        listToolQueueMapper.insert(queue4DB);
    }

    private void validParam(ListToolQueue queue) throws Exception {
        if (StringUtils.isBlank(queue.getResourceName())) {
            throw new Exception("菜单名不能为空");
        }
        if (StringUtils.isBlank(queue.getResourceType())) {
            throw new Exception("菜单类型不能为空");
        }
        if (null == queue.getResourceId()) {
            throw new Exception("资源ID不能为空");
        }
        if (listToolQueueMapper.countByResourceId(queue.getResourceId()) > 0) {
            throw new Exception("该资源ID已被占用，请检查");
        }
    }

    private void update(ListToolQueue queue) throws Exception {
        validParam(queue);
        ListToolQueue queueDB = listToolQueueMapper.getByQueueId(queue.getQueueId());
        if (null == queueDB) {
            throw new Exception("无此队列信息");
        }
        queueDB.setResourceName(queue.getResourceName());
        queueDB.setParentQueueId(queue.getParentQueueId());
        queueDB.setResourceType(queue.getResourceType());
        queueDB.setResourceId(queue.getResourceId());
        if ("link".equals(queue.getResourceType())) {
            queueDB.setResourceUrl("/" + queue.getResourceId());
        } else {
            queueDB.setResourceUrl("");
        }
        queueDB.setResourceLevel(getResourceLevel(queueDB.getParentQueueId()));
        queueDB.setOrderNum(queue.getOrderNum());
        queueDB.setActiveFlag(queue.getActiveFlag());
        listToolQueueMapper.update(queueDB);
    }

    /**
     * 获取当前菜单级别，方便前端做样式控制，如越往内层的菜单，字体越小
     * @param parentQueueId
     * @return
     */
    private Integer getResourceLevel(Integer parentQueueId) {
        if (null == parentQueueId || parentQueueId.equals(0)) {
            return 1;
        }
        ListToolQueue parentQueue = listToolQueueMapper.getByQueueId(parentQueueId);
        return parentQueue.getResourceLevel() + 1;
    }
}

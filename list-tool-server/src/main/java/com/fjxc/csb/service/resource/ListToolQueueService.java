package com.fjxc.csb.service.resource;

import com.fjxc.csb.domain.SearchCommonVO;
import com.fjxc.csb.domain.resource.ListToolQueue;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyong
 * @date 2018/6/25
 */
@Service
public interface ListToolQueueService {

    ListToolQueue getByQueueId(Integer queueId);

    List<ListToolQueue> listByParentQueueId(Integer parentQueueId);

    List<ListToolQueue> getMenuJson(Integer appType);

    List<ListToolQueue> listMenusByType(Integer appType, String resourceType);

    void saveOrUpdate(ListToolQueue queue) throws Exception;

    PageInfo list(SearchCommonVO<ListToolQueue> condition);
}

package com.fjxc.csb.service.resource;

import com.fjxc.csb.domain.resource.ListToolQueue;
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

    String getMenuJson(Integer appType);
}

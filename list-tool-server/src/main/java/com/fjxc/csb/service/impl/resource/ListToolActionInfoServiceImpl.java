package com.fjxc.csb.service.impl.resource;

import com.fjxc.csb.constants.SystemConstants;
import com.fjxc.csb.dao.resource.ListToolActionInfoMapper;
import com.fjxc.csb.domain.resource.ListToolActionInfo;
import com.fjxc.csb.service.resource.ListToolActionInfoService;
import com.fjxc.csb.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangyong
 */
@Component
public class ListToolActionInfoServiceImpl implements ListToolActionInfoService {

    @Autowired
    private ListToolActionInfoMapper listToolActionInfoMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<ListToolActionInfo> listByResourceId(Integer resourceId) {

        String redisKey = SystemConstants.REDIS_KEY_PREFIX_FIELD_ACTION+ resourceId;
        if (redisUtils.exists(redisKey)) {
            return (List<ListToolActionInfo>) redisUtils.get(redisKey);
        }

        List<ListToolActionInfo> actionInfos = listToolActionInfoMapper.listByResourceId(resourceId);
        redisUtils.set(redisKey, actionInfos, SystemConstants.REDIS_EXPIRED_TIME);
        return actionInfos;
    }
}

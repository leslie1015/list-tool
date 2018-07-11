package com.fjxc.csb.service.impl.resource;

import com.fjxc.csb.dao.resource.ListToolActionInfoMapper;
import com.fjxc.csb.domain.resource.ListToolActionInfo;
import com.fjxc.csb.service.resource.ListToolActionInfoService;
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

    @Override
    public List<ListToolActionInfo> listByResourceId(Integer resourceId) {
        return listToolActionInfoMapper.listByResourceId(resourceId);
    }
}

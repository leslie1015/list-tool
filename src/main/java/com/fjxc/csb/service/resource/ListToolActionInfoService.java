package com.fjxc.csb.service.resource;

import com.fjxc.csb.domain.resource.ListToolActionInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyong
 */
@Service
public interface ListToolActionInfoService {

    List<ListToolActionInfo> listByResourceId(@Param("resourceId") Integer resourceId);
}

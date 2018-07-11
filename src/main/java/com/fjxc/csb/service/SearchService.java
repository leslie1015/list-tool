package com.fjxc.csb.service;

import com.fjxc.csb.domain.PageResult;
import com.fjxc.csb.domain.resource.SearchVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface SearchService {

    PageResult<HashMap<String,Object>> list(String resourceId) throws Exception;

    PageResult<HashMap<String,Object>> search(SearchVO searchVO) throws Exception;

    String config(String resourceId) throws Exception;
}

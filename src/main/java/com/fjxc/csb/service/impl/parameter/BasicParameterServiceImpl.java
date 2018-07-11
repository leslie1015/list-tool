package com.fjxc.csb.service.impl.parameter;

import com.fjxc.csb.dao.parameter.BasicParameterMapper;
import com.fjxc.csb.domain.parameter.SimpleParam;
import com.fjxc.csb.service.parameter.BasicParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangyong
 */
@Component
public class BasicParameterServiceImpl implements BasicParameterService {

    @Autowired
    private BasicParameterMapper basicParameterMapper;

    @Override
    public List<SimpleParam> listKeyValByGroupKey(String groupKey) {
        return basicParameterMapper.listKeyValByGroupKey(groupKey);
    }

}

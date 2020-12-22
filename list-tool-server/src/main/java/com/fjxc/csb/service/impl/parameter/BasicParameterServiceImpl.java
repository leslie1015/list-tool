package com.fjxc.csb.service.impl.parameter;

import com.fjxc.csb.constants.SystemConstants;
import com.fjxc.csb.dao.parameter.BasicParameterMapper;
import com.fjxc.csb.domain.parameter.SimpleParam;
import com.fjxc.csb.service.parameter.BasicParameterService;
import com.fjxc.csb.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<SimpleParam> listKeyValByGroupKey(String groupKey) {

        String redisKey = SystemConstants.REDIS_KEY_PREFIX_BASIC_PARAM + groupKey;
        if (redisUtils.exists(redisKey)) {
            return (List<SimpleParam>) redisUtils.get(redisKey);
        }
        List<SimpleParam> params = basicParameterMapper.listKeyValByGroupKey(groupKey);
        redisUtils.set(redisKey, params, SystemConstants.REDIS_EXPIRED_TIME);
        return params;
    }


    // String value = basicParameterMapper.getValueByKey(field.getParamGroupKey(), paramKey);

    @Override
    public String getParamByKey(String groupKey, String key) {

        String redisKey = SystemConstants.REDIS_KEY_PREFIX_BASIC_PARAM + groupKey + "_" + key;

        if (redisUtils.exists(redisKey)) {
            return (String)redisUtils.get(redisKey);
        }
        String paramValue = basicParameterMapper.getValueByKey(groupKey, key);
        redisUtils.set(redisKey, paramValue, SystemConstants.REDIS_EXPIRED_TIME);
        return paramValue;
    }
}

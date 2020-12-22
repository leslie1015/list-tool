package com.fjxc.csb.service.parameter;

import com.fjxc.csb.domain.parameter.SimpleParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyong
 */
@Service
public interface BasicParameterService {

    List<SimpleParam> listKeyValByGroupKey(String groupKey);

    String getParamByKey(String groupKey, String key);
}

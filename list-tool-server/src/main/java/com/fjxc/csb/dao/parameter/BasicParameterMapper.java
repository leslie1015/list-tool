package com.fjxc.csb.dao.parameter;

import com.fjxc.csb.domain.parameter.BasicParameter;
import com.fjxc.csb.domain.parameter.SimpleParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangyong
 */
@Mapper
public interface BasicParameterMapper {

    List<BasicParameter> listByGroupKey(@Param("groupKey") String groupKey);

    List<SimpleParam> listKeyValByGroupKey(@Param("groupKey") String groupKey);

    String getValueByKey(@Param("groupKey")String groupKey, @Param("key")String key);
}

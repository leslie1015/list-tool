package com.fjxc.csb.interceptor;

import com.fjxc.csb.constants.SystemConstants;
import com.fjxc.csb.domain.enumerate.SearchParamEnum;
import com.fjxc.csb.domain.resource.SearchParam;
import com.fjxc.csb.resultfilter.ResultFilterService;
import com.fjxc.csb.util.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 此拦截器参考了PageHelper5的设计，拦截的是Executor的query方法，另一种是拦截StatementHandler.prepare，查看mybatis的源码可以发现，
 * StatementHandler.prepare逻辑上在Executor.query后面执行，配置插件顺序对执行顺序无效，如果这里拦截StatementHandler，必然导致先分页再拼接查询条件，
 * 我们想要的是先拼查询条件再分页，所以与PageHelper5一样，都拦截Executor的query，通过配置拦截器的顺序来影响拦截器执行顺序：SqlInterceptor=》PageHelper5
 *
 * @author wangyong
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class SqlInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];

        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor = (Executor) invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;
        //由于逻辑关系，只会进入一次
        //拦截Executor的query方法，query被重载了，有4个和6个参数，其中4个参数的query调用了6个参数的query
        //4 个参数时
        if (args.length == 4) {
            boundSql = ms.getBoundSql(parameter);
            // 只针对列表数据查询的sql过滤重新拼接
            if (isListSearch(ms.getId())) {
                boundSql = rebuildBoundSql(boundSql, ms);
            }
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } //6 个参数时
        else {
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }
        List<HashMap> result = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
        // 如果不是列表查询，直接返回查询结果
        if (!isListSearch(ms.getId())) {
            return result;
        }
        // 以下过滤只针对当前页面数据，不会全量，因此效率上面也有保证
        ResultFilterService resultFilterService;
        try {
            resultFilterService = (ResultFilterService) SpringContextUtils.getBean(ms.getId());
        } catch (NoSuchBeanDefinitionException e) {
            return result;
        }
        // 支持对结果集进行过滤或者追加新字段再输出，策略模式实现，Demo参照DemoResultFilterService
        return resultFilterService.resultFilter(result);
    }

    /**
     * 根据参数重新构造BoundSql对象
     *
     * @param boundSql
     * @param ms
     * @return
     */
    private BoundSql rebuildBoundSql(BoundSql boundSql, MappedStatement ms) {

        Object paramObj = boundSql.getParameterObject();
        // 这里会进来两次，一次针对列表sql，一次针对分页count总数的sql，此时参数为null
        if (null == paramObj) {
            return new BoundSql(ms.getConfiguration(), boundSql.getSql(), boundSql.getParameterMappings(), boundSql.getParameterObject());
        }
        // 参数相关
//        DefaultSqlSession.StrictMap map = (DefaultSqlSession.StrictMap)paramObj;
        HashMap map = (HashMap) paramObj;
        // 查询的相关参数不为空，则重新拼接sql
        String sql = boundSql.getSql();
        if (null != map && map.size() != 0) {
            sql = buildSqlBySearchCondition(boundSql.getSql(), (List<SearchParam>) map.get("list"));
        }
        //拼接好的重新生成BoundSql对象
        return new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
    }

    /**
     * 传入执行方法的路径，判断是否为ListToolMapper，只对该类中的方法进行sql过滤重新拼接
     *
     * @param mapperPath
     * @return
     */
    private boolean isListSearch(String mapperPath) {
        Pattern pat = Pattern.compile("^" + SystemConstants.MAPPPER_PATH);
        Matcher matcher = pat.matcher(mapperPath);
        return matcher.find();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 拼接sql
     *
     * @param oldsql    原始sql
     * @param paramList 查询参数相关数据
     * @return
     */
    public String buildSqlBySearchCondition(String oldsql, List<SearchParam> paramList) {
        if (CollectionUtils.isEmpty(paramList)) {
            return oldsql;
        }
        StringBuilder sb = new StringBuilder(" select * from (").append(oldsql).append(") as aa where 1 = 1 ");
        for (SearchParam param : paramList) {
            if (null == param || StringUtils.isBlank(param.getValue()) || SearchParamEnum.NOT_SEARCH.getType().equals(param.getType())) {
                continue;
            }
            if (SearchParamEnum.FUZZY.getType().equals(param.getType())) {
                sb.append(" and ").append(param.getFieldName()).append(" like '%").append(param.getValue().trim()).append("%'");
            } else if (SearchParamEnum.EXACT.getType().equals(param.getType())) {
                sb.append(" and ").append(param.getFieldName()).append(" = ").append("'").append(param.getValue().trim()).append("'");
            } else if (SearchParamEnum.DROP_DOWN.getType().equals(param.getType())) {
                sb.append(" and ").append(param.getFieldName()).append(" = ").append(param.getValue());
            } else if (SearchParamEnum.DATE_INTERVAL.getType().equals(param.getType())) {
                String lessOrMoreCode = SearchParam.MORE_TYPE.equals(param.getMoreOrLessType()) ? " >= " : " <= ";
                String exaceTime = SearchParam.MORE_TYPE.equals(param.getMoreOrLessType()) ? " 00:00:00" : " 23:59:59";
                //ORACLE写法
//                sb.append(" and ").append(param.getFieldName()).append(lessOrMoreCode).append(" to_date(").append("'")
//                        .append(param.getValue().trim()).append(exaceTime).append("'").append(", 'yyyy-mm-dd hh24:mi:ss')");

                //MYSQL写法
                sb.append(" and ").append(param.getFieldName()).append(lessOrMoreCode).append(" str_to_date(").append("'")
                        .append(param.getValue().trim()).append(exaceTime).append("'").append(", '%Y-%m-%d %H:%i:%s')");
            } else if (SearchParamEnum.NUMBER_INTERVAL.getType().equals(param.getType())) {
                String lessOrMoreCode = SearchParam.MORE_TYPE.equals(param.getMoreOrLessType()) ? " >= " : " <= ";
                sb.append(" and ").append(param.getFieldName()).append(lessOrMoreCode).append(param.getValue());

            } else if (SearchParamEnum.DROP_DOWN_CHOICE.getType().equals(param.getType())) {
                sb.append(" and ").append(param.getFieldName()).append(" in (").append(param.getValue()).append(")");
            } else {
                // 若有待续
            }
        }
        return sb.toString();
    }
}

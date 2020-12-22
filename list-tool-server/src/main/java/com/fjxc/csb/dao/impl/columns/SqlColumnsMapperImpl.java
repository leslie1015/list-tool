package com.fjxc.csb.dao.impl.columns;


import com.fjxc.csb.dao.columns.SqlColumnsMapper;
import com.fjxc.csb.util.SqlParseUtils;
import net.sf.jsqlparser.JSQLParserException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangyong
 */
@Repository
public class SqlColumnsMapperImpl implements SqlColumnsMapper {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<String> getSqlColumns(String resourceId) {
        SqlSession sqlSession = null;
        List<String> columns = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            // 获取resourceId对应的sql
            String sql = sqlSession.getConfiguration().getMappedStatement("com.fjxc.csb.dao.ListToolMapper." + resourceId)
                    .getBoundSql(null).getSql();
            // 解析sql
            columns = SqlParseUtils.getAllColumnsBySql(sql);
        } catch (JSQLParserException e) {
            
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }
        return columns;
    }
}

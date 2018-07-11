package com.fjxc.csb.dao.impl;

import com.fjxc.csb.dao.ListToolMapper;
import com.fjxc.csb.domain.resource.SearchParam;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ListToolMapperImpl implements ListToolMapper {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 构造方法注入
     *
     * @param
     */
//    public ListToolMapperImpl(SqlSessionFactory sqlSessionFactory) {
//        this.sqlSessionFactory = sqlSessionFactory;
//    }

    @Override
    public List<HashMap<String, Object>> executeSearch(String resourceId) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<HashMap<String, Object>> list = sqlSession.selectList(resourceId);

        //释放资源
        sqlSession.close();
        return list;
    }



    @Override
    public List<HashMap<String, Object>> executeSearch(String resourceId, List<SearchParam> params) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<HashMap<String, Object>> list = sqlSession.selectList(resourceId, params);
        //释放资源
        sqlSession.close();
        return list;
    }
}

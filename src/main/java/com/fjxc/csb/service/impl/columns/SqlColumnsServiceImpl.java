package com.fjxc.csb.service.impl.columns;

import com.fjxc.csb.dao.columns.SqlColumnsMapper;
import com.fjxc.csb.service.columns.SqlColumnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SqlColumnsServiceImpl implements SqlColumnsService {

    @Autowired
    private SqlColumnsMapper sqlColumnsMapper;

    @Override
    public List<String> getSqlColumns(String resourceId) {
        return sqlColumnsMapper.getSqlColumns(resourceId);
    }
}

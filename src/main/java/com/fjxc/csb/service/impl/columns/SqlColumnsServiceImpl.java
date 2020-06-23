package com.fjxc.csb.service.impl.columns;

import com.fjxc.csb.dao.columns.SqlColumnsMapper;
import com.fjxc.csb.service.columns.SqlColumnsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class SqlColumnsServiceImpl implements SqlColumnsService {

    @Autowired
    private SqlColumnsMapper sqlColumnsMapper;

    /**
     * 统一转大写 ，方便前端比较
     * @param resourceId
     * @return
     */
    @Override
    public List<String> getSqlColumns(String resourceId) {
        List<String> colNames = sqlColumnsMapper.getSqlColumns(resourceId);
        if (CollectionUtils.isEmpty(colNames)) {
            return colNames;
        }
        List<String> upperColNames = new ArrayList<>();
        for (String colName: colNames) {
            upperColNames.add(toUpperCase(colName));
        }
        return upperColNames;
    }


    private String toUpperCase(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return str.toUpperCase();
    }
}

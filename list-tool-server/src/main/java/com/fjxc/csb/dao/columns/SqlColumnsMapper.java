package com.fjxc.csb.dao.columns;

import java.util.List;

public interface SqlColumnsMapper {

    /**
     * 获取要查询的sql的列数据
     *
     * @param resourceId
     * @return
     */
    List<String> getSqlColumns(String resourceId);

}

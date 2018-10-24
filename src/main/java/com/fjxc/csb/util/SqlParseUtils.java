package com.fjxc.csb.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SubSelect;
import org.apache.commons.lang.StringUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * sql解析。在配置的时候，需要根据resourceId获取到列表sql的字段，然后去配置，从mybatis的config中获取到列表sql，
 * 然后用JSQLParse解析，需要注意的是，解析字段的时候，JSQLParse默认只解析最外层的字段，需要循环或者递归判断内层是否还有字段
 * 需要解析，将字段放入set中去重后返回，自动带入到配置页面上，方便配置
 *
 * @author wangyong
 */
public class SqlParseUtils {


    /**
     * 获取sql中查询的字段
     *
     * @param sql
     * @return
     * @throws JSQLParserException
     */
    public static List<String> getAllColumnsBySql(String sql) throws JSQLParserException {
        CCJSqlParserManager parser = new CCJSqlParserManager();
        StringReader reader = new StringReader(sql);
        Statement stmt = parser.parse(reader);
        List<String> allColumns = null;
        if (stmt instanceof Select) {
            Select selectStatement = (Select) stmt;
            PlainSelect selectBody = (PlainSelect) selectStatement.getSelectBody();
            allColumns = getRealColumns(selectBody);
        }
        return allColumns;
    }


    /**
     * 多层嵌套的情况下，获取最里层的字段信息
     *
     * @param select
     * @return
     */
    private static List<String> getRealColumns(PlainSelect select) {
        PlainSelect plainSelect = select;

        Set<String> cols = new HashSet<>();
        //字段中是否包含*，如果包含，需要到下一级的from中继续找字段，如果不包含，则直接返回该层级的所有字段
        boolean containAll = true;
        while (containAll) {
            StringBuilder sb = new StringBuilder();
            for (SelectItem item : plainSelect.getSelectItems()) {
                sb.append(item.toString());
                if (!item.toString().contains("*")) {
                    cols.add(dealStr2Column(item.toString()));
                }
            }
            containAll = sb.toString().contains("*");
            if (containAll) {
                SubSelect fromItem = (SubSelect) plainSelect.getFromItem();
                plainSelect = (PlainSelect) fromItem.getSelectBody();
            }
        }
        return new ArrayList<>(cols);
    }


    /**
     * 处理包含表别名的字段
     *
     * @param tableColumn
     * @return
     */
    private static String dealStr2Column(String tableColumn) {
        if (StringUtils.isBlank(tableColumn)) {
            return StringUtils.EMPTY;
        }
        // 将取到的字段以点和空格split，主要是如a.xxx和b.yyy as kkk,这种情况最终取到的是xxx和kkk，是想要的结果
        String[] str = tableColumn.split("\\.| ");
        if (str.length == 0) {
            return tableColumn;
        }
        return str[str.length - 1];
    }
}

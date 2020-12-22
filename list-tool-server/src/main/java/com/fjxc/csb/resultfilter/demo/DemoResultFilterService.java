package com.fjxc.csb.resultfilter.demo;

import com.fjxc.csb.constants.SystemConstants;
import com.fjxc.csb.resultfilter.ResultFilterService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * demo，如果要对结果集进行过滤，可以参照此写个service，资源的id与注解中对应，一般可以适用于
 * sql中包含子查询，且子查询的表数据量过大，导致sql查询很慢，可以在sql中只查询表的主键，然后用
 * 主键在此查询出结果放入结果集返回
 */
@Service(SystemConstants.MAPPPER_PATH + "690001")
public class DemoResultFilterService extends ResultFilterService {
    @Override
    public List<HashMap> resultFilter(List<HashMap> result) {
//        List<HashMap> newResult = result;
        for (HashMap map : result) {
            Iterator entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                if ("OUT_VOUCHER".equals(entry.getKey())) {
                    map.put("OUT_VOUCHER", entry.getValue() + "66666666666");
                }
            }
        }
        return result;
    }
}

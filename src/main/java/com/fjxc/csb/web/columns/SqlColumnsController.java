package com.fjxc.csb.web.columns;

import com.fjxc.csb.service.columns.SqlColumnsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/columns")
public class SqlColumnsController {

    @Autowired
    private SqlColumnsService sqlColumnsService;

    @RequestMapping(value = "/getColumns/{resourceId}", method = RequestMethod.GET)
    @ApiOperation(value = "获取列表sql字段")
    public List<String> list(@PathVariable String resourceId) throws Exception {

        return sqlColumnsService.getSqlColumns(resourceId);
    }
}

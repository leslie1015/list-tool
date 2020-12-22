package com.fjxc.csb.web.columns;

import com.fjxc.csb.domain.ActionResult;
import com.fjxc.csb.domain.enumerate.ResultCode;
import com.fjxc.csb.service.columns.SqlColumnsService;
import com.fjxc.csb.web.BasicController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/columns")
public class SqlColumnsController extends BasicController {

    @Autowired
    private SqlColumnsService sqlColumnsService;

    @RequestMapping(value = "/getColumns/{resourceId}", method = RequestMethod.GET)
    @ApiOperation(value = "获取列表sql字段")
    public ActionResult list(@PathVariable String resourceId) throws Exception {
        try {
            return actionResult(sqlColumnsService.getSqlColumns(resourceId));
        } catch (IllegalArgumentException e) {
            return actionResult(ResultCode.FAILURE, "未写sql脚本");
        } catch (Exception e) {
            return actionResult(ResultCode.FAILURE, e.getMessage());
        }
    }
}

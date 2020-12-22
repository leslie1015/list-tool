package com.fjxc.csb.web.resource;

import com.alibaba.fastjson.JSON;
import com.fjxc.csb.domain.ActionResult;
import com.fjxc.csb.domain.UserThreadLocal;
import com.fjxc.csb.domain.enumerate.ResultCode;
import com.fjxc.csb.domain.resource.FieldAddVO;
import com.fjxc.csb.domain.resource.ListToolResourceField;
import com.fjxc.csb.service.resource.ListToolResourceFieldService;
import com.fjxc.csb.web.BasicController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listResource")
public class ListToolResourceFieldController extends BasicController{

    @Autowired
    private ListToolResourceFieldService listToolResourceFieldService;

    @RequestMapping(value = "/listField/{resourceId}" ,method = RequestMethod.GET)
    @ApiOperation(value = "列表查询")
    public ActionResult list(@PathVariable Integer resourceId) {

        try {
            return actionResult(listToolResourceFieldService.listByResourceId(resourceId));
        } catch (Exception e) {
            return actionResult(ResultCode.FAILURE, e.getMessage());
        }
    }

    @RequestMapping(value = "/batchInsert" ,method = RequestMethod.POST)
    @ApiOperation(value = "插入数据")
    public ActionResult batchInsert(@RequestBody FieldAddVO fieldAddVO) {
        try {
            listToolResourceFieldService.batchInsert(fieldAddVO);
            return actionResult(ResultCode.SUCCESS);
        } catch (Exception e) {
            return actionResult(ResultCode.FAILURE, e.getMessage());
        }
    }
}

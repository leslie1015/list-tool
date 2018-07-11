package com.fjxc.csb.web.resource;

import com.alibaba.fastjson.JSON;
import com.fjxc.csb.service.resource.ListToolResourceFieldService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listResource")
public class ListToolResourceFieldController {

    @Autowired
    private ListToolResourceFieldService listToolResourceFieldService;

    @RequestMapping(value = "/listField/{resourceId}" ,method = RequestMethod.GET)
    @ApiOperation(value = "列表查询")
    public String list(@PathVariable Integer resourceId)throws Exception {
        return JSON.toJSONString(listToolResourceFieldService.listByResourceId(resourceId));
    }
}

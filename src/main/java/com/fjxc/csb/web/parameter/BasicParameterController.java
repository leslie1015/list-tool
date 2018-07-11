package com.fjxc.csb.web.parameter;

import com.alibaba.fastjson.JSON;
import com.fjxc.csb.service.parameter.BasicParameterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parameter")
public class BasicParameterController {

    @Autowired
    private BasicParameterService basicParameterService;


    @RequestMapping(value = "/list/{groupKey}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取参数信息")
    public String list(@PathVariable String groupKey) throws Exception {
        return JSON.toJSONString(basicParameterService.listKeyValByGroupKey(groupKey));
    }

}

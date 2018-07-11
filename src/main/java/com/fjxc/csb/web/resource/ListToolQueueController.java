package com.fjxc.csb.web.resource;

import com.alibaba.fastjson.JSON;
import com.fjxc.csb.service.resource.ListToolQueueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listQueue")
public class ListToolQueueController {

    @Autowired
    private ListToolQueueService listToolQueueService;

    @RequestMapping(value = "/getByQueueId/{queueId}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取队列信息")
    public String listByQueueId(@PathVariable Integer queueId)throws Exception {
        return JSON.toJSONString(listToolQueueService.getByQueueId(queueId) );
    }


    @RequestMapping(value = "/listByParentQueueId/{parentQueueId}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取子队列信息")
    public String listByParentQueueId(@PathVariable Integer parentQueueId) throws Exception {
        return JSON.toJSONString(listToolQueueService.listByParentQueueId(parentQueueId) );
    }

    @RequestMapping(value = "/getAppMenu/{appType}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取菜单信息")
    //允许跨域请求的注解
//    @CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET}, origins="*")
    public String getMenuData(@PathVariable Integer appType) throws Exception{
        return listToolQueueService.getMenuJson(appType);
    }
}

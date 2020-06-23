package com.fjxc.csb.web.resource;

import com.alibaba.fastjson.JSON;
import com.fjxc.csb.domain.ActionResult;
import com.fjxc.csb.domain.SearchCommonVO;
import com.fjxc.csb.domain.enumerate.ResultCode;
import com.fjxc.csb.domain.resource.ListToolQueue;
import com.fjxc.csb.service.resource.ListToolQueueService;
import com.fjxc.csb.web.BasicController;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listQueue")
public class ListToolQueueController extends BasicController {

    @Autowired
    private ListToolQueueService listToolQueueService;

    @RequestMapping(value = "/getByQueueId/{queueId}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取队列信息")
    public ActionResult listByQueueId(@PathVariable Integer queueId)throws Exception {
        return actionResult(listToolQueueService.getByQueueId(queueId) );
    }


    @RequestMapping(value = "/listByParentQueueId/{parentQueueId}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取子队列信息")
    public ActionResult listByParentQueueId(@PathVariable Integer parentQueueId) throws Exception {
        return actionResult(listToolQueueService.listByParentQueueId(parentQueueId) );
    }

    @RequestMapping(value = "/getAppMenu/{appType}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取菜单信息")
    public ActionResult<List<ListToolQueue>> getMenuData(@PathVariable Integer appType) throws Exception{
        return actionResult(listToolQueueService.getMenuJson(appType));
    }

    @RequestMapping(value = "/listParentMenus/{appType}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取所有父菜单")
    public ActionResult listParentMenus(@PathVariable Integer appType) throws Exception {
        return actionResult(listToolQueueService.listMenusByType(appType, "button"));
    }

    @RequestMapping(value = "/listResourceMenus/{appType}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取所有资源菜单")
    public ActionResult listResourceMenus(@PathVariable Integer appType) throws Exception {
        return actionResult(listToolQueueService.listMenusByType(appType, "link"));
    }

    @RequestMapping(value = "/listByPage" ,method = RequestMethod.POST)
    @ApiOperation(value = "获取所有资源菜单")
    public ActionResult listByPage(@RequestBody SearchCommonVO<ListToolQueue> condition) throws Exception {
        return actionResult(listToolQueueService.list(condition));
    }



    @RequestMapping(value = "/saveOrUpdate" ,method = RequestMethod.POST)
    @ApiOperation(value = "保存或更新菜单信息")
    public ActionResult saveOrUpdate(@RequestBody ListToolQueue queue) throws Exception {
        try {
            listToolQueueService.saveOrUpdate(queue);
        } catch (Exception e) {
            return new ActionResult(ResultCode.FAILURE, e.getMessage());
        }
        return actionResult(ResultCode.SUCCESS);
    }




}

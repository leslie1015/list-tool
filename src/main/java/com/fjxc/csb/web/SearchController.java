package com.fjxc.csb.web;

import com.fjxc.csb.domain.ActionResult;
import com.fjxc.csb.domain.resource.SearchVO;
import com.fjxc.csb.service.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listTool")
public class SearchController extends BasicController{

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/list/{resourceId}" ,method = RequestMethod.GET)
    @ApiOperation(value = "列表数据显示")
    public ActionResult list(@PathVariable String resourceId)throws Exception {
        return actionResult(searchService.list(resourceId));
    }

    @RequestMapping(value = "/search" ,method = RequestMethod.POST)
    @ApiOperation(value = "列表查询")
    public ActionResult list(@RequestBody SearchVO searchVO)throws Exception {
      return actionResult(searchService.search(searchVO));
    }
}

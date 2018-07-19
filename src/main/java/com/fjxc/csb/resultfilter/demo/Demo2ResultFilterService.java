package com.fjxc.csb.resultfilter.demo;

import com.fjxc.csb.constants.SystemConstants;
import com.fjxc.csb.resultfilter.ResultFilterService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service(SystemConstants.MAPPPER_PATH + "690002")
public class Demo2ResultFilterService extends ResultFilterService {
    @Override
    public List<HashMap> resultFilter(List<HashMap> result) {

        List<HashMap> result2 = result;
        return result2;
    }
}

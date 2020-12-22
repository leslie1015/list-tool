package com.fjxc.csb.service.columns;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SqlColumnsService {

    List<String> getSqlColumns(String resourceId);

}

package com.fjxc.csb.service.resource;


import com.fjxc.csb.domain.resource.FieldAddVO;
import com.fjxc.csb.domain.resource.ListToolResourceField;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListToolResourceFieldService {

    List<ListToolResourceField> listByResourceId(Integer resourceId);

    void batchInsert(FieldAddVO fieldAddVO) throws Exception;

}

package com.fjxc.csb.domain.resource;

import lombok.Data;

import java.util.List;

@Data
public class FieldAddVO {

    private Integer resourceId;

    private List<ListToolResourceField> resourceFieldList;
}

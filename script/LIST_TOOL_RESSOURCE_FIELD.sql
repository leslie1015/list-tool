create table xgbj.LIST_TOOL_RESOURCE_FIELD(
resource_id    number(6) not null,
field_name     varchar2(20) not null,
show_name      varchar2(50) not null,
primarykey_flag number(1) default 0 ,
target_type    number(1),
target_url     varchar2(200),
index_no       number(3),
param_group_key  varchar2(20),
search_type    number(2) default 0,
sort_type      number(1) default 0,
field_type     number(1),
is_date        number(1) default 0,
date_format_type number(1),
)

comment on table xgbj.LIST_TOOL_RESOURCE_FIELD  is  '字段分析表';

COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.resource_id IS '资源ID';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.field_name IS '对应字段';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.show_name IS '显示名';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.primarykey_flag IS '是否主键，0不是，1是';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.target_type IS '链接类型。0: 无，1: 普通链接，2: JS方法';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.target_url IS 'target_type为1，填写url，target_type为2，填写JS方法名';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.index_no IS '字段排序,数字小的排在前面';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.param_group_key IS '对应basic_parameter.param_group_key，配置后自动带出下拉参数。search_type为3、6时不能为空';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.search_type IS '0不搜索，1模糊搜索，2精确搜索，3下拉搜索，4时间区间搜索，5数字区间，6多选搜索';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.sort_type IS '是否排序，0不排序，1排序';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.field_type IS '字段类型。1:普通字段，2：操作按钮';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.is_date IS '字段是否是时间，0不是，1是';
COMMENT ON COLUMN xgbj.LIST_TOOL_RESOURCE_FIELD.date_format_type IS '显示时间格式，1：yyyy-mm-dd，2：yyyy-mm-dd hh:mi:ss';

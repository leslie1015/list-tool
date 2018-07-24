create table xgbj.basic_parameter (
param_group_key   varchar2(50) not null,
param_name        varchar2(100),
param_key         varchar2(50) not null,
param_value       varchar2(100) not null,
lan_code          number(1) default 1,
order_num         number(3) not null,
remark            varchar2(200),
add_no            varchar2(50) not null,
add_name          varchar2(50) not null,
add_time          date not null,
update_no         varchar2(50) not null,
update_name       varchar2(50) not null,
update_time       date not null
)

comment on table xgbj.basic_parameter  is  '参数表';

COMMENT ON COLUMN xgbj.basic_parameter.param_group_key IS '参数组的key';
COMMENT ON COLUMN xgbj.basic_parameter.param_name IS '参数名';
COMMENT ON COLUMN xgbj.basic_parameter.param_key IS '参数key，param_group_key + param_key确定唯一数据';
COMMENT ON COLUMN xgbj.basic_parameter.param_value IS '参数值';
COMMENT ON COLUMN xgbj.basic_parameter.lan_code IS '语言，1中文，2英文';
COMMENT ON COLUMN xgbj.basic_parameter.order_num IS '顺序，按照所填数字排序';
COMMENT ON COLUMN xgbj.basic_parameter.remark IS '备注';
COMMENT ON COLUMN xgbj.basic_parameter.add_no IS '添加人编号';
COMMENT ON COLUMN xgbj.basic_parameter.add_name IS '添加人姓名';
COMMENT ON COLUMN xgbj.basic_parameter.add_time IS '添加时间';
COMMENT ON COLUMN xgbj.basic_parameter.update_no IS '修改人编号';
COMMENT ON COLUMN xgbj.basic_parameter.update_name IS '修改人姓名';
COMMENT ON COLUMN xgbj.basic_parameter.update_time IS '修改时间';
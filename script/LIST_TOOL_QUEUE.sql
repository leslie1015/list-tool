
create table xgbj.LIST_TOOL_QUEUE(
queue_id              number(5) UNIQUE NOT NULL,
resource_id           number(6) NOT NULL,
parent_queue_id       number(6) default 0,
resource_name         varchar2(50) NOT NULL,
resource_type         number(1) NOT NULL,
resource_level        number(2) NOT NULL,
resource_url          varchar(100),
active_flag           number(1) default 1 NOT NULL,
order_num             number(3),
app_type              number(3) NOT NULL,
add_time              date NOT NULL,
add_name              varchar2(50) NOT NULL,
add_no                varchar2(50) NOT NULL )


comment on table xgbj.LIST_TOOL_QUEUE  is  '队列资源表';

COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.queue_id IS '队列ID';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.resource_id IS '资源ID';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.parent_queue_id IS '父级菜单ID, 无父级菜单默认0';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.resource_name IS '资源名称';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.resource_type IS '资源类型：1.按钮，2.资源链接，3.子资源汇总页面链接。为2,3时resource_url不能为空';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.resource_level IS '菜单资源等级 1一级标题，2二级标题，3三级标题，4四级标题';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.resource_url IS '资源链接';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.active_flag IS '0禁用，1启用';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.order_num IS '排序编号，数字小的菜单在上面，只针对同一父菜单下同级别菜单排序';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.app_type IS '所属应用,定义在枚举类AppTypeEnum中';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.add_time IS '添加时间';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.add_no IS '添加人编号';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.add_name IS '添加人';

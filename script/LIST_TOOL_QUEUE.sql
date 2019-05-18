
---------------------------------------------------------------------------------
-------------------------------ORACLE中建表语句------------------------------------
---------------------------------------------------------------------------------
create table xgbj.LIST_TOOL_QUEUE(
queue_id              number(5) UNIQUE NOT NULL,
resource_id           number(6) NOT NULL,
parent_queue_id       number(6) default 0,
resource_name         varchar2(50) NOT NULL,
resource_type         varchar2(20) NOT NULL,
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
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.resource_type IS '资源类型：button 按钮，link 资源链接。为link时resource_url不能为空';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.resource_level IS '菜单资源等级 1一级标题，2二级标题，3三级标题，4四级标题';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.resource_url IS '资源链接';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.active_flag IS '0禁用，1启用';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.order_num IS '排序编号，数字小的菜单在上面，只针对同一父菜单下同级别菜单排序';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.app_type IS '所属应用,定义在枚举类AppTypeEnum中';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.add_time IS '添加时间';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.add_no IS '添加人编号';
COMMENT ON COLUMN xgbj.LIST_TOOL_QUEUE.add_name IS '添加人';

---------------------------------------------------------------------------------
-------------------------------MYSQL中建表语句------------------------------------
---------------------------------------------------------------------------------

create table cb.LIST_TOOL_QUEUE(
queue_id              decimal(5) UNIQUE NOT NULL comment '队列ID',
resource_id           decimal(6) NOT NULL comment '资源ID',
parent_queue_id       decimal(6) default 0 comment '父级菜单ID, 无父级菜单默认0',
resource_name         varchar(50) NOT NULL comment '资源名称',
resource_type         varchar(20) NOT NULL comment '资源类型：button 按钮，link 资源链接。为link时resource_url不能为空',
resource_level        decimal(2) NOT NULL comment '菜单资源等级 1一级标题，2二级标题，3三级标题，4四级标题...',
resource_url          varchar(200) comment '资源链接',
active_flag           decimal(1) default 1 NOT NULL comment '0禁用，1启用',
order_num             decimal(3) comment '排序编号，数字小的菜单在上面，只针对同一父菜单下同级别菜单排序',
app_type              decimal(3) NOT NULL comment '所属应用,定义在枚举类AppTypeEnum中',
add_time              DATETIME NOT NULL comment '添加时间',
add_name              varchar(50) NOT NULL comment '添加人编号',
add_no                varchar(50) NOT NULL comment '添加人'
 )ENGINE=InnoDB  DEFAULT CHARSET=utf8 comment='队列资源表'

--INSERT INTO `cb`.`list_tool_queue` (`queue_id`, `resource_id`, `parent_queue_id`, `resource_name`, `resource_type`, `resource_level`, `resource_url`, `active_flag`, `order_num`, `app_type`, `add_time`, `add_name`, `add_no`) VALUES ('1', '960001', '0', '代理商管理', 'button', '1', NULL, '1', '1', '1', '2019-05-15 11:30:23', 'system', '000001');
--INSERT INTO `cb`.`list_tool_queue` (`queue_id`, `resource_id`, `parent_queue_id`, `resource_name`, `resource_type`, `resource_level`, `resource_url`, `active_flag`, `order_num`, `app_type`, `add_time`, `add_name`, `add_no`) VALUES ('11', '960002', '1', '所有代理商', 'link', '2', 960002, '1', '1', '2', '2019-05-15 11:30:23', 'system', '000001');
--INSERT INTO `cb`.`list_tool_queue` (`queue_id`, `resource_id`, `parent_queue_id`, `resource_name`, `resource_type`, `resource_level`, `resource_url`, `active_flag`, `order_num`, `app_type`, `add_time`, `add_name`, `add_no`) VALUES ('12', '960003', '1', '审核通过的代理商', 'link', '2', 960003, '1', '1', '1', '2019-05-15 11:30:23', 'system', '000001');

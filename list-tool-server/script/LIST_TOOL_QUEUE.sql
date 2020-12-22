
---------------------------------------------------------------------------------
-------------------------------ORACLE中建表语句------------------------------------
---------------------------------------------------------------------------------
create table list_tool_queue(
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


comment on table list_tool_queue  is  '队列资源表';

COMMENT ON COLUMN list_tool_queue.queue_id IS '队列ID';
COMMENT ON COLUMN list_tool_queue.resource_id IS '资源ID';
COMMENT ON COLUMN list_tool_queue.parent_queue_id IS '父级菜单ID, 无父级菜单默认0';
COMMENT ON COLUMN list_tool_queue.resource_name IS '资源名称';
COMMENT ON COLUMN list_tool_queue.resource_type IS '资源类型：button 按钮，link 资源链接。为link时resource_url不能为空';
COMMENT ON COLUMN list_tool_queue.resource_level IS '菜单资源等级 1一级标题，2二级标题，3三级标题，4四级标题';
COMMENT ON COLUMN list_tool_queue.resource_url IS '资源链接';
COMMENT ON COLUMN list_tool_queue.active_flag IS '0禁用，1启用';
COMMENT ON COLUMN list_tool_queue.order_num IS '排序编号，数字小的菜单在上面，只针对同一父菜单下同级别菜单排序';
COMMENT ON COLUMN list_tool_queue.app_type IS '所属应用,定义在枚举类AppTypeEnum中';
COMMENT ON COLUMN list_tool_queue.add_time IS '添加时间';
COMMENT ON COLUMN list_tool_queue.add_no IS '添加人编号';
COMMENT ON COLUMN list_tool_queue.add_name IS '添加人';

---------------------------------------------------------------------------------
-------------------------------MYSQL中建表语句------------------------------------
---------------------------------------------------------------------------------

create table list_tool_queue(
queue_id              decimal(5) unique not null comment '队列id',
resource_id           decimal(6) not null comment '资源id',
parent_queue_id       decimal(6) default 0 comment '父级菜单id, 无父级菜单默认0',
resource_name         varchar(50) not null comment '资源名称',
resource_type         varchar(20) not null comment '资源类型：button 按钮，link 资源链接。为link时resource_url不能为空',
resource_level        decimal(2) not null comment '菜单资源等级 1一级标题，2二级标题，3三级标题，4四级标题...',
resource_url          varchar(200) comment '资源链接',
active_flag           decimal(1) default 1 not null comment '0禁用，1启用',
order_num             decimal(3) comment '排序编号，数字小的菜单在上面，只针对同一父菜单下同级别菜单排序',
app_type              decimal(3) not null comment '所属应用,定义在枚举类apptypeenum中',
add_time              datetime not null comment '添加时间',
add_name              varchar(50) not null comment '添加人编号',
add_no                varchar(50) not null comment '添加人'
 )engine=innodb  default charset=utf8 comment='队列资源表'

---------------------------------------------------------------------
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (1000, 100000, 1000, '新闻管理', 'button', 1, null, 1, 2, 1, '2020-05-31 11:28:41', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (1001, 100001, 1000, '所有新闻', 'link', 2, '/100001', 1, 3, 1, '2020-05-31 11:28:41', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (1002, 100002, 1000, '待审核的新闻', 'link', 2, '/100002', 1, 1, 1, '2020-05-31 11:28:41', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (1003, 100003, 1000, '审核拒绝的新闻', 'link', 2, '/100003', 1, 5, 1, '2020-05-31 11:28:41', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (1004, 100004, 1000, '已删除的新闻', 'link', 2, '/100004', 1, 7, 1, '2020-05-31 11:28:41', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (1005, 100005, 1000, '置顶的新闻', 'link', 2, '/100005', 1, 9, 1, '2020-05-31 11:28:41', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (1006, 100006, 1000, '广告', 'link', 2, '/100006', 1, 11, 1, '2020-05-31 11:28:41', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (1007, 100007, 1000, '推荐的新闻', 'link', 2, '/100007', 1, 13, 1, '2020-05-31 11:28:41', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (1008, 100008, 1000, '禁止评论的新闻', 'link', 2, '/100008', 1, 15, 1, '2020-05-31 11:28:41', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (2000, 200000, 0, '栏目管理', 'button', 1, null, 1, 1, 1, '2020-05-31 11:16:11', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (2001, 200001, 2000, '已启用的栏目', 'link', 2, '/200001', 1, 1, 1, '2020-05-31 11:16:11', 'admin', '000001');
INSERT INTO list_tool_queue (queue_id, resource_id, parent_queue_id, resource_name, resource_type, resource_level, resource_url, active_flag, order_num, app_type, add_time, add_name, add_no) VALUES (2002, 200002, 2000, '所有栏目', 'link', 2, '/200002', 1, 3, 1, '2020-05-31 11:16:11', 'admin', '000001');
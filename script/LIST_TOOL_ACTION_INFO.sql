
---------------------------------------------------------------------------------
-------------------------------ORACLE中建表语句------------------------------------
---------------------------------------------------------------------------------
create table list_tool_action_info (
resource_Id             number(6) not null,
button_tag             varchar2(20) default 'Button' not null,
show_name              varchar2(20) not null,
button_style           varchar2(10) default 'info' not null,
click_function        varchar2(50) not null,
order_num              number(2),
request_type           number(1)
)


comment on table list_tool_action_info  is  '操作列按钮表';

COMMENT ON COLUMN list_tool_action_info.resource_Id IS '列表ID';
COMMENT ON COLUMN list_tool_action_info.button_tag IS '按钮标签，直接填写a或者Button';
COMMENT ON COLUMN list_tool_action_info.show_name IS '按钮名';
COMMENT ON COLUMN list_tool_action_info.button_style IS '样式，具体参照iview按钮type属性';
COMMENT ON COLUMN list_tool_action_info.click_function IS '点击事件触发的链接';
COMMENT ON COLUMN list_tool_action_info.order_num IS '显示顺序';
COMMENT ON COLUMN list_tool_action_info.request_type IS '1.get请求跳到链接页面，2.get仅发起请求，3.post请求';

---------------------------------------------------------------------------------
-------------------------------MYSQL中建表语句------------------------------------
---------------------------------------------------------------------------------

create table list_tool_action_info (
resource_Id             decimal(6) not null comment '列表ID',
button_tag             varchar(20) default 'Button' not null comment '按钮标签，直接填写a或者Button',
show_name              varchar(20) not null comment '按钮名',
button_style           varchar(10) default 'info' not null comment '样式，具体参照iview按钮type属性',
click_function        varchar(50) not null comment '点击事件触发的链接',
order_num              decimal(2) comment '显示顺序',
request_type           decimal(1) comment '1.get请求跳到链接页面，2.get仅发起请求，3.post请求'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8  comment='操作列按钮表';
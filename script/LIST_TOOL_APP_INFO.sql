
---------------------------------------------------------------------------------
-------------------------------ORACLE中建表语句------------------------------------
---------------------------------------------------------------------------------

create table list_tool_app_info (
appCode      number(2),
appName      varchar2(30),
shortName    varchar2(20),
gatewayUrl   varchar2(100),
appDesc      varchar2(400)
)

comment on table list_tool_app_info  is  '使用列表工具的微服务应用信息表';
COMMENT ON COLUMN list_tool_app_info.appCode IS '应用代码';
COMMENT ON COLUMN list_tool_app_info.appName IS '应用名称';
COMMENT ON COLUMN list_tool_app_info.shortName IS '应用缩写';
COMMENT ON COLUMN list_tool_app_info.gatewayUrl IS '网关url，要求与网关转发到此应用的配置一致，如网关配置调整此处要同步调整（如127.0.0.1:9001/demo）';
COMMENT ON COLUMN list_tool_app_info.appDesc IS '应用描述';


---------------------------------------------------------------------------------
-------------------------------ORACLE中建表语句------------------------------------
---------------------------------------------------------------------------------

create table list_tool_app_info (
appCode      decimal(2) comment '应用代码',
appName      varchar(30) comment '应用名称',
shortName    varchar(20) comment '应用缩写',
gatewayUrl   varchar(100) comment '网关url，要求与网关转发到此应用的配置一致，如网关配置调整此处要同步调整（如127.0.0.1:9001/demo）',
appDesc      varchar(400) comment '应用描述'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 comment='使用列表工具的微服务应用信息表';

INSERT INTO list_tool_app_info (appCode, appName, shortName, gatewayUrl, appDesc) VALUES (1, '文章发布系统', '文章', null, null);
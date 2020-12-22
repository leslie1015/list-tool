
---------------------------------------------------------------------------------
-------------------------------ORACLE中建表语句------------------------------------
---------------------------------------------------------------------------------
create table list_tool_resource_field(
resource_id    number(6) not null,
field_name     varchar2(20) not null,
show_name      varchar2(50) not null,
hide_flag       number(1) default 0 not null,
primarykey_flag number(1) default 0 ,
target_type    number(1),
target_url     varchar2(200),
index_no       number(3),
param_group_key  varchar2(20),
search_type    number(2) default 0,
sort_type      number(1) default 0,
field_type     number(1),
is_date        number(1) default 0,
date_format_type number(1)
)

comment on table list_tool_resource_field  is  '字段分析表';

COMMENT ON COLUMN list_tool_resource_field.resource_id IS '资源ID';
COMMENT ON COLUMN list_tool_resource_field.field_name IS '对应字段';
COMMENT ON COLUMN list_tool_resource_field.show_name IS '显示名';
COMMENT ON COLUMN list_tool_resource_field.primarykey_flag IS '是否主键，0不是，1是';
COMMENT ON COLUMN list_tool_resource_field.target_type IS '链接类型。0: 无，1: 普通链接，2: JS方法';
COMMENT ON COLUMN list_tool_resource_field.target_url IS 'target_type为1，填写url，target_type为2，填写JS方法名';
COMMENT ON COLUMN list_tool_resource_field.index_no IS '字段排序,数字小的排在前面';
COMMENT ON COLUMN list_tool_resource_field.param_group_key IS '对应basic_parameter.param_type，配置后自动带出下拉参数。search_type为3、6时不能为空';
COMMENT ON COLUMN list_tool_resource_field.search_type IS '0不搜索，1模糊搜索，2精确搜索，3下拉搜索，4时间区间搜索，5数字区间，6多选搜索';
COMMENT ON COLUMN list_tool_resource_field.sort_type IS '是否排序，0不排序，1排序';
COMMENT ON COLUMN list_tool_resource_field.field_type IS '字段类型。1:普通字段，2：操作按钮';
COMMENT ON COLUMN list_tool_resource_field.is_date IS '字段是否是时间，0不是，1是';
COMMENT ON COLUMN list_tool_resource_field.date_format_type IS '显示时间格式，1：yyyy-mm-dd，2：yyyy-mm-dd hh:mi:ss';

---------------------------------------------------------------------------------
-------------------------------MYSQL中建表语句------------------------------------
---------------------------------------------------------------------------------

create table list_tool_resource_field(
resource_id    int not null comment '资源ID',
field_name     varchar(20) not null comment '对应字段',
show_name      varchar(50) not null comment '显示名',
hide_flag       tinyint(1) default 0 not null comment '是否隐藏列，0否，1是',
primarykey_flag tinyint(1) default 0 comment '是否主键，0不是，1是',
target_type    tinyint(1) comment '链接类型。0: 无，1: 普通链接，2: JS方法',
target_url     varchar(200) comment 'target_type为1，填写url，target_type为2，填写JS方法名',
index_no       smallint(3) comment '字段排序,数字小的排在前面',
param_group_key  varchar(20) comment '对应basic_parameter.param_type，配置后自动带出下拉参数。search_type为3、6时不能为空',
search_type    tinyint(2) default 0 comment '0不搜索，1模糊搜索，2精确搜索，3下拉搜索，4时间区间搜索，5数字区间，6多选搜索',
sort_type      tinyint(1) default 0 comment '是否排序，0不排序，1排序',
field_type     tinyint(1) comment '字段类型。1:普通字段，2：操作按钮',
is_date        tinyint(1) default 0 comment '字段是否是时间，0不是，1是',
date_format_type decimal(1) comment '显示时间格式，1：yyyy-mm-dd，2：yyyy-mm-dd hh:mi:ss'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 comment='字段分析表'

---------------------------------------------------
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'article_title', '文章标题', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'status', '状态', 0, 0, null, 9, 'article_status', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'review_flag', '是否允许评论', 0, 0, null, 11, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'top_flag', '是否置顶', 0, 0, null, 13, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'ad_flag', '是否广告', 0, 0, null, 15, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'recommend_flag', '是否推荐', 0, 0, null, 17, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'type_name', ' 类型', 0, 0, null, 5, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'add_name', '作者', 0, 0, null, 19, null, 2, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'add_time', '创建时间', 0, 0, null, 21, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'type_id', '类型', 0, 0, null, 7, null, 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100001, 'update_time', '更新时间', 0, null, null, 23, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'article_title', '文章标题', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'top_flag', '是否置顶', 0, 0, null, 13, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'ad_flag', '是否广告', 0, 0, null, 15, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'recommend_flag', '是否推荐', 0, 0, null, 17, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'type_name', ' 类型', 0, 0, null, 5, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'add_name', '作者', 0, 0, null, 19, null, 2, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'add_time', '创建时间', 0, 0, null, 21, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'type_id', '类型', 0, 0, null, 7, null, 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100002, 'update_time', '更新时间', 0, null, null, 23, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100003, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100003, 'article_title', '文章标题', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100003, 'status', '状态', 0, 0, null, 9, 'article_status', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100003, 'type_name', ' 类型', 0, 0, null, 5, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100003, 'add_name', '作者', 0, 0, null, 19, null, 2, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100003, 'add_time', '创建时间', 0, 0, null, 21, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100003, 'type_id', '类型', 0, 0, null, 7, null, 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100003, 'update_time', '更新时间', 0, null, null, 23, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100004, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100004, 'article_title', '文章标题', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100004, 'status', '状态', 0, 0, null, 9, 'article_status', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100004, 'type_name', ' 类型', 0, 0, null, 5, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100004, 'add_name', '作者', 0, 0, null, 19, null, 2, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100004, 'add_time', '创建时间', 0, 0, null, 21, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100004, 'type_id', '类型', 0, 0, null, 7, null, 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100004, 'update_time', '更新时间', 0, null, null, 23, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'article_title', '文章标题', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'status', '状态', 0, 0, null, 9, 'article_status', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'review_flag', '是否允许评论', 0, 0, null, 11, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'ad_flag', '是否广告', 0, 0, null, 15, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'recommend_flag', '是否推荐', 0, 0, null, 17, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'type_name', ' 类型', 0, 0, null, 5, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'add_name', '作者', 0, 0, null, 19, null, 2, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'add_time', '创建时间', 0, 0, null, 21, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'type_id', '类型', 0, 0, null, 7, null, 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100005, 'update_time', '更新时间', 0, null, null, 23, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'article_title', '文章标题', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'status', '状态', 0, 0, null, 9, 'article_status', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'review_flag', '是否允许评论', 0, 0, null, 11, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'top_flag', '是否置顶', 0, 0, null, 13, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'recommend_flag', '是否推荐', 0, 0, null, 17, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'type_name', ' 类型', 0, 0, null, 5, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'add_name', '作者', 0, 0, null, 19, null, 2, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'add_time', '创建时间', 0, 0, null, 21, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'type_id', '类型', 0, 0, null, 7, null, 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100006, 'update_time', '更新时间', 0, null, null, 23, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'article_title', '文章标题', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'status', '状态', 0, 0, null, 9, 'article_status', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'review_flag', '是否允许评论', 0, 0, null, 11, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'top_flag', '是否置顶', 0, 0, null, 13, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'ad_flag', '是否广告', 0, 0, null, 15, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'type_name', ' 类型', 0, 0, null, 5, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'add_name', '作者', 0, 0, null, 19, null, 2, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'add_time', '创建时间', 0, 0, null, 21, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'type_id', '类型', 0, 0, null, 7, null, 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100007, 'update_time', '更新时间', 0, null, null, 23, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'article_title', '文章标题', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'status', '状态', 0, 0, null, 9, 'article_status', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'top_flag', '是否置顶', 0, 0, null, 13, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'ad_flag', '是否广告', 0, 0, null, 15, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'recommend_flag', '是否推荐', 0, 0, null, 17, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'type_name', ' 类型', 0, 0, null, 5, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'add_name', '作者', 0, 0, null, 19, null, 2, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'add_time', '创建时间', 0, 0, null, 21, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'type_id', '类型', 0, 0, null, 7, null, 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (100008, 'update_time', '更新时间', 0, null, null, 23, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (200001, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (200001, 'category_name', '分类名', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (200001, 'order_num', '排序', 0, 0, null, 5, null, 0, 1, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (200002, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (200002, 'category_name', '分类名', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (200002, 'order_num', '排序', 0, 0, null, 5, null, 0, 1, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (200002, 'active_flag', '是否启用', 0, 0, null, 7, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (300001, 'id', 'id', 1, 0, null, 1, null, 0, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (300001, 'nickname', '昵称', 0, 0, null, 3, null, 1, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (300001, 'username', '用户名', 0, 0, null, 5, null, 2, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (300001, 'admin_flag', '是否管理员', 0, 0, null, 7, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (300001, 'status', '是否冻结', 0, 0, null, 9, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (300001, 'delete_flag', '是否删除', 0, 0, null, 11, 'common_judge_flag', 3, 0, 1, 0, null);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (300001, 'create_time', '创建时间', 0, 0, null, 13, null, 4, 1, 1, 1, 2);
insert into list_tool_resource_field (resource_id, field_name, show_name, primarykey_flag, target_type, target_url, index_no, param_group_key, search_type, sort_type, field_type, is_date, date_format_type) values (300001, 'last_time', '最后登录时间', 0, 0, null, 15, null, 4, 1, 1, 1, 2);
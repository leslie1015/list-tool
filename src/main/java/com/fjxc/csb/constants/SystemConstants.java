package com.fjxc.csb.constants;

public class SystemConstants {

    public static final String MAPPPER_PATH = "com.fjxc.csb.dao.ListToolMapper.";


    //Redis Key
    // REDIS 过期时间
    public static final Long REDIS_EXPIRED_TIME = 120L;

    /**
     * 菜单redis key前缀
     */
    public static final String REDIS_KEY_PREFIX_MENU = "list_tool_menu_apptype_";

    /**
     * 列表配置redis key前缀
     */
    public static final String REDIS_KEY_PREFIX_RESOURCE_FIELD = "list_tool_resource_field_";

    /**
     * 列操作按钮redis key前缀
     */
    public static final String REDIS_KEY_PREFIX_FIELD_ACTION = "list_tool_field_action_";

    /**
     * 参数redis key前缀
     */
    public static final String REDIS_KEY_PREFIX_BASIC_PARAM = "list_tool_basic_param_";


}

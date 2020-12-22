package com.fjxc.csb.constants;
/**
 * @author wangyong
 */
public class SystemConstants {

    public static final String MAPPPER_PATH = "com.fjxc.csb.dao.ListToolMapper.";


    //Redis Key
    // REDIS 过期时间，单位：秒
    public static final Long REDIS_EXPIRED_TIME = 60L;

    /**
     * 菜单redis key前缀
     */
    public static final String REDIS_KEY_PREFIX_MENU = "csb:list_tool_menu_apptype:";

    /**
     * 列表配置redis key前缀
     */
    public static final String REDIS_KEY_PREFIX_RESOURCE_FIELD = "csb:list_tool_resource_field:";

    /**
     * 列操作按钮redis key前缀
     */
    public static final String REDIS_KEY_PREFIX_FIELD_ACTION = "csb:list_tool_field_action:";

    /**
     * 参数redis key前缀
     */
    public static final String REDIS_KEY_PREFIX_BASIC_PARAM = "csb:list_tool_basic_param:";


}

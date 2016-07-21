package com.aebiz.b2b2c.basebusiness.systembackmgr.common;

/**
 * 
 * 系统管理的memcache的参数设置
 * 
 * 1.系统管理的key设置<br />
 * 2.为了便于扩展，将memcache客户端名称也定义在此
 * 
 * @author cj
 * 
 */
public class SystembackCacheConstant {
	/* 系统管理,名称 */
	public static final String SYSTEMBACK_NAME = "systemback";

	/* 系统管理,缓存客服端名称 */
	public static final String SYSTEMBACK_MEM_CLIENT_NAME = "memCachedClient";

	/* 系统管理,权限菜单 */
	public static final String SYSTEMBACK_SYS_MENU = SYSTEMBACK_NAME
			+ "/sysmenu";

	/* 系统管理,部门管理 */
	public static final String SYSTEMBACK_SYS_DEPT = SYSTEMBACK_NAME
			+ "/sysdept";

	/* 系统管理,部门菜单关联 */
	public static final String SYSTEMBACK_SYS_DEPT_MENU_REL = SYSTEMBACK_NAME
			+ "/sysdeptmenurel";

	/* 系统管理,部门权限关联 */
	public static final String SYSTEMBACK_SYS_DEPT_PERMIT_REL = SYSTEMBACK_NAME
			+ "/syspermitrel";
	
	/* 系统管理,管理员 */
	public static final String SYSTEMBACK_SYS_MANAGER = SYSTEMBACK_NAME
			+ "/sysmanager";
	
	/* 系统管理,管理员角色关联 */
	public static final String SYSTEMBACK_SYS_MANAGER_ROLE_REL = SYSTEMBACK_NAME
			+ "/sysmanagerrolerel";
	
	/* 系统管理,权限 */
	public static final String SYSTEMBACK_SYS_PERMIT = SYSTEMBACK_NAME
			+ "/syspermit";
	
	/* 系统管理,角色 */
	public static final String SYSTEMBACK_SYS_ROLE = SYSTEMBACK_NAME
			+ "/sysrole";
	
	/* 系统管理,角色菜单关联关系 */
	public static final String SYSTEMBACK_SYS_ROLE_MENU_REL = SYSTEMBACK_NAME
			+ "/sysrolemenurel";
	
	/* 系统管理,角色菜单关联关系 */
	public static final String SYSTEMBACK_SYS_ROLE_PERMIT_REL = SYSTEMBACK_NAME
			+ "/sysrolepermitrel";
	
	/* 系统管理,商户菜单管理 */
	public static final String SYSTEMBACK_STORE_PLAT_MENU = SYSTEMBACK_NAME
			+ "/storeplatmenu";
	
	/* 系统管理,商户权限管理 */
	public static final String SYSTEMBACK_STORE_PLAT_PERMIT = SYSTEMBACK_NAME
			+ "/storeplatpermit";
	
	/* 系统管理,商户角色管理 */
	public static final String SYSTEMBACK_STORE_PLAT_ROLE = SYSTEMBACK_NAME
			+ "/storeplatrole";
	
	/* 系统管理,商户角色菜单关联 */
	public static final String SYSTEMBACK_STORE_PLAT_ROLE_MENU_REL = SYSTEMBACK_NAME
			+ "/storeplatrolemenurel";
	
	/* 系统管理,商户角色菜单关联 */
	public static final String SYSTEMBACK_STORE_PLAT_ROLE_PERMIT_REL = SYSTEMBACK_NAME
			+ "/storeplatrolepermitrel";
}

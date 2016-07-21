package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.service;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

/**
 * 系统菜单管理
 * 
 * 菜单分为一级菜单、二级菜单、三级菜单,需要给某个角色分配菜单，此用户登录时，才能展示所选择的菜单
 * 
 * 
 * @author duandeyi
 * 
 */
public interface SysMenuService extends
		BaseService<SysMenuModel, SysMenuQueryModel> {

	/**
	 * 查询某个用户所拥有的菜单
	 * 
	 * @param userUuid
	 * @return
	 */
	public List<SysMenuModel> getUserSysMenus(String userUuid);

	/**
	 * 获得一级菜单
	 * 
	 * @return
	 */
	public List<SysMenuModel> getTopMenus();

	/**
	 * 获得某个菜单的子菜单
	 * 
	 * @return
	 */
	public List<SysMenuModel> getSubMenus(String parentUuid);

	/**
	 * 通过菜单UUID查名称
	 * 
	 * @param sysMenuUuid
	 * @return
	 */
	public String getSysMenuNameByUuid(String sysMenuUuid);

	/**
	 * 验证菜单的编号是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public boolean check(String uuid, String menuId);

	/**
	 * 查询某个部门所拥有的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysMenuModel> getDeptSysMenus(String deptUuid);

	/**
	 * 通过菜单ID获得父ID的组合,组合的拼凑方法如下：
	 * 
	 * 一级菜单|二级菜单|三级菜单
	 * 
	 * @param menuUuid
	 * @return
	 */
	public String getParentUuids(String menuUuid);

	/**
	 * 获得父级菜单的名称，供选择
	 * 
	 * 一级菜单>二级菜单>三级菜单
	 * 
	 * @param menuUuid
	 * @return
	 */
	public String getParentNames(String menuUuid);
}

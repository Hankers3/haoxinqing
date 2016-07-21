package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.dao;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;

public interface SysMenuDAO extends BaseDAO<SysMenuModel, SysMenuQueryModel> {
	public void setDataDeleteFlag(String uuid, int delFlag);

	/**
	 * 获得某个用户拥有的菜单权限
	 * 
	 * @param userUuid
	 * @return
	 */
	public List<SysMenuModel> getUserSysMenus(String userUuid);

	public List<String> getUserSysMenuUuids(String userUuid);

	/**
	 * 查询一级菜单，一级菜单的parentId=-1
	 * 
	 * @return
	 */
	public List<SysMenuModel> getTopMenus();

	public List<String> getTopMenuUuids();

	/**
	 * 获得某个菜单的子菜单
	 * 
	 * @return
	 */
	public List<SysMenuModel> getSubMenus(String parentUuid);

	public List<String> getSubMenuUuids(String parentUuid);

	/**
	 * 根据菜单的UUID获得菜单的名称
	 * 
	 * @param menuUuid
	 * @return
	 */
	public String getMenuNameByUuid(String menuUuid);

	/**
	 * 查询是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public SysMenuModel getMenuByMenuIdAndUuid(String uuid, String menuId);

	public String getMenuUuidByMenuIdAndUuid(String uuid, String menuId);

	/**
	 * 查询某个部门所拥有的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysMenuModel> getDeptSysMenus(String deptUuid);

	public List<String> getDeptSysMenuUuids(String deptUuid);
}

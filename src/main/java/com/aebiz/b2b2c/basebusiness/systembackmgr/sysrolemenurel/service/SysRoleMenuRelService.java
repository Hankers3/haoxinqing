package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.service;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

public interface SysRoleMenuRelService extends
		BaseService<SysRoleMenuRelModel, SysRoleMenuRelQueryModel> {

	/**
	 * 
	 * 通过角色编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysRoleMenuRelModel> getAllRelsByRoleUuid(String roleUuid);
	
	/**
	 * 
	 * 通过角色编号查找已经关联的菜单的uuid
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<String> getAllRelMenuUuidsByRoleUuid(String roleUuid);
	
	/**
	 * 
	 * 通过角色编号查找已经关联的菜单
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<SysMenuModel> getAllRelMenuByRoleUuid(List<String> roleIds);

	/**
	 * 保存角色和菜单以及权限的关联关系
	 * 
	 * 1.删除角色关联的所有菜单 <br />
	 * 2.重新创建角色和菜单的关联关系<br />
	 * 3.删除角色关联的所有权限 <br />
	 * 4.重新创建角色和权限的关联关系<br />
	 * 
	 * @param roleUuid
	 * @param menuUuids
	 * @param permitUuids
	 */
	public void saveRoleAndMenuPermitRel(String roleUuid, String[] menuUuids,
			String[] permitUuids);
}

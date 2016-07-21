package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.vo.StorePlatRoleMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.vo.StorePlatRoleMenuRelQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelModel;

public interface StorePlatRoleMenuRelService extends
		BaseService<StorePlatRoleMenuRelModel, StorePlatRoleMenuRelQueryModel> {

	/**
	 * 
	 * 通过角色编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<StorePlatRoleMenuRelModel> getAllRelsByRoleUuid(String roleUuid);

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

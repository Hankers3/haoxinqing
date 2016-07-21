package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.dao;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.vo.StorePlatRoleMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.vo.StorePlatRoleMenuRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;

public interface StorePlatRoleMenuRelDAO extends
		BaseDAO<StorePlatRoleMenuRelModel, StorePlatRoleMenuRelQueryModel> {

	/**
	 * 
	 * 通过角色编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<StorePlatRoleMenuRelModel> getAllRelsByRoleUuid(String roleUuid);

	/**
	 * 删除角色关联的所有菜单
	 * 
	 * 在保存角色和菜单的关联关系时，需要先删除角色和菜单的关联关系，再重新建立角色和菜单的关联关系
	 * 
	 * @param roleUuid
	 */
	public void removeAllRelMenusByRoleUuid(String roleUuid);
}
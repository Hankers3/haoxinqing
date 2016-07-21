package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.dao;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;

public interface SysDeptMenuRelDAO extends
		BaseDAO<SysDeptMenuRelModel, SysDeptMenuRelQueryModel> {

	/**
	 * 
	 * 通过部门编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysDeptMenuRelModel> getAllRelsByDeptUuid(String deptUuid);
	
	public List<String> getAllRelUuidsByDeptUuid(String deptUuid);

	/**
	 * 删除部门关联的所有菜单
	 * 
	 * 在保存部门和菜单的关联关系时，需要先删除部门和菜单的关联关系，再重新建立部门和菜单的关联关系
	 * 
	 * @param deptUuid
	 */
	public void removeAllRelMenusByDeptUuid(String deptUuid);

}
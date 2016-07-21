package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.service;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

public interface SysDeptMenuRelService extends
		BaseService<SysDeptMenuRelModel, SysDeptMenuRelQueryModel> {

	/**
	 * 
	 * 通过部门编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysDeptMenuRelModel> getAllRelsByDeptUuid(String deptUuid);

	/**
	 * 保存部门和菜单以及权限的关联关系
	 * 
	 * 1.删除部门关联的所有菜单 <br />
	 * 2.重新创建部门和菜单的关联关系<br />
	 * 3.删除部门关联的所有权限 <br />
	 * 4.重新创建部门和权限的关联关系<br />
	 * 
	 * @param deptUuid
	 * @param menuUuids
	 * @param permitUuids
	 */
	public void saveDeptAndMenuPermitRel(String deptUuid, String[] menuUuids,
			String[] permitUuids);
}

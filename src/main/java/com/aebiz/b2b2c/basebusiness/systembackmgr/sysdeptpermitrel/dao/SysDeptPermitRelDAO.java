package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.dao;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;

public interface SysDeptPermitRelDAO extends
		BaseDAO<SysDeptPermitRelModel, SysDeptPermitRelQueryModel> {

	/**
	 * 
	 * 通过部门编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysDeptPermitRelModel> getAllPermitRelsByDeptUuid(
			String deptUuid);
	
	public List<String> getAllPermitRelUuidsByDeptUuid(
			String deptUuid);

	/**
	 * 删除所有的某个部门和权限的关联关系
	 * 
	 * 在建立部门和权限的关联关系时，先删除，再重新建立
	 * 
	 * @param deptUuid
	 */
	public void removeAllRelPermitsByDeptUuid(String deptUuid);
}
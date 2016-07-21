package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.service;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

public interface SysDeptPermitRelService extends
		BaseService<SysDeptPermitRelModel, SysDeptPermitRelQueryModel> {

	/**
	 * 
	 * 通过部门编号查找已经关联的权限
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysDeptPermitRelModel> getAllPermitRelsByDeptUuid(
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

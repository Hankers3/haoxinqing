package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.dao;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;

public interface SysDeptDAO extends BaseDAO<SysDeptModel, SysDeptQueryModel> {

	/**
	 * 检查部门是否存在
	 * 
	 * @param uuid
	 * @param deptName
	 * @return
	 */
	public SysDeptModel getDeptByDeptIdAndUuid(String uuid, String deptName);

	public String getDeptUuidByDeptIdAndUuid(String uuid, String deptName);

	/**
	 * 通过部门uuid查找部门名称
	 * 
	 * @param deptUuid
	 * @return
	 */
	public String getDeptNameByDeptUuid(String deptUuid);
}
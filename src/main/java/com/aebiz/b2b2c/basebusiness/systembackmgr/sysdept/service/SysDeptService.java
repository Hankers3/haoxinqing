package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptQueryModel;

/**
 * <p>
 * 系统部门管理
 * 
 * 管理员都需要以部门为单位来管理
 * 
 * 部门可以关联菜单、权限，当添加角色时，如果角色属于某个部门，则只能从该部门选择菜单和权限<br />
 * 管理员只能从部门关联的角色中选择所属角色
 * 
 * </p>
 * 
 * @author duandeyi
 * 
 */
public interface SysDeptService extends
		BaseService<SysDeptModel, SysDeptQueryModel> {

	/**
	 * 检查部门名称是否重复
	 * 
	 * @param uuid
	 * @param deptName
	 * @return
	 */
	public boolean check(String uuid, String deptName);

	/**
	 * 通过部门的编号查找部门的名称
	 * 
	 * @param deptUuid
	 * @return
	 */
	public String getDeptNameByDeptUuid(String deptUuid);
	
}

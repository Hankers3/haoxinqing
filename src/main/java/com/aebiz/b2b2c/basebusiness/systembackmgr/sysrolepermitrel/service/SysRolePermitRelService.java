package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.service;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.vo.SysRolePermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.vo.SysRolePermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

public interface SysRolePermitRelService extends
		BaseService<SysRolePermitRelModel, SysRolePermitRelQueryModel> {

	/**
	 * 
	 * 获得角色已经关联的权限列表
	 * 
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<SysRolePermitRelModel> getAllPermitRelsByRoleUuid(
			String roleUuid);

	/**
	 * 删除所有的某个角色和权限的关联关系
	 * 
	 * 在建立角色和权限的关联关系时，先删除，再重新建立
	 * 
	 * @param roleUuid
	 */
	public void removeAllRelPermitsByRoleUuid(String roleUuid);

}

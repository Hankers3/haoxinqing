package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.vo.SysRolePermitRelModel;

public interface StorePlatRolePermitRelService extends BaseService<StorePlatRolePermitRelModel,StorePlatRolePermitRelQueryModel>{

	/**
	 * 
	 * 获得角色已经关联的权限列表
	 * 
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<StorePlatRolePermitRelModel> getAllPermitRelsByRoleUuid(
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

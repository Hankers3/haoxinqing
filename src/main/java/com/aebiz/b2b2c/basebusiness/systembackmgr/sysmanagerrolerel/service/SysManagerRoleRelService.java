package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.service;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

public interface SysManagerRoleRelService extends
		BaseService<SysManagerRoleRelModel, SysManagerRoleRelQueryModel> {

	/**
	 * 保存管理员和角色的关联关系
	 * 
	 * 
	 * @param managerUuid
	 * @param selectedRoleUuids
	 */
	public void saveRelatedRole(String managerUuid, String[] selectedRoleUuids);
	
	public List<String> getSysManagerRoleRelByRoleUuid(String roleUuid);
}

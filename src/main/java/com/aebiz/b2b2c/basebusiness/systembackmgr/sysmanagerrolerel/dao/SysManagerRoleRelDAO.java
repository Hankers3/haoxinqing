package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelQueryModel;

public interface SysManagerRoleRelDAO extends
		BaseDAO<SysManagerRoleRelModel, SysManagerRoleRelQueryModel> {

	/**
	 * 删除管理员已近关联的所有角色
	 * 
	 * @param managerUuid
	 */
	public void removeReledRelsByManagerUuid(String managerUuid);

	public List<String> getReledRelUuidsByManagerUuid(String managerUuid);

	public List<String> getSysManagerRoleRelByRoleUuid(String roleUuid);
}
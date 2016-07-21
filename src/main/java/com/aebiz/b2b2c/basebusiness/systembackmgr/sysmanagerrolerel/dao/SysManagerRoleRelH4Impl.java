package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

@Repository
public class SysManagerRoleRelH4Impl extends
		BaseH4Impl<SysManagerRoleRelModel, SysManagerRoleRelQueryModel>
		implements SysManagerRoleRelDAO {

	/**
	 * 删除管理员已近关联的所有角色
	 * 
	 * @param managerUuid
	 */
	public void removeReledRelsByManagerUuid(String managerUuid) {
		StringBuffer sb = new StringBuffer(
				"delete from SysManagerRoleRelModel o where o.managerUuid = :managerUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("managerUuid", managerUuid);

		q.executeUpdate();
	}

	public List<String> getReledRelUuidsByManagerUuid(String managerUuid) {
		StringBuffer sb = new StringBuffer(
				"select o.uuid from SysManagerRoleRelModel o where o.managerUuid = :managerUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("managerUuid", managerUuid);

		return q.list();
	}

	@Override
	public List<String> getSysManagerRoleRelByRoleUuid(String roleUuid) {
		StringBuffer sb = new StringBuffer(
				"select o.uuid from SysManagerRoleRelModel o where o.roleUuid = :roleUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("roleUuid", roleUuid);

		return q.list();
	}
}

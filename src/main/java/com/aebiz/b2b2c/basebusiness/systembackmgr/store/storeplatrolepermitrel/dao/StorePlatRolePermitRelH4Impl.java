package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

@Repository
public class StorePlatRolePermitRelH4Impl
		extends
		BaseH4Impl<StorePlatRolePermitRelModel, StorePlatRolePermitRelQueryModel>
		implements StorePlatRolePermitRelDAO {

	/**
	 * 
	 * 获得角色已经关联的权限列表
	 * 
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<StorePlatRolePermitRelModel> getAllPermitRelsByRoleUuid(
			String roleUuid) {

		StringBuffer hql = new StringBuffer(
				"select o from StorePlatRolePermitRelModel o where 1=1");
		hql.append(" and o.roleUuid=:roleUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("roleUuid", roleUuid);

		return q.list();
	}

	/**
	 * 删除所有的某个角色和权限的关联关系
	 * 
	 * 在建立角色和权限的关联关系时，先删除，再重新建立
	 * 
	 * @param roleUuid
	 */
	public void removeAllRelPermitsByRoleUuid(String roleUuid) {
		StringBuffer sb = new StringBuffer(
				"delete from StorePlatRolePermitRelModel o where o.roleUuid = :roleUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("roleUuid", roleUuid);

		q.executeUpdate();
	}
}

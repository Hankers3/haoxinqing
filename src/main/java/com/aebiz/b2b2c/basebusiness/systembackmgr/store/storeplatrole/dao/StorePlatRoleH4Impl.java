package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo.StorePlatRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo.StorePlatRoleQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Repository
public class StorePlatRoleH4Impl extends
		BaseH4Impl<StorePlatRoleModel, StorePlatRoleQueryModel> implements
		StorePlatRoleDAO {

	/**
	 * 通过角色编号，查询角色，在验证角色编号的时候试用
	 * 
	 */
	public StorePlatRoleModel getRoleByRoleIdAndUuid(String uuid, String roleId) {

		String hql = "select o from StorePlatRoleModel o where 1=1 ";
		hql += " and o.id = :roleID";
		if (!StringUtil.isEmpty(uuid)) {
			hql += " and o.uuid <> :roleUuid";
		}

		Query q = this.getH4Session().createQuery(hql);

		if (!StringUtil.isEmpty(uuid)) {
			q.setString("roleUuid", uuid);
		}
		q.setString("roleID", roleId);

		List<StorePlatRoleModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

}

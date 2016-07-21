package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

@Repository
public class SysRoleMenuRelH4Impl extends
		BaseH4Impl<SysRoleMenuRelModel, SysRoleMenuRelQueryModel> implements
		SysRoleMenuRelDAO {

	/**
	 * 
	 * 通过角色编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysRoleMenuRelModel> getAllRelsByRoleUuid(String roleUuid) {

		StringBuffer hql = new StringBuffer(
				"select o from SysRoleMenuRelModel o where 1=1");

		hql.append(" and o.roleUuid=:roleUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("roleUuid", roleUuid);

		return q.list();
	}
	
	public List<String> getAllRelUuidsByRoleUuid(String roleUuid){
		StringBuffer hql = new StringBuffer(
				"select o.uuid from SysRoleMenuRelModel o where 1=1");

		hql.append(" and o.roleUuid=:roleUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("roleUuid", roleUuid);

		return q.list();
	}
	
	/**
	 * 
	 * 通过角色编号查找已经关联的菜单的uuid
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<String> getAllRelMenuUuidsByRoleUuid(String roleUuid){
		StringBuffer hql = new StringBuffer(
				"select o.menuUuid from SysRoleMenuRelModel o where 1=1");

		hql.append(" and o.roleUuid=:roleUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("roleUuid", roleUuid);

		return q.list();
	}

	/**
	 * 删除部门关联的所有菜单
	 * 
	 * 在保存部门和菜单的关联关系时，需要先删除部门和菜单的关联关系，再重新建立部门和菜单的关联关系
	 * 
	 * @param deptUuid
	 */
	public void removeAllRelMenusByRoleUuid(String roleUuid) {

		StringBuffer sb = new StringBuffer(
				"delete from SysRoleMenuRelModel o where o.roleUuid = :roleUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("roleUuid", roleUuid);

		q.executeUpdate();
	}
}

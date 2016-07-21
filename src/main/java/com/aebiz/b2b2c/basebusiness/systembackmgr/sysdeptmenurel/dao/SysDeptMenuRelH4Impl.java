package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

@Repository
public class SysDeptMenuRelH4Impl extends
		BaseH4Impl<SysDeptMenuRelModel, SysDeptMenuRelQueryModel> implements
		SysDeptMenuRelDAO {

	/**
	 * 
	 * 通过部门编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysDeptMenuRelModel> getAllRelsByDeptUuid(String deptUuid) {

		StringBuffer hql = new StringBuffer(
				"select o from SysDeptMenuRelModel o where 1=1");

		hql.append(" and o.deptUuid=:deptUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();
	}

	public List<String> getAllRelUuidsByDeptUuid(String deptUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.uuid from SysDeptMenuRelModel o where 1=1");

		hql.append(" and o.deptUuid=:deptUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();
	}

	/**
	 * 删除部门关联的所有菜单
	 * 
	 * 在保存部门和菜单的关联关系时，需要先删除部门和菜单的关联关系，再重新建立部门和菜单的关联关系
	 * 
	 * @param deptUuid
	 */
	public void removeAllRelMenusByDeptUuid(String deptUuid) {

		StringBuffer sb = new StringBuffer(
				"delete from SysDeptMenuRelModel o where o.deptUuid = :deptUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("deptUuid", deptUuid);

		q.executeUpdate();

	}
}

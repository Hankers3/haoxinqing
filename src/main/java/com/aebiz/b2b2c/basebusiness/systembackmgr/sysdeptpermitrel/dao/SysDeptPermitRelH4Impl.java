package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

@Repository
public class SysDeptPermitRelH4Impl extends
		BaseH4Impl<SysDeptPermitRelModel, SysDeptPermitRelQueryModel> implements
		SysDeptPermitRelDAO {

	/**
	 * 
	 * 通过部门编号查找已经关联的权限
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysDeptPermitRelModel> getAllPermitRelsByDeptUuid(
			String deptUuid) {
		StringBuffer hql = new StringBuffer(
				"select o from SysDeptPermitRelModel o where 1=1");
		hql.append(" and o.deptUuid=:deptUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();
	}

	public List<String> getAllPermitRelUuidsByDeptUuid(String deptUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.uuid from SysDeptPermitRelModel o where 1=1");
		hql.append(" and o.deptUuid=:deptUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();
	}

	/**
	 * 删除所有的某个部门和权限的关联关系
	 * 
	 * 在建立部门和权限的关联关系时，先删除，再重新建立
	 * 
	 * @param deptUuid
	 */
	public void removeAllRelPermitsByDeptUuid(String deptUuid) {
		StringBuffer sb = new StringBuffer(
				"delete from SysDeptPermitRelModel o where o.deptUuid = :deptUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("deptUuid", deptUuid);

		q.executeUpdate();
	}
}

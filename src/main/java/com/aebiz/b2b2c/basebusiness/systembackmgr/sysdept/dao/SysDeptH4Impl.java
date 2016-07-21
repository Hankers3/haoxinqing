package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Repository
public class SysDeptH4Impl extends BaseH4Impl<SysDeptModel, SysDeptQueryModel>
		implements SysDeptDAO {

	/**
	 * 查询部门是否存在
	 * 
	 */
	public SysDeptModel getDeptByDeptIdAndUuid(String uuid, String deptName) {

		String hql = "select o from SysDeptModel o where 1=1 ";
		hql += " and o.departmentName = :departmentName ";

		if (!StringUtil.isEmpty(uuid)) {
			hql += " and o.uuid <> :deptUuid ";
		}

		Query q = this.getH4Session().createQuery(hql);

		if (!StringUtil.isEmpty(uuid)) {
			q.setString("deptUuid", uuid);
		}
		q.setString("departmentName", deptName);

		List<SysDeptModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public String getDeptUuidByDeptIdAndUuid(String uuid, String deptName) {
		String hql = "select o.uuid from SysDeptModel o where 1=1 ";
		hql += " and o.departmentName = :departmentName ";

		if (!StringUtil.isEmpty(uuid)) {
			hql += " and o.uuid <> :deptUuid ";
		}

		Query q = this.getH4Session().createQuery(hql);

		if (!StringUtil.isEmpty(uuid)) {
			q.setString("deptUuid", uuid);
		}
		q.setString("departmentName", deptName);

		List<String> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 通过部门编号查询部门名称
	 */
	public String getDeptNameByDeptUuid(String deptUuid) {
		String hql = "select o.departmentName from SysDeptModel o where 1=1 ";
		hql += " and o.uuid=:deptUuid ";

		Query q = this.getH4Session().createQuery(hql);

		q.setString("deptUuid", deptUuid);

		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}

		return "";
	}

}

package com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

@Repository
public class SysPermitH4Impl extends
		BaseH4Impl<SysPermitModel, SysPermitQueryModel> implements SysPermitDAO {

	public void setDataDeleteFlag(String uuid, int delFlag) {
		String hql = "update SysPermitModel o set o.delFlag=:delFlag where o.uuid=:uuid";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		map.put("delFlag", delFlag);

		super.exeUpdate(hql, map);
	}

	public Set<String> getPermitExprsByRoleIds(Set<String> roleIds) {
		Set<String> retSet = new HashSet<String>();

		String hql = "select o from SysPermitModel o,SysRoleModel sr,SysRolePermitRelModel srp "
				+ " where o.uuid=srp.permitUuid and srp.roleUuid=sr.uuid and sr.id in (:roleIds)";
		Query q = this.getH4Session().createQuery(hql);
		q.setParameterList("roleIds", roleIds.toArray());

		List<SysPermitModel> list = q.list();
		for (SysPermitModel spm : list) {
			retSet.add(spm.getExpression());
		}
		return retSet;
	}

	public List<SysPermitModel> getRolesSysPermit(String roleUuid) {
		StringBuffer hql = new StringBuffer(
				"select spm from SysPermitModel spm ,SysRoleModel srm ,SysRolePermitRelModel srpm where 1=1");
		hql.append(" and spm.uuid = srpm.permitUuid");
		hql.append(" and srm.uuid = srpm.roleUuid");
		hql.append(" and srm.uuid = :roleUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("roleUuid", roleUuid);
		return q.list();
	}

	public List<String> getRolesSysPermitUuids(String roleUuid) {
		StringBuffer hql = new StringBuffer(
				"select spm.uuid from SysPermitModel spm ,SysRoleModel srm ,SysRolePermitRelModel srpm where 1=1");
		hql.append(" and spm.uuid = srpm.permitUuid");
		hql.append(" and srm.uuid = srpm.roleUuid");
		hql.append(" and srm.uuid = :roleUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("roleUuid", roleUuid);
		return q.list();
	}

	public SysPermitModel getSysPermitByMenuId(String bussinessType,
			String bussinessUuid) {

		StringBuffer hql = new StringBuffer(
				"select spm from SysPermitModel spm where 1=1");
		hql.append(" and spm.businessType=:businessType");
		hql.append(" and spm.businessUuid=:businessUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("businessType", bussinessType);
		q.setString("businessUuid", bussinessUuid);

		List<SysPermitModel> list = q.list();

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public String getSysPermitUuidByMenuId(String bussinessType,
			String bussinessUuid) {
		StringBuffer hql = new StringBuffer(
				"select spm.uuid from SysPermitModel spm where 1=1");
		hql.append(" and spm.businessType=:businessType");
		hql.append(" and spm.businessUuid=:businessUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("businessType", bussinessType);
		q.setString("businessUuid", bussinessUuid);

		List<String> list = q.list();

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public List<SysPermitModel> getAllOperteSysPermit(String bussinessType) {
		StringBuffer hql = new StringBuffer(
				"select spm from SysPermitModel spm where 1=1");
		hql.append(" and spm.businessType=:businessType");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("businessType", bussinessType);

		return q.list();
	}

	public List<String> getAllOperteSysPermitUuids(String bussinessType) {
		StringBuffer hql = new StringBuffer(
				"select spm.uuid from SysPermitModel spm where 1=1");
		hql.append(" and spm.businessType=:businessType");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("businessType", bussinessType);

		return q.list();
	}

	/**
	 * 查询某个部门所拥有的权限
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysPermitModel> getDeptSysPermits(String deptUuid) {
		StringBuffer sb = new StringBuffer(
				"select spm from SysPermitModel spm ,SysDeptPermitRelModel sdmrm where 1=1");
		sb.append(" and spm.uuid = sdmrm.permitUuid");

		sb.append(" and sdmrm.deptUuid = :deptUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();
	}

	public List<String> getDeptSysPermitUuids(String deptUuid) {
		StringBuffer sb = new StringBuffer(
				"select spm.uuid from SysPermitModel spm ,SysDeptPermitRelModel sdmrm where 1=1");
		sb.append(" and spm.uuid = sdmrm.permitUuid");

		sb.append(" and sdmrm.deptUuid = :deptUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();
	}
}

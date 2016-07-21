package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Repository
public class SysRoleH4Impl extends BaseH4Impl<SysRoleModel, SysRoleQueryModel>
		implements SysRoleDAO {

	/**
	 * 设置角色的删除状态
	 * 
	 * 将编号为uuid的角色更新成删除状态或取消删除状态
	 * 
	 */
	public void setDataDeleteFlag(String uuid, int delFlag) {
		String hql = "update SysRoleModel o set o.delFlag=:delFlag where o.uuid=:uuid";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		map.put("delFlag", delFlag);

		super.exeUpdate(hql, map);
	}

	/**
	 * 查询某个用户所拥有的角色
	 * 
	 * 一个用户可以有多个角色，根据用户ID查询此用户关联的角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public Set<String> getRoleIdsByUserId(String userId) {
		Set<String> retSet = new HashSet<String>();

		String hql = "select o from SysRoleModel o,SysManagerModel sm,SysManagerRoleRelModel smr "
				+ " where o.uuid=smr.roleUuid and smr.managerUuid=sm.uuid and sm.id=:userId";
		Query q = this.getH4Session().createQuery(hql);
		q.setString("userId", userId);

		List<SysRoleModel> list = q.list();
		for (SysRoleModel srm : list) {
			retSet.add(srm.getId());
		}
		return retSet;
	}

	/**
	 * 通过角色编号，查询角色，在验证角色编号的时候试用
	 * 
	 */
	public SysRoleModel getRoleByRoleIdAndUuid(String uuid, String roleId) {

		String hql = "select o from SysRoleModel o where 1=1 ";
		hql += " and o.id = :roleID";
		if (!StringUtil.isEmpty(uuid)) {
			hql += " and o.uuid <> :roleUuid";
		}

		Query q = this.getH4Session().createQuery(hql);

		if (!StringUtil.isEmpty(uuid)) {
			q.setString("roleUuid", uuid);
		}
		q.setString("roleID", roleId);

		List<SysRoleModel> list = q.list();

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	public String getRoleUuidByRoleIdAndUuid(String uuid, String roleId){
		String hql = "select o.uuid from SysRoleModel o where 1=1 ";
		hql += " and o.id = :roleID";
		if (!StringUtil.isEmpty(uuid)) {
			hql += " and o.uuid <> :roleUuid";
		}

		Query q = this.getH4Session().createQuery(hql);

		if (!StringUtil.isEmpty(uuid)) {
			q.setString("roleUuid", uuid);
		}
		q.setString("roleID", roleId);

		List<String> list = q.list();

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	protected String getAppendHql(SysRoleQueryModel qm) {
		return "order by o." + qm.getSortName() + " " + qm.getSortType();
	}

	/**
	 * 查询某部门下所有的角色
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysRoleModel> getSysRoleModelsByDeptUuid(String deptUuid) {
		StringBuffer hql = new StringBuffer(
				"select o from SysRoleModel o where 1=1 ");
		hql.append(" and o.deptUuid = :deptUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();

	}
	
	public List<String> getSysRoleModelUuidsByDeptUuid(String deptUuid){
		StringBuffer hql = new StringBuffer(
				"select o.uuid from SysRoleModel o where 1=1 ");
		hql.append(" and o.deptUuid = :deptUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();
	}

	/**
	 * 获得某个管理员已经关联的角色
	 * 
	 * @param managerUuid
	 * @return
	 */
	public List<SysRoleModel> getSysRoleModelByManagerUuid(String managerUuid) {
		StringBuffer hql = new StringBuffer(
				"select o from SysRoleModel o ,SysManagerModel smm ,SysManagerRoleRelModel srrm where 1=1 ");
		hql.append(" and o.uuid = srrm.roleUuid");
		hql.append(" and smm.uuid = srrm.managerUuid");
		hql.append(" and smm.uuid = :managerUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("managerUuid", managerUuid);

		return q.list();

	}
	
	public List<String> getSysRoleModelUuidByManagerUuid(String managerUuid){
		StringBuffer hql = new StringBuffer(
				"select o.uuid from SysRoleModel o ,SysManagerModel smm ,SysManagerRoleRelModel srrm where 1=1 ");
		hql.append(" and o.uuid = srrm.roleUuid");
		hql.append(" and smm.uuid = srrm.managerUuid");
		hql.append(" and smm.uuid = :managerUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("managerUuid", managerUuid);

		return q.list();
	}
}

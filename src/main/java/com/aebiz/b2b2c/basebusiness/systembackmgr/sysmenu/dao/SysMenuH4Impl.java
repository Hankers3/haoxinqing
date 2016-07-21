package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Repository
public class SysMenuH4Impl extends BaseH4Impl<SysMenuModel, SysMenuQueryModel>
		implements SysMenuDAO {

	public void setDataDeleteFlag(String uuid, int delFlag) {
		String hql = "update SysMenuModel o set o.delFlag=:delFlag where o.uuid=:uuid";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		map.put("delFlag", delFlag);

		super.exeUpdate(hql, map);
	}

	public List<SysMenuModel> getUserSysMenus(String userUuid) {
		String hql = "select o from SysMenuModel o where "
				+ "exists "
				+ "(select p.name from SysPermitModel p,SysRolePermitRelModel srp,SysRoleModel sr,SysManagerRoleRelModel smr,SysManagerModel sm "
				+ " where p.uuid=srp.permitUuid and srp.roleUuid=sr.uuid and sr.uuid=smr.roleUuid and smr.managerUuid=sm.uuid and sm.uuid=:userUuid and p.expression=:allMenu "
				+ ")"
				+ " or "
				+ " o.uuid in( "
				+ " select p.belongToMenuUuid from SysPermitModel p,SysRolePermitRelModel srp,SysRoleModel sr,SysManagerRoleRelModel smr,SysManagerModel sm "
				// +
				// " where p.uuid=srp.permitUuid and srp.roleUuid=sr.uuid and sr.uuid=smr.roleUuid and smr.managerUuid=sm.uuid and sm.uuid=:userUuid and p.expression=:viewMenu"
				+ " where p.uuid=srp.permitUuid and srp.roleUuid=sr.uuid and sr.uuid=smr.roleUuid and smr.managerUuid=sm.uuid and sm.uuid=:userUuid "
				+ ") " + " order by o.showIndex asc";

		Query q = this.getH4Session().createQuery(hql);
		q.setString("userUuid", userUuid);
		// q.setString("viewMenu", "sysback:menu:view");
		q.setString("allMenu", "sysback:menu:*");
		return q.list();
	}

	public List<String> getUserSysMenuUuids(String userUuid) {
		String hql = "select distinct(o.uuid) from SysMenuModel o where "
				+ "exists "
				+ "(select p.name from SysPermitModel p,SysRolePermitRelModel srp,SysRoleModel sr,SysManagerRoleRelModel smr,SysManagerModel sm "
				+ " where p.uuid=srp.permitUuid and srp.roleUuid=sr.uuid and sr.uuid=smr.roleUuid and smr.managerUuid=sm.uuid and sm.uuid=:userUuid and p.expression=:allMenu "
				+ ")"
				+ " or "
				+ " o.uuid in( "
				+ " select smm.uuid from SysMenuModel smm,SysRoleMenuRelModel srp,SysRoleModel sr,SysManagerRoleRelModel smr,SysManagerModel sm "
				// +
				// " select p.belongToMenuUuid from SysPermitModel p,SysRolePermitRelModel srp,SysRoleModel sr,SysManagerRoleRelModel smr,SysManagerModel sm "
				// +
				// " where p.uuid=srp.permitUuid and srp.roleUuid=sr.uuid and sr.uuid=smr.roleUuid and smr.managerUuid=sm.uuid and sm.uuid=:userUuid and p.expression=:viewMenu"
				+ " where smm.uuid=srp.menuUuid and srp.roleUuid=sr.uuid and sr.uuid=smr.roleUuid and smr.managerUuid=sm.uuid and sm.uuid=:userUuid "
				+ ") " + " order by o.showIndex asc";

		Query q = this.getH4Session().createQuery(hql);
		q.setString("userUuid", userUuid);
		// q.setString("viewMenu", "sysback:menu:view");
		q.setString("allMenu", "sysback:menu:*");

		List<String> menuUuids = q.list();
		if (menuUuids != null) {
			System.out.println(menuUuids.size());
		}
		return menuUuids;
	}

	/**
	 * 查询一级菜单
	 * 
	 * 一级菜单的parentUuid=-1
	 */
	public List<SysMenuModel> getTopMenus() {

		StringBuffer hql = new StringBuffer(
				"select o from SysMenuModel o where 1=1");

		hql.append(" and o.parentMenuUuid=:parentMenuUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("parentMenuUuid", "-1");

		return q.list();
	}

	public List<String> getTopMenuUuids() {
		StringBuffer hql = new StringBuffer(
				"select o.uuid from SysMenuModel o where 1=1");

		hql.append(" and o.parentMenuUuid=:parentMenuUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("parentMenuUuid", "-1");

		return q.list();
	}

	/**
	 * 通过菜单的uuid查询菜单的中文名称
	 * 
	 */
	public String getMenuNameByUuid(String menuUuid) {

		StringBuffer hql = new StringBuffer(
				"select o.name from SysMenuModel o where 1=1");
		hql.append(" and o.uuid=:menuUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("menuUuid", menuUuid);

		List list = q.list();
		String menuName = "";
		if (list != null && list.size() > 0) {
			menuName = (String) list.get(0);
		}
		return menuName;
	}

	/**
	 * 获得某个菜单的子菜单
	 * 
	 * 现在的拼凑方式是：一级菜单|二级菜单|三级菜单
	 * 
	 * 如果需要查子的规则是以本菜单编号结尾的 ,查询规则是 '%parentUuid'
	 * 
	 * @return
	 */
	public List<SysMenuModel> getSubMenus(String parentUuid) {

		StringBuffer hql = new StringBuffer(
				"select o from SysMenuModel o where 1=1");
		hql.append(" and o.parentMenuUuid like :parentMenuUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("parentMenuUuid", "%" + parentUuid);

		return q.list();
	}

	public List<String> getSubMenuUuids(String parentUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.uuid from SysMenuModel o where 1=1");
		hql.append(" and o.parentMenuUuid like :parentMenuUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("parentMenuUuid", "%" + parentUuid);

		return q.list();
	}

	/**
	 * 重写父类的方法，重新排序
	 * 
	 * @param qm
	 * @return
	 */
	protected String getAppendHql(SysMenuQueryModel qm) {
		return " order by o.opeTime desc ";
	}

	/**
	 * 查询是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public SysMenuModel getMenuByMenuIdAndUuid(String uuid, String menuId) {
		String hql = "select o from SysMenuModel o where 1=1 ";
		hql += " and o.id = :menuId";

		if (!StringUtil.isEmpty(uuid)) {
			hql += " and o.uuid <> :menuUuid";
		}

		Query q = this.getH4Session().createQuery(hql);
		if (!StringUtil.isEmpty(uuid)) {
			q.setString("menuUuid", uuid);
		}
		q.setString("menuId", menuId);

		List<SysMenuModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public String getMenuUuidByMenuIdAndUuid(String uuid, String menuId) {
		String hql = "select o.uuid from SysMenuModel o where 1=1 ";
		hql += " and o.id = :menuId";

		if (!StringUtil.isEmpty(uuid)) {
			hql += " and o.uuid <> :menuUuid";
		}

		Query q = this.getH4Session().createQuery(hql);
		if (!StringUtil.isEmpty(uuid)) {
			q.setString("menuUuid", uuid);
		}
		q.setString("menuId", menuId);

		List<String> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 查询某个部门所拥有的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysMenuModel> getDeptSysMenus(String deptUuid) {
		StringBuffer sb = new StringBuffer(
				"select smm from SysMenuModel smm ,SysDeptMenuRelModel sdmrm where 1=1");
		sb.append(" and smm.uuid = sdmrm.menuUuid");

		sb.append(" and sdmrm.deptUuid = :deptUuid");

		sb.append(" order by smm.showIndex asc ");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();
	}

	public List<String> getDeptSysMenuUuids(String deptUuid) {
		StringBuffer sb = new StringBuffer(
				"select smm.uuid from SysMenuModel smm ,SysDeptMenuRelModel sdmrm where 1=1");
		sb.append(" and smm.uuid = sdmrm.menuUuid");

		sb.append(" and sdmrm.deptUuid = :deptUuid");

		sb.append(" order by smm.showIndex asc ");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("deptUuid", deptUuid);

		return q.list();
	}
}

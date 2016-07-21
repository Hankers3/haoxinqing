package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Repository
public class StorePlatMenuH4Impl extends
		BaseH4Impl<StorePlatMenuModel, StorePlatMenuQueryModel> implements
		StorePlatMenuDAO {

	/**
	 * 查询一级菜单
	 * 
	 * 一级菜单的parentUuid=-1
	 */
	public List<StorePlatMenuModel> getTopMenus() {

		StringBuffer hql = new StringBuffer(
				"select o from StorePlatMenuModel o where 1=1");

		hql.append(" and o.parentMenuUuid=:parentMenuUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("parentMenuUuid", "-1");

		return q.list();
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
	public List<StorePlatMenuModel> getSubMenus(String parentUuid) {

		StringBuffer hql = new StringBuffer(
				"select o from StorePlatMenuModel o where 1=1");
		hql.append(" and o.parentMenuUuid like :parentMenuUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("parentMenuUuid", "%" + parentUuid);

		return q.list();
	}

	/**
	 * 查询是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public StorePlatMenuModel getMenuByMenuIdAndUuid(String uuid, String menuId) {
		String hql = "select o from StorePlatMenuModel o where 1=1 ";
		hql += " and o.id = :menuId";

		if (!StringUtil.isEmpty(uuid)) {
			hql += " and o.uuid <> :menuUuid";
		}

		Query q = this.getH4Session().createQuery(hql);
		if (!StringUtil.isEmpty(uuid)) {
			q.setString("menuUuid", uuid);
		}
		q.setString("menuId", menuId);

		List<StorePlatMenuModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 根据菜单的UUID获得菜单的名称
	 * 
	 * @param menuUuid
	 * @return
	 */
	public String getMenuNameByUuid(String menuUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.name from StorePlatMenuModel o where 1=1");
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
}

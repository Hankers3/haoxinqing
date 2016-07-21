package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.dao;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;

public interface StorePlatMenuDAO extends
		BaseDAO<StorePlatMenuModel, StorePlatMenuQueryModel> {

	/**
	 * 获得一级菜单
	 * 
	 * @return
	 */
	public List<StorePlatMenuModel> getTopMenus();

	/**
	 * 获得某个菜单的子菜单
	 * 
	 * @return
	 */
	public List<StorePlatMenuModel> getSubMenus(String parentUuid);

	/**
	 * 查询是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public StorePlatMenuModel getMenuByMenuIdAndUuid(String uuid, String menuId);

	/**
	 * 根据菜单的UUID获得菜单的名称
	 * 
	 * @param menuUuid
	 * @return
	 */
	public String getMenuNameByUuid(String menuUuid);
}
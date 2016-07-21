package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.service;

import java.util.List;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

public interface StorePlatMenuService extends
		BaseService<StorePlatMenuModel, StorePlatMenuQueryModel> {

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
	 * 验证菜单的编号是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public boolean check(String uuid, String menuId);

	/**
	 * 通过菜单UUID查名称
	 * 
	 * @param sysMenuUuid
	 * @return
	 */
	public String getSysMenuNameByUuid(String sysMenuUuid);

	/**
	 * 通过菜单ID获得父ID的组合,组合的拼凑方法如下：
	 * 
	 * 一级菜单|二级菜单|三级菜单
	 * 
	 * @param menuUuid
	 * @return
	 */
	public String getParentUuids(String menuUuid);

	/**
	 * 获得父级菜单的名称，供选择
	 * 
	 * 一级菜单>二级菜单>三级菜单
	 * 
	 * @param menuUuid
	 * @return
	 */
	public String getParentNames(String menuUuid);

}

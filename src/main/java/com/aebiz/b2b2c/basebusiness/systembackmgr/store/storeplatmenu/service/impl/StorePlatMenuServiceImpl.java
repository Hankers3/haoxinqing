package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.dao.StorePlatMenuDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.service.StorePlatMenuService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class StorePlatMenuServiceImpl extends
		BaseServiceImpl<StorePlatMenuModel, StorePlatMenuQueryModel> implements
		StorePlatMenuService {
	private StorePlatMenuDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(StorePlatMenuDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StorePlatMenuModel m) {
		m.setUuid(us.getNextUuid("StorePlatMenu", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(setParentUuid(m));
		return ret;
	}

	@Override
	public void update(StorePlatMenuModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(setParentUuid(m));
	}

	@Override
	public void delete(StorePlatMenuModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 获得一级菜单
	 * 
	 * 一级菜单的parentMenuUuid为空
	 * 
	 * @return
	 */
	public List<StorePlatMenuModel> getTopMenus() {
		return myDao.getTopMenus();
	}

	/**
	 * 获得某个菜单的子菜单
	 * 
	 * @return
	 */
	public List<StorePlatMenuModel> getSubMenus(String parentUuid) {
		return myDao.getSubMenus(parentUuid);
	}

	/**
	 * 验证菜单的编号是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public boolean check(String uuid, String menuId) {
		StorePlatMenuModel srm = myDao.getMenuByMenuIdAndUuid(uuid, menuId);
		if (srm == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过菜单UUID查名称
	 * 
	 * @param sysMenuUuid
	 * @return
	 */
	public String getSysMenuNameByUuid(String sysMenuUuid) {
		return myDao.getMenuNameByUuid(sysMenuUuid);
	}

	/**
	 * 通过菜单ID获得父ID的组合,组合的拼凑方法如下：
	 * 
	 * 一级菜单|二级菜单|三级菜单
	 * 
	 * @param menuUuid
	 * @return
	 */
	public String getParentUuids(String menuUuid) {
		// 获得当前menuUuid的菜单

		StorePlatMenuModel smm = this.getByUuid(menuUuid);

		if (smm == null) {
			return "";
		}

		String parentUuids = smm.getUuid();

		int i = 0;
		// 递归到最后第一级
		while (smm != null) {
			if ("-1".equals(smm.getParentMenuUuid())) {
				break;
			} else {
				parentUuids = smm.getParentMenuUuid() + "|" + parentUuids;
			}

			smm = this.getByUuid(smm.getParentMenuUuid());

			// 防止父编号出错，死循环
			i++;
			if (i >= 3) {
				break;
			}
		}

		if (parentUuids.endsWith("|")) {
			parentUuids = parentUuids.substring(0, parentUuids.length() - 1);
		}

		return parentUuids;
	}

	/**
	 * 获得父级菜单的名称，供选择
	 * 
	 * 一级菜单>二级菜单>三级菜单
	 * 
	 * @param menuUuid
	 * @return
	 */
	public String getParentNames(String menuUuid) {
		// 获得当前menuUuid的菜单
		StorePlatMenuModel smm = this.getByUuid(menuUuid);
		if (smm == null) {
			return "";
		}

		String parentNames = smm.getName();

		int i = 0;
		// 递归到最后第一级
		while (smm != null) {
			if ("-1".equals(smm.getParentMenuUuid())) {
				break;
			}

			smm = this.getByUuid(smm.getParentMenuUuid());
			if (smm != null) {
				parentNames = smm.getName() + ">" + parentNames;
			}

			// 防止父编号出错，死循环
			i++;
			if (i >= 3) {
				break;
			}
		}

		if (parentNames.endsWith(">")) {
			parentNames = parentNames.substring(0, parentNames.length() - 1);
		}

		return parentNames;
	}

	/**
	 * *************************************************************************
	 * *************************************************************************
	 */

	/**
	 * 通过页面选择的父菜单，设置父菜单
	 * 
	 * 父菜单拼凑规则：一级菜单|二级菜单|三级菜单
	 * 
	 * @param smm
	 * @return
	 */
	private StorePlatMenuModel setParentUuid(StorePlatMenuModel smm) {

		if (StringUtil.isEmpty(smm.getMenuUuidLevel1())
				&& StringUtil.isEmpty(smm.getMenuUuidLevel2())
				&& StringUtil.isEmpty(smm.getMenuUuidLevel3())) {
			smm.setParentMenuUuid("-1");
		} else {
			if (!StringUtil.isEmpty(smm.getMenuUuidLevel3())) {
				smm.setParentMenuUuid(smm.getMenuUuidLevel3());
			} else if (!StringUtil.isEmpty(smm.getMenuUuidLevel2())) {
				smm.setParentMenuUuid(smm.getMenuUuidLevel2());
			} else if (!StringUtil.isEmpty(smm.getMenuUuidLevel1())) {
				smm.setParentMenuUuid(smm.getMenuUuidLevel1());
			}
		}

		return smm;
	}
}
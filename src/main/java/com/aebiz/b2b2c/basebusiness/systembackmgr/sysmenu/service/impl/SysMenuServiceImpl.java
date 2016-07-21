package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.dao.SysMenuDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.service.SysMenuService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class SysMenuServiceImpl extends
		BaseServiceImpl<SysMenuModel, SysMenuQueryModel> implements
		SysMenuService {
	private SysMenuDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(SysMenuDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SysMenuModel m) {
		m.setUuid(us.getNextUuid("SysMenu", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		// 设置父菜单
		String ret = super.create(setParentUuid(m));
		return ret;
	}

	@Override
	public void update(SysMenuModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		super.update(setParentUuid(m));
	}

	@Override
	public void delete(SysMenuModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 查询某个用户所拥有的菜单
	 * 
	 * @param userUuid
	 * @return
	 */
	public List<SysMenuModel> getUserSysMenus(String userUuid) {
		return myDao.getUserSysMenus(userUuid);
	}

	/**
	 * 获得一级菜单
	 * 
	 * 一级菜单的parentMenuUuid为空
	 * 
	 * @return
	 */
	public List<SysMenuModel> getTopMenus() {
		return myDao.getTopMenus();
	}

	/**
	 * 获得某个菜单的子菜单
	 * 
	 * @return
	 */
	public List<SysMenuModel> getSubMenus(String parentUuid) {
		return myDao.getSubMenus(parentUuid);
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
	 * 验证菜单的编号是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public boolean check(String uuid, String menuId) {
		SysMenuModel srm = myDao.getMenuByMenuIdAndUuid(uuid, menuId);
		if (srm == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询某个部门所拥有的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysMenuModel> getDeptSysMenus(String deptUuid) {
		return myDao.getDeptSysMenus(deptUuid);
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
		SysMenuModel smm = this.getByUuid(menuUuid);

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
		SysMenuModel smm = this.getByUuid(menuUuid);
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
	private SysMenuModel setParentUuid(SysMenuModel smm) {
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
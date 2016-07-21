package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysMenuCacheImpl extends
		BaseMemcachedCache<SysMenuModel, SysMenuQueryModel> implements
		SysMenuDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysMenuDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysMenuCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_MENU);
	}

	@Override
	public List<SysMenuModel> getUserSysMenus(String userUuid) {
		List<String> uuids = this.myDao.getUserSysMenuUuids(userUuid);
		List<SysMenuModel> list = new ArrayList<SysMenuModel>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysMenuModel m = super.getByUuid(uuid);
				if (m != null) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public List<SysMenuModel> getTopMenus() {
		List<String> uuids = this.myDao.getTopMenuUuids();
		List<SysMenuModel> list = new ArrayList<SysMenuModel>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysMenuModel m = super.getByUuid(uuid);
				if (m != null) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public List<SysMenuModel> getSubMenus(String parentUuid) {
		List<String> uuids = this.myDao.getSubMenuUuids(parentUuid);
		List<SysMenuModel> list = new ArrayList<SysMenuModel>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysMenuModel m = super.getByUuid(uuid);
				if (m != null) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public String getMenuNameByUuid(String menuUuid) {
		SysMenuModel m = super.getByUuid(menuUuid);
		if (m != null) {
			return m.getName();
		}
		return this.myDao.getMenuNameByUuid(menuUuid);
	}

	@Override
	public SysMenuModel getMenuByMenuIdAndUuid(String uuid, String menuId) {
		String id = this.myDao.getMenuUuidByMenuIdAndUuid(uuid, menuId);
		if (StringUtil.isEmpty(id)) {
			return null;
		}

		return super.getByUuid(id);
	}

	@Override
	public List<SysMenuModel> getDeptSysMenus(String deptUuid) {
		List<String> uuids = this.myDao.getDeptSysMenuUuids(deptUuid);
		List<SysMenuModel> list = new ArrayList<SysMenuModel>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysMenuModel m = super.getByUuid(uuid);
				if (m != null) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public List<String> getUserSysMenuUuids(String userUuid) {
		return this.myDao.getUserSysMenuUuids(userUuid);
	}

	@Override
	public List<String> getTopMenuUuids() {
		return this.myDao.getTopMenuUuids();
	}

	@Override
	public List<String> getSubMenuUuids(String parentUuid) {
		return this.myDao.getSubMenuUuids(parentUuid);
	}

	@Override
	public String getMenuUuidByMenuIdAndUuid(String uuid, String menuId) {
		return this.myDao.getMenuUuidByMenuIdAndUuid(uuid, menuId);
	}

	@Override
	public List<String> getDeptSysMenuUuids(String deptUuid) {
		return this.myDao.getDeptSysMenuUuids(deptUuid);
	}

}

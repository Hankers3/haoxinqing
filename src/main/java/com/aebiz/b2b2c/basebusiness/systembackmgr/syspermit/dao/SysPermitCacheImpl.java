package com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysPermitCacheImpl extends
		BaseMemcachedCache<SysPermitModel, SysPermitQueryModel> implements
		SysPermitDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysPermitDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysPermitCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_PERMIT);
	}

	@Override
	public Set<String> getPermitExprsByRoleIds(Set<String> roleIds) {
		return this.myDao.getPermitExprsByRoleIds(roleIds);
	}

	@Override
	public List<SysPermitModel> getRolesSysPermit(String roleUuid) {
		List<String> uuids = this.myDao.getRolesSysPermitUuids(roleUuid);
		List<SysPermitModel> list = new ArrayList<SysPermitModel>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysPermitModel m = super.getByUuid(uuid);
				if (m != null) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public SysPermitModel getSysPermitByMenuId(String bussinessType,
			String bussinessUuid) {
		String uuid = this.myDao.getSysPermitUuidByMenuId(bussinessType,
				bussinessUuid);
		return super.getByUuid(uuid);
	}

	@Override
	public List<SysPermitModel> getAllOperteSysPermit(String bussinessType) {
		List<String> uuids = this.myDao
				.getAllOperteSysPermitUuids(bussinessType);
		List<SysPermitModel> list = new ArrayList<SysPermitModel>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysPermitModel m = super.getByUuid(uuid);
				if (m != null) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public List<SysPermitModel> getDeptSysPermits(String deptUuid) {
		List<String> uuids = this.myDao.getDeptSysPermitUuids(deptUuid);
		List<SysPermitModel> list = new ArrayList<SysPermitModel>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysPermitModel m = super.getByUuid(uuid);
				if (m != null) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public List<String> getRolesSysPermitUuids(String roleUuid) {
		return this.myDao.getRolesSysPermitUuids(roleUuid);
	}

	@Override
	public String getSysPermitUuidByMenuId(String bussinessType,
			String bussinessUuid) {
		return this.myDao
				.getSysPermitUuidByMenuId(bussinessType, bussinessUuid);
	}

	@Override
	public List<String> getAllOperteSysPermitUuids(String bussinessType) {
		return this.myDao.getAllOperteSysPermitUuids(bussinessType);
	}

	@Override
	public List<String> getDeptSysPermitUuids(String deptUuid) {
		return this.myDao.getDeptSysPermitUuids(deptUuid);
	}

}

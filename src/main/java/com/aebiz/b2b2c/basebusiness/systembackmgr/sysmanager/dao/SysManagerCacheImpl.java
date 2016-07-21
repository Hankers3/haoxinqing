package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysManagerCacheImpl extends
		BaseMemcachedCache<SysManagerModel, SysManagerQueryModel> implements
		SysManagerDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysManagerDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysManagerCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_MANAGER);
	}

	@Override
	public SysManagerModel getManagerByManagerIdAndUuid(String uuid,
			String managerId) {
		String id = this.myDao
				.getManagerUuidByManagerIdAndUuid(uuid, managerId);
		if(StringUtil.isEmpty(id)){
			return null;
		}
		return super.getByUuid(id);
	}

	@Override
	public String getSysManagerNameByUuid(String uuid) {
		SysManagerModel m = super.getByUuid(uuid);
		if (m != null) {
			return m.getName();
		}
		return this.myDao.getSysManagerNameByUuid(uuid);
	}

	@Override
	public String getManagerUuidByManagerIdAndUuid(String uuid, String managerId) {
		return this.myDao.getManagerUuidByManagerIdAndUuid(uuid, managerId);
	}

	@Override
	public SysManagerModel getSysManagerModelByLoginNameAndPassword(
			String loginName, String pwd) {
	
		return this.myDao.getSysManagerModelByLoginNameAndPassword(loginName, pwd);
	}

}

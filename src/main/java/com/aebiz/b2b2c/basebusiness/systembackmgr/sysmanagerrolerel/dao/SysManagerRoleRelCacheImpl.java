package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysManagerRoleRelCacheImpl extends
		BaseMemcachedCache<SysManagerRoleRelModel, SysManagerRoleRelQueryModel>
		implements SysManagerRoleRelDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysManagerRoleRelDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysManagerRoleRelCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_MANAGER_ROLE_REL);
	}

	@Override
	public void removeReledRelsByManagerUuid(String managerUuid) {
		List<String> uuids = this.myDao
				.getReledRelUuidsByManagerUuid(managerUuid);
		if(uuids != null && uuids.size() > 0){
			for(String uuid:uuids){
				SysManagerRoleRelModel m = super.getByUuid(uuid);
				if(m != null){
					super.delete(m);
				}
			}
		}
	}

	@Override
	public List<String> getReledRelUuidsByManagerUuid(String managerUuid) {
		return this.myDao.getReledRelUuidsByManagerUuid(managerUuid);
	}

	@Override
	public List<String> getSysManagerRoleRelByRoleUuid(String roleUuid) {
		return this.myDao.getSysManagerRoleRelByRoleUuid(roleUuid);
	}

}

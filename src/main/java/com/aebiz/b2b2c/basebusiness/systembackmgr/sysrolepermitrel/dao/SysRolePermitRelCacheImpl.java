package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.vo.SysRolePermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.vo.SysRolePermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysRolePermitRelCacheImpl extends
		BaseMemcachedCache<SysRolePermitRelModel, SysRolePermitRelQueryModel>
		implements SysRolePermitRelDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysRolePermitRelDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysRolePermitRelCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_ROLE_PERMIT_REL);
	}

	@Override
	public List<SysRolePermitRelModel> getAllPermitRelsByRoleUuid(
			String roleUuid) {
		List<String> uuids = this.myDao
				.getAllPermitRelUuidsByRoleUuid(roleUuid);
		List<SysRolePermitRelModel> list = new ArrayList<SysRolePermitRelModel>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysRolePermitRelModel m = super.getByUuid(uuid);
				if (m != null) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public void removeAllRelPermitsByRoleUuid(String roleUuid) {
		List<String> uuids = this.myDao
				.getAllPermitRelUuidsByRoleUuid(roleUuid);
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysRolePermitRelModel m = super.getByUuid(uuid);
				if (m != null) {
					super.delete(m);
				}
			}
		}
	}

	@Override
	public List<String> getAllPermitRelUuidsByRoleUuid(String roleUuid) {
		return this.myDao.getAllPermitRelUuidsByRoleUuid(roleUuid);
	}

}

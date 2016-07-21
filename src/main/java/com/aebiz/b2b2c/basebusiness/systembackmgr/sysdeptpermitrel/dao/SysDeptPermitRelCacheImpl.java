package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysDeptPermitRelCacheImpl extends
		BaseMemcachedCache<SysDeptPermitRelModel, SysDeptPermitRelQueryModel>
		implements SysDeptPermitRelDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysDeptPermitRelDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysDeptPermitRelCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_DEPT_PERMIT_REL);
	}

	@Override
	public List<SysDeptPermitRelModel> getAllPermitRelsByDeptUuid(
			String deptUuid) {
		List<String> uuids = this.myDao
				.getAllPermitRelUuidsByDeptUuid(deptUuid);
		List<SysDeptPermitRelModel> list = new ArrayList<SysDeptPermitRelModel>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysDeptPermitRelModel m = super.getByUuid(uuid);
				if (m != null) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public void removeAllRelPermitsByDeptUuid(String deptUuid) {
		List<String> uuids = this.myDao
				.getAllPermitRelUuidsByDeptUuid(deptUuid);
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				SysDeptPermitRelModel m = super.getByUuid(uuid);
				if (m != null) {
					super.delete(m);
				}
			}
		}
	}

	@Override
	public List<String> getAllPermitRelUuidsByDeptUuid(String deptUuid) {
		return this.myDao.getAllPermitRelUuidsByDeptUuid(deptUuid);
	}

}

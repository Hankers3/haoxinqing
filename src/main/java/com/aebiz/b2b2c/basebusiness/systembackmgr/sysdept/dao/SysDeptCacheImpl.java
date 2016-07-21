package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysDeptCacheImpl extends
		BaseMemcachedCache<SysDeptModel, SysDeptQueryModel> implements
		SysDeptDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysDeptDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysDeptCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_DEPT);
	}

	@Override
	public SysDeptModel getDeptByDeptIdAndUuid(String uuid, String deptName) {
		String id = this.myDao.getDeptUuidByDeptIdAndUuid(uuid, deptName);
		if(StringUtil.isEmpty(id)){
			return null;
		}
		return super.getByUuid(id);
	}

	@Override
	public String getDeptNameByDeptUuid(String deptUuid) {
		SysDeptModel m = super.getByUuid(deptUuid);
		if (m != null) {
			return m.getDepartmentName();
		}
		return this.myDao.getDeptNameByDeptUuid(deptUuid);
	}

	@Override
	public String getDeptUuidByDeptIdAndUuid(String uuid, String deptName) {
		return this.myDao.getDeptUuidByDeptIdAndUuid(uuid, deptName);
	}

}

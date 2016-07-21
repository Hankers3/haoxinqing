package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysDeptMenuRelCacheImpl extends
		BaseMemcachedCache<SysDeptMenuRelModel, SysDeptMenuRelQueryModel>
		implements SysDeptMenuRelDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysDeptMenuRelDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysDeptMenuRelCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_DEPT_MENU_REL);
	}

	@Override
	public List<SysDeptMenuRelModel> getAllRelsByDeptUuid(String deptUuid) {
		List<String> uuids = this.myDao.getAllRelUuidsByDeptUuid(deptUuid);
		List<SysDeptMenuRelModel> list = new ArrayList<SysDeptMenuRelModel>();
		if(uuids != null && uuids.size() > 0){
			for(String uuid:uuids){
				SysDeptMenuRelModel m = super.getByUuid(uuid);
				if(m != null){
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public void removeAllRelMenusByDeptUuid(String deptUuid) {
		List<String> uuids = this.myDao.getAllRelUuidsByDeptUuid(deptUuid);
		List<SysDeptMenuRelModel> list = new ArrayList<SysDeptMenuRelModel>();
		if(uuids != null && uuids.size() > 0){
			for(String uuid:uuids){
				SysDeptMenuRelModel m = super.getByUuid(uuid);
				if(m != null){
					super.delete(m);
				}
			}
		}
	}

	@Override
	public List<String> getAllRelUuidsByDeptUuid(String deptUuid) {
		return this.myDao.getAllRelUuidsByDeptUuid(deptUuid);
	}

}

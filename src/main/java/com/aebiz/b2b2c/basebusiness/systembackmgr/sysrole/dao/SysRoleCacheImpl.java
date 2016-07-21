package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysRoleCacheImpl extends
		BaseMemcachedCache<SysRoleModel, SysRoleQueryModel> implements
		SysRoleDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysRoleDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysRoleCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_ROLE);
	}

	@Override
	public Set<String> getRoleIdsByUserId(String userId) {
		return this.myDao.getRoleIdsByUserId(userId);
	}

	@Override
	public SysRoleModel getRoleByRoleIdAndUuid(String uuid, String roleId) {
		String id = this.myDao.getRoleUuidByRoleIdAndUuid(uuid, roleId);
		if(StringUtil.isEmpty(id)){
			return null;
		}
		return super.getByUuid(id);
	}

	@Override
	public List<SysRoleModel> getSysRoleModelsByDeptUuid(String deptUuid) {
		List<String> uuids = this.myDao
				.getSysRoleModelUuidsByDeptUuid(deptUuid);
		List<SysRoleModel> list = new ArrayList<SysRoleModel>();
		if(uuids != null && uuids.size() > 0){
			for(String uuid:uuids){
				SysRoleModel m = super.getByUuid(uuid);
				if(m != null){
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public List<SysRoleModel> getSysRoleModelByManagerUuid(String managerUuid) {
		List<String> uuids = this.myDao
				.getSysRoleModelUuidByManagerUuid(managerUuid);
		List<SysRoleModel> list = new ArrayList<SysRoleModel>();
		if(uuids != null && uuids.size() > 0){
			for(String uuid:uuids){
				SysRoleModel m = super.getByUuid(uuid);
				if(m != null){
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public String getRoleUuidByRoleIdAndUuid(String uuid, String roleId) {
		return this.myDao.getRoleUuidByRoleIdAndUuid(uuid, roleId);
	}

	@Override
	public List<String> getSysRoleModelUuidsByDeptUuid(String deptUuid) {
		return this.myDao.getSysRoleModelUuidsByDeptUuid(deptUuid);
	}

	@Override
	public List<String> getSysRoleModelUuidByManagerUuid(String managerUuid) {
		return this.myDao.getSysRoleModelUuidByManagerUuid(managerUuid);
	}

}

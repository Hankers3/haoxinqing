package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.common.SystembackCacheConstant;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelQueryModel;
import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SysRoleMenuRelCacheImpl extends
		BaseMemcachedCache<SysRoleMenuRelModel, SysRoleMenuRelQueryModel>
		implements SysRoleMenuRelDAO {
	@Resource(name = SystembackCacheConstant.SYSTEMBACK_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SysRoleMenuRelDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public SysRoleMenuRelCacheImpl() {
		super(SystembackCacheConstant.SYSTEMBACK_SYS_ROLE_MENU_REL);
	}

	@Override
	public List<SysRoleMenuRelModel> getAllRelsByRoleUuid(String roleUuid) {
		List<String> uuids = this.myDao.getAllRelUuidsByRoleUuid(roleUuid);
		List<SysRoleMenuRelModel> list = new ArrayList<SysRoleMenuRelModel>();
		if(uuids != null && uuids.size() > 0){
			for(String uuid:uuids){
				SysRoleMenuRelModel m = super.getByUuid(uuid);
				if(m != null){
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public void removeAllRelMenusByRoleUuid(String roleUuid) {
		List<String> uuids = this.myDao.getAllRelUuidsByRoleUuid(roleUuid);
		if(uuids != null && uuids.size() > 0){
			for(String uuid:uuids){
				SysRoleMenuRelModel m = super.getByUuid(uuid);
				if(m != null){
					super.delete(m);
				}
			}
		}
	}
	
	/**
	 * 
	 * 通过角色编号查找已经关联的菜单的uuid
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<String> getAllRelMenuUuidsByRoleUuid(String roleUuid){
		return myDao.getAllRelMenuUuidsByRoleUuid(roleUuid);
	}

	@Override
	public List<String> getAllRelUuidsByRoleUuid(String roleUuid) {
		return this.myDao.getAllRelUuidsByRoleUuid(roleUuid);
	}

}

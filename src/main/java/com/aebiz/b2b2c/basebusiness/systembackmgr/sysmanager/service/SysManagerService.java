package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerQueryModel;

public interface SysManagerService extends
		BaseService<SysManagerModel, SysManagerQueryModel> {

	/**
	 * 通过登录名查找用户
	 * 
	 * @param id
	 * @return
	 */
	public SysManagerModel getById(String id);

	/**
	 * 验证管理员登录名是否已经存在
	 * 
	 * @return
	 */
	public boolean check(String uuid, String managerId);
	
	/**
	 * 通过编号获取管理员名
	 * 
	 * @param id
	 * @return
	 */
	public String getSysManagerNameByUuid(String uuid);
	public SysManagerModel getSysManagerModelByLoginNameAndPassword( String loginName,String pwd);
}

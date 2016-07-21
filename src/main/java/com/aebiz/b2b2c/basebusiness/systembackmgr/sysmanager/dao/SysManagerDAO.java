package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.dao;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;

public interface SysManagerDAO extends
		BaseDAO<SysManagerModel, SysManagerQueryModel> {

	/**
	 * 查询是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public SysManagerModel getManagerByManagerIdAndUuid(String uuid,
			String managerId);
	
	/**
	 * 获取管理员登录用户名
	 * @param uuid
	 * @return
	 */
	public String getSysManagerNameByUuid(String uuid);
	/**
     * 根据用户名和密码获取系统管理员
     * @param uuid
     * @return
     */
    public SysManagerModel getSysManagerModelByLoginNameAndPassword(String loginName, String pwd);
    
	public String getManagerUuidByManagerIdAndUuid(String uuid, String managerId);

    
}
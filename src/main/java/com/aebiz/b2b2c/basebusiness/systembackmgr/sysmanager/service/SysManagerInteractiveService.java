package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

public interface SysManagerInteractiveService extends BaseService<SysManagerModel, SysManagerQueryModel>{
	/**
	 * 获取管理员登录用户名
	 * @param uuid
	 * @return
	 */
	public String getSysManagerNameByUuid(String uuid);
}

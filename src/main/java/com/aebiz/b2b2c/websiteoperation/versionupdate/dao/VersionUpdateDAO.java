package com.aebiz.b2b2c.websiteoperation.versionupdate.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateModel;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateQueryModel;

public interface VersionUpdateDAO extends BaseDAO<VersionUpdateModel,VersionUpdateQueryModel>{
	/**
	 * 获取最发布的应用
	 * @param versionType
	 * @return
	 */
	public VersionUpdateModel getVersionUpdateModel(String versionType);
}
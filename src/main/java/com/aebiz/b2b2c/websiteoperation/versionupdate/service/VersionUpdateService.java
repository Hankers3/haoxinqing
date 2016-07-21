package com.aebiz.b2b2c.websiteoperation.versionupdate.service;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateModel;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateQueryModel;

public interface VersionUpdateService extends BaseService<VersionUpdateModel,VersionUpdateQueryModel>{
	/**
	 * 文件上传
	 * 
	 * @param  versionUpdate
	 * @param files
	 * @return
	 */
	public VersionUpdateModel uploadFile(VersionUpdateModel versionUpdate,
			MultipartFile[] files);
	/**
	 * 获取最发布的应用
	 * @param versionType
	 * @return
	 */
	public VersionUpdateModel getVersionUpdateModel(String versionType);
	
}

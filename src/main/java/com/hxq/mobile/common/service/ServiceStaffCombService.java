package com.hxq.mobile.common.service;

import org.springframework.web.multipart.MultipartFile;

import com.hxq.mobile.entity.common.ServiceStaffComb;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 医生注册信息
 *
 */
public interface ServiceStaffCombService extends SimpleEntityService {
	/**
	 * 通过会员编号获取复合model，家政员基础信息和平台家政员等级信息
	 * 
	 * @param uuid
	 */
	public ServiceStaffComb getServiceStaffComb(String uuid);
	
	/**
	 * 更新家政人员基础信息
	 * 
	 * @param customerCombModel
	 * @param imgFiles
	 */
	public void updateServicestaffBaseInfo(
			ServiceStaffComb customerComb, MultipartFile[] imgFiles,
			MultipartFile[] certFile);
}

package com.hxq.mobile.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.hxq.mobile.entity.common.ServiceStaffComb;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 医生基本(补充)信息
 */
public interface ServiceStaffInfoService extends SimpleEntityService {
	/**
	 * 根据医生ID获取基础信息
	 */
	public ServiceStaffInfo selectByServiceStaffUuid(String serviceStaffUuid);

	/**
	 * 获得名医基本信息
	 */
	public List<Map<String,Object>> selectfamousDoctors();
	
	/**
	 * 更新家政员员基础信息
	 * 
	 * @param customerModel
	 */
	public void updateServicestaffinfo(ServiceStaffComb ssc,MultipartFile[] imgFiles,MultipartFile[] certFiles) throws Exception;

}

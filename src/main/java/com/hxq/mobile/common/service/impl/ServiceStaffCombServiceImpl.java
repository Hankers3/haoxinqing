package com.hxq.mobile.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hxq.mobile.common.service.ServiceStaffCombService;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.common.ServiceStaffComb;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;

/** 
* @author  作者 E-mail: liuyang
* @date 创建时间：2016年5月28日 上午10:32:27 
* @version 2.0 
* @parameter  
* @since  
* @return  
*/
@Service("com.hxq.mobile.common.service.ServiceStaffCombService")
public class ServiceStaffCombServiceImpl extends SpringJdbcSimpleEntityService implements ServiceStaffCombService {

	/*医生基础信息service2.0*/
	@Autowired
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffService")
	private ServiceStaffService serviceStaffService;
	
	@Autowired
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffInfoService")
	private ServiceStaffInfoService serviceStaffInfoService;

	@Override
	public ServiceStaffComb getServiceStaffComb(String uuid) {
		ServiceStaffComb serviceStaffComb = new ServiceStaffComb();
		// 得到医生信息
		ServiceStaff serciceStaff= null;
		try {
			serciceStaff = (ServiceStaff) serviceStaffService.getServiceStaffByUuid(uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		serviceStaffComb.setServiceStaff(serciceStaff);
		
		
		ServiceStaffInfo serviceStaffInfo = serviceStaffInfoService.selectByServiceStaffUuid(uuid);
		serviceStaffComb.setServiceStaffInfo(serviceStaffInfo);
		

		return serviceStaffComb;
	}
	
	
	/**
	 * 更新家政人员基础信息
	 * 
	 * @param customerCombModel
	 * @param imgFiles
	 */
	@Override
	public void updateServicestaffBaseInfo(
			ServiceStaffComb customerComb, MultipartFile[] imgFiles,
			MultipartFile[] certFile){
		try {
			serviceStaffInfoService.updateServicestaffinfo(customerComb, imgFiles, certFile);
		} catch (Exception e) {
			e.printStackTrace();
		};
	
	}

	
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}
	
	@Override
	protected String getCacheName() {
		return null;
	}

	@Override
	protected String getQueryCacheName() {
		return null;
	}



}

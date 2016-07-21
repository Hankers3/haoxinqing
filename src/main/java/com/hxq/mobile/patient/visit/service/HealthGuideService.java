package com.hxq.mobile.patient.visit.service;

import java.util.List;
import java.util.Map;


import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 健康指导
 *
 */
public interface HealthGuideService extends SimpleEntityService{
	/**
	 * 根据医生和患者获取健康指导的列表
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	List<Map<String, Object>> selectHealthGuideListByCustomerUuidAndDoctorUuid(String doctorUuid,String customerUuid);
}

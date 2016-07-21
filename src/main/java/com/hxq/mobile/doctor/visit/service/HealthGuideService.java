package com.hxq.mobile.doctor.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 健康指导
 *
 */
public interface HealthGuideService extends SimpleEntityService {

	/**
	 * 获取健康指导信息
	 * @param visitRecordUuid 随访方案id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getHealthGuideByDoctorUuidAndVisitUuid(String doctorUuid,String visitUuid);


}

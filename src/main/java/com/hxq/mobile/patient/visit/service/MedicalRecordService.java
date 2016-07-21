package com.hxq.mobile.patient.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

public interface MedicalRecordService extends SimpleEntityService{
	/**
	 * 根据患者和医生id获取病例
	 * 
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	public List<Map<String,Object>> selectListByCustomerUuidAndDoctorUuid(String customerUuid, String doctorUuid);
}

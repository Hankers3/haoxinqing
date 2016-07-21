package com.hxq.mobile.patient.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

public interface VisitApplyService extends SimpleEntityService {
	/**
	 * 通过患者和申请状态查询医生id
	 * @param customerUuid
	 * @param applyState
	 * @return
	 */
	public String selectDoctorFromApply(String customerUuid, String applyState);
	
	/**
	 * 根据患者id和申请状态查询visitRecordUuid
	 * @param customerUuid
	 * @param applyState
	 * @return
	 */
	public Map<String, Object> selectCustomerFromVisitRecordUuid(String customerUuid,String applyState) ;

	/**
	 * 根据医生ID查找所有随访患者Uuid
	 * @param doctorUuid
	 * @return
	 */
	List<Map<String, Object>> selectCustomeruidByDoctorUuid(String doctorUuid) ;
}

package com.hxq.mobile.doctor.visit.service;

import java.util.Map;

import com.hxq.mobile.entity.visit.DoctorAdviceMain;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 重要医嘱
 *
 */
public interface DoctorAdviceMainService extends SimpleEntityService {

	/**
	 * 获取最新重要医嘱
	 * @param serviceStaffUuid
	 * @param customerUuid
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectLastDoctorAdviceMain(String serviceStaffUuid, String customerUuid);

	/**
	 * 第1次的重要医嘱是从随访方案中获取
	 * @param serviceStaffUuid
	 * @param customerUuid
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectDoctorAdviceMainByVisitRecord(String serviceStaffUuid, String customerUuid);

	public int insertSave2(DoctorAdviceMain bean) throws Exception;
}

package com.hxq.mobile.doctor.visit.service;

import java.util.Map;

import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 随访表单（记录）
 *
 */
public interface VisitRecordService extends SimpleEntityService {
	public Map<String, Object> selectWaitProcessedVisitList(String doctorUuid, String customerUuid,
			String applyUuid, String applyState, String createTime) throws Exception;

	public Map<String, Object> updateViewVisit(String visitRecordUuid) throws Exception;
	/**
	 * 根据医生id,患者id,delFlag,从visit_record表中取出数据
	 * @param delFlag
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 * @throws Exception
	 */
	public VisitRecord getVisitRecordByDoctoridAndCustomerId(String delFlag,String doctorUuid,String customerUuid) throws Exception;

	public Map<String, Object> countVisitRecordByDoctorUuid(String uuid) throws Exception;

	public Map<String, Object> selectVisitNumModel(String uuid) throws Exception;
}

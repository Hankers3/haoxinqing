package com.hxq.mobile.doctor.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 重要医嘱的药物/随访方案的治疗方案/随访表单的服药记录
 *
 */
public interface DoctorAdviceService extends SimpleEntityService {

	/**
	 * 获取重要医嘱下的药物列表
	 * @param mainUuid 重要医嘱id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectListByDoctorAdviceMain(String mainUuid);

	/**
	 * 获取随访方案下的治疗方案
	 * @param visitRecordUuid 随访方案id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectListByVisitPreceptAndType(String visitPreceptUuid,String type);

	/**
	 * 获取随访方案下的治疗方案
	 * @param visitRecordUuid 随访方案id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectListByVisitPrecept(String visitPreceptUuid);

	
	/**
	 * 获取随访表单(记录)下的服药记录
	 * @param visitRecordUuid 随访记录id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectListByVisitRecord(String visitRecordUuid);
}

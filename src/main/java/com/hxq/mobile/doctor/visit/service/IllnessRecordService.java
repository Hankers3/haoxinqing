package com.hxq.mobile.doctor.visit.service;


import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 病情变化记录
 *
 */
public interface IllnessRecordService extends SimpleEntityService {
	/**
	 * 通过随访记录id获取病情记录
	 * @param visitRecordUuid 随访记录id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectByVisitRecord(String visitRecordUuid) throws Exception;
}
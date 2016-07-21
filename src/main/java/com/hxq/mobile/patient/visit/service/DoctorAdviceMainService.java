package com.hxq.mobile.patient.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

public interface DoctorAdviceMainService extends SimpleEntityService {

	/**
	 * 通过患者id查找已建立允许状态的随访医生
	 * @param customerUuid
	 * @return
	 */
	public Map<String, Object> selectVisitApplyByCustomer(String customerUuid);

	/**
	 * 查询子对象列表
	 * @param mainUuid 主表id
	 * @return List&lt;Map&lt;列名,列值&gt;&gt;
	 */
	public List<Map<String, Object>> selectChildList(String mainUuid);
}

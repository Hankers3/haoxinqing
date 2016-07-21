package com.hxq.mobile.patient.visit.service;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.hxq.mobile.util.repository.SimpleEntityService;

public interface CsResultService extends SimpleEntityService {

	/**
	 * 通过患者id得到自评的历史记录
	 * 
	 * @param costomerUuid
	 * @return
	 */
	public List<Map<String, Object>> selectHistory(String customerUuid,String self);

	/**
	 * 自评量表_列表
	 * 通过患者id 和医生id  和主题 查询列表
	 */
	public Map<String, Object> selectResult(String customerUuid, String SubjectId, Date complete_date);

	/**
	 * 检查本随访周期内该患者是否填写自评医评
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Integer select4PeriodCheck(String subject_id,String customerUuid, Date complete_date);
	
	
	/**
	 * 通过主题id和患者id 查训自评医平的详细答题记录
	 * @param customerUuid 患者id
	 * @param SubjectId 测试主题id
	 * @return
	 */
	public List<Map<String, Object>> selectDetailedRecord(String customerUuid, String subjectId,String complete_date);
}

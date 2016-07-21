package com.hxq.mobile.patient.visit.service;

import java.util.Date;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 病情变化记录
 *
 */
public interface IllnessRecordService extends SimpleEntityService {

	/**
	 * 检查本随访周期内该患者是否填写了病情变化 
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Boolean select4PeriodCheck(String customerUuid, Date createTime);
}
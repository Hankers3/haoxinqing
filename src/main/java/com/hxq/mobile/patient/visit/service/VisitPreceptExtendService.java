package com.hxq.mobile.patient.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 随访方案-其他检查项周期
 *
 */
public interface VisitPreceptExtendService extends SimpleEntityService{
	/**
	 * 查询其他项的周期
	 */
	public List<Map<String, Object>> selectOtherPeriod(String preceptUuid);
}

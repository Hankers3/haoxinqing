package com.hxq.mobile.patient.visit.service;

import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 随访方案
 *
 */
public interface VisitPreceptService extends SimpleEntityService {
	/**
	 * 判断：1睡眠情况，2进食情况，3其他情况，5体重，6心电图，7血常规，8肝功能，自评、医评  的周期时间
	 * @param serviceStaffUuid
	 * @param customerUuid
	 * @return
	 */
	public Map<String, Object> selectPeriod (String serviceStaffUuid ,String customerUuid);

}

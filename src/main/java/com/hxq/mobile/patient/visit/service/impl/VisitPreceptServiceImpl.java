package com.hxq.mobile.patient.visit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.patient.visit.service.VisitPreceptService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 随访方案
 *
 */
@Service("com.hxq.mobile.patient.visit.service.VisitPreceptService")
public class VisitPreceptServiceImpl extends SpringJdbcSimpleEntityService implements VisitPreceptService {

	/*
	 * 查询：1睡眠情况，2进食情况，3其他情况，5体重，6心电图，7血常规，8肝功能，自评、医评  的时间
	 */
	public Map<String, Object> selectPeriod (String serviceStaffUuid ,String customerUuid){
		if(ObjectUtils.isEmpty(serviceStaffUuid) || ObjectUtils.isEmpty(customerUuid)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.uuid,a.selfTest,a.doctorTest,")
		.append(" date_sub(curdate(), interval (a.period * 7) day) as period,")
		.append(" date_sub(curdate(), interval (a.electrocardiogram * 7) day) as electrocardiogram,")
		.append(" date_sub(curdate(), interval (a.weight * 7) day) as weight,")
		.append(" date_sub(curdate(), interval (a.bloodRoutine * 7) day) as bloodRoutine,")
		.append(" date_sub(curdate(), interval (a.hepatic * 7) day) as hepatic,")
		.append(" date_sub(curdate(), interval (a.selfPeriod * 7) day) as selfPeriod,")
		.append(" date_sub(curdate(), interval (a.doctorPeriod * 7) day) as doctorPeriod")
		.append(" from visit_precept as a where a.delFlag='1' and a.serviceStaffUuid=? and a.customerUuid=? LIMIT 0,1");

		List<Map<String, Object>> lst = dao.query(sbf.toString(),
				new Object[]{StringUtils.trimToEmpty(serviceStaffUuid), StringUtils.trimToEmpty(customerUuid)},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : lst.get(0);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getCacheName() {return null;}
	@Override
	protected String getQueryCacheName() {return "VisitPreceptService";}
}
package com.hxq.mobile.patient.visit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.patient.visit.service.VisitPreceptExtendService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
@Service("com.hxq.mobile.patient.visit.service.VisitPreceptExtendService")
public class VisitPreceptExtendServiceImpl  extends SpringJdbcSimpleEntityService implements VisitPreceptExtendService{

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}
	@Override
	protected String getCacheName() {return null;}
	@Override
	protected String getQueryCacheName() {return null;}
	
	
	/**
	 * 查询其他项的周期
	 */
	@Override
	public List<Map<String, Object>> selectOtherPeriod(String preceptUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.uuid,a.name,date_sub(curdate(), interval (a.period * 7) day) as period")
		.append(" from visit_precept_extend as a where a.preceptUuid=?");
		return dao.query(sbf.toString(), new Object[]{preceptUuid}, null, getQueryCache());
	}
}

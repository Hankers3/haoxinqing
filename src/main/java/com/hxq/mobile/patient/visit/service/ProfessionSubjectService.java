package com.hxq.mobile.patient.visit.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.util.repository.SimpleEntityService;
import com.hxq.mobile.util.repository.WXDBSpringJdbcSimpleEntityService;

@Service("com.hxq.mobile.patient.visit.service.ProfessionSubjectService")
public class ProfessionSubjectService extends WXDBSpringJdbcSimpleEntityService implements SimpleEntityService {
	@Override
	protected String getCacheName() {return null;}
	@Override
	protected String getQueryCacheName() {return null;}
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

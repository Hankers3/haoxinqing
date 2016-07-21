package com.hxq.mobile.doctor.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.common.service.CustomerAdviceService;

import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
@Service("com.hxq.mobile.doctor.common.service.CustomerAdviceService")
public class CustomerAdviceServiceImpl extends SpringJdbcSimpleEntityService implements CustomerAdviceService  {

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCacheName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getQueryCacheName() {
		// TODO Auto-generated method stub
		return null;
	}
}

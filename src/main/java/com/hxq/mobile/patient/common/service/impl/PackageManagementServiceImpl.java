package com.hxq.mobile.patient.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.patient.common.service.PackageManagementService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;

@Service("com.hxq.mobile.patient.common.service.PackageManagementService")
public class PackageManagementServiceImpl extends SpringJdbcSimpleEntityService implements PackageManagementService{

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "PackageManagementService";}
}

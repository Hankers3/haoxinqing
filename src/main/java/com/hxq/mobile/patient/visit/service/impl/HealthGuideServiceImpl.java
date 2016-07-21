package com.hxq.mobile.patient.visit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.hxq.mobile.patient.visit.service.HealthGuideService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
@Service("com.hxq.mobile.patient.visit.service.HealthGuideService")
public class HealthGuideServiceImpl extends SpringJdbcSimpleEntityService implements HealthGuideService{

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}
	
	/* 根据医生和患者获取健康指导的列表*/
	@Override
	public List<Map<String, Object>> selectHealthGuideListByCustomerUuidAndDoctorUuid(String doctorUuid,String customerUuid) {
		// TODO Auto-generated method stub
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select diet,note,rest,sports,sleep,createTime from health_guide where customerUuid=? and serviceStaffUuid=? order by createTime DESC");
		List<Map<String,Object>> lstReturn = dao.query(sbf.toString(), new Object[]{customerUuid, doctorUuid}, null,getCache());
		return lstReturn;
	}

	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "HealthGuideService";}

}

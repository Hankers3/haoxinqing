package com.hxq.mobile.doctor.visit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.visit.service.HealthGuideService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.visit.service.HealthGuideService")
public class HealthGuideServiceImpl extends SpringJdbcSimpleEntityService
	implements HealthGuideService {

	

	@Override
	protected String getQueryCacheName() {return null;}


	@Override
	protected String getCacheName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getHealthGuideByDoctorUuidAndVisitUuid(String doctorUuid, String visitUuid) {
		if(ObjectUtils.isEmpty(doctorUuid) || ObjectUtils.isEmpty(visitUuid)){
			return null;
		}
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT uuid,delFlag,opeTime,oper,createTime,customerUuid,diet,note,");
		sqlBuff.append(" notevisitRecordUuid,rest,serviceStaffUuid,sports,sleep,period ");
		sqlBuff.append(" FROM health_guide ");
		sqlBuff.append("WHERE serviceStaffUuid =? AND notevisitRecordUuid=?");
		List<Map<String, Object>> lstReturn = dao.query(sqlBuff.toString()	,	new String[]{doctorUuid,visitUuid}, null, getCache());
		if(ObjectUtils.isEmpty(lstReturn)){
			return null;
		}
		
		return lstReturn;
	}


	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
 
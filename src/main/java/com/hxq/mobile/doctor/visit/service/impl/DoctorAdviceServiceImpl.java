package com.hxq.mobile.doctor.visit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.visit.service.DoctorAdviceService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.visit.service.DoctorAdviceService")
public class DoctorAdviceServiceImpl extends SpringJdbcSimpleEntityService
	implements DoctorAdviceService {

	@Override
	public List<Map<String, Object>> selectListByDoctorAdviceMain(String mainUuid) {
		if(ObjectUtils.isEmpty(mainUuid)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.medicineUuid,a.directions, a.dosage, a.frequency, a.unit, a.food")
		.append(" from doctor_advice as a")
		.append(" where a.mainUuid = ? order by a.createTime");
		return dao.query(sbf.toString(), new Object[]{mainUuid}, null, getCache());
	}

	@Override
	public List<Map<String, Object>> selectListByVisitPreceptAndType(String visitRreceptUuid,String type) {
		if(ObjectUtils.isEmpty(visitRreceptUuid)){
			return null;
		}
		String  querySql = toSql(visitRreceptUuid, type);
	

		
		return dao.query(querySql, new Object[]{visitRreceptUuid,type}, null, getCache());
	}

	/**
	 * 拼接查询sql
	 * @param visitRreceptUuid
	 * @param type
	 * @return
	 */
	public String toSql(String visitRreceptUuid,String type){
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.uuid,a.medicineUuid,a.directions, a.dosage, a.frequency, a.unit, a.food")
		.append(" from doctor_advice as a ")
		.append(" where a.visitRecordUuid = ? ");
		if(!ObjectUtils.isEmpty(type)){
			sbf.append(" and a.type=? ");
		}
		sbf.append(" order by a.createTime desc");
		return sbf.toString();
	}
	
	@Override
	public List<Map<String, Object>> selectListByVisitRecord(String visitRecordUuid) {
		if(ObjectUtils.isEmpty(visitRecordUuid)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.directions, a.dosage, a.frequency, a.unit, a.medicalDateBegin, a.medicalDateEnd,a.medicineUuid")
		.append(" from doctor_advice as a ")
		.append(" where a.visitRecordUuid = ? order by a.createTime");
		//select a.directions, a.dosage, a.frequency, a.unit, a.medicalDateBegin, a.medicalDateEnd,
		//b.productName from doctor_advice as a left join product_main as b on a.medicineUuid=b.uuid
		//where a.visitRecordUuid = ? order by a.createTime
		return dao.query(sbf.toString(), new Object[]{visitRecordUuid}, null, getCache());
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}

	@Override
	protected String getCacheName() {return "h1";}

	@Override
	protected String getQueryCacheName() {return null;}

	@Override
	public List<Map<String, Object>> selectListByVisitPrecept(String visitPreceptUuid) {
		if(ObjectUtils.isEmpty(visitPreceptUuid)){
			return null;
		}
		String  querySql = toSql(visitPreceptUuid, "");
		
		return dao.query(querySql, new Object[]{visitPreceptUuid}, null, getCache());
	}
}

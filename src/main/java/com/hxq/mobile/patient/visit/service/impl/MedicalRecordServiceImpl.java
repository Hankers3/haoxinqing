package com.hxq.mobile.patient.visit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.patient.visit.service.MedicalRecordService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.patient.visit.service.MedicalRecordService")
public class MedicalRecordServiceImpl extends SpringJdbcSimpleEntityService	implements MedicalRecordService {

	@Override
	public List<Map<String, Object>> selectListByCustomerUuidAndDoctorUuid(String customerUuid, String doctorUuid) {
		if (ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(doctorUuid)) return null;
		return dao.query("select UUID,caseCategoryType,createTime from medical_record where customerUuid=? and doctorUuid=? order by createTime desc",
				new Object[] { customerUuid, doctorUuid }, null, getQueryCache());
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// doctorUuid 医生id ，customerUuid 患者id
		if(RequestUtil.isEmpty(form,"doctorUuid") || RequestUtil.isEmpty(form,"customerUuid")) return null;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<String>();

		sbf.append("(SELECT a.uuid, a.seeDoctorTime as dt, a.caseCategoryType,'' as visitState,  0 as type");
		sbf.append(" FROM medical_record as a WHERE a.customerUuid = ?");
		columns.add(RequestUtil.getFormValue(form, "customerUuid"));
		sbf.append(" AND a.doctorUuid = ? ) ");
		columns.add(RequestUtil.getFormValue(form, "doctorUuid"));

		sbf.append(" UNION ALL ");

		sbf.append("(SELECT b.uuid, b.createTime as dt, b.applyState as caseCategoryType,b.visitState,  1 as type");
		sbf.append(" FROM visit_record as b WHERE b.customerUuid = ?");
		columns.add(RequestUtil.getFormValue(form, "customerUuid"));

		String visitState = RequestUtil.getFormValue(form, "visitState");
		if (ObjectUtils.isEmpty(visitState) == false) {
			sbf.append(" and b.visitState = ? ");
			columns.add(visitState);
		}

		sbf.append(" AND b.serviceStaffUuid = ? ) ");
		columns.add(RequestUtil.getFormValue(form, "doctorUuid"));

		sbf.append(" ORDER BY dt desc").toString();

		Object[] params = columns.isEmpty() ? null : columns.toArray();
		return dao.query(sbf.toString(), params, null, getQueryCache());
	}

	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return null;}
}

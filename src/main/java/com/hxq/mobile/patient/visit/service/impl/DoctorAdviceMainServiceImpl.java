package com.hxq.mobile.patient.visit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.patient.visit.service.DoctorAdviceMainService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.patient.visit.service.DoctorAdviceMainService")
public class DoctorAdviceMainServiceImpl extends SpringJdbcSimpleEntityService implements DoctorAdviceMainService {

	//查询随访状态 
	@Override
	public Map<String, Object> selectVisitApplyByCustomer(String customerUuid) {//String customerUuid
		//业务规则：1个患者仅能同1个医生建立有效的随访关联
		String sql = "select a.serviceStaffUuid from visit_apply as a where a.applyState='1' and a.customerUuid=? limit 0,5";//limit = 1:只检索第一条   limit 0,-1 从0到最后
		List<String> columns = new ArrayList<String>();
		columns.add(customerUuid);
		Object[] params = columns.isEmpty() ? null : columns.toArray();
		List<Map<String, Object>> lstReturn = dao.query(sql, params, null, getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0);
		
		
	}
	//查询doctor_main
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) {
		if(RequestUtil.isEmpty(form, "customerUuid") || RequestUtil.isEmpty(form, "serviceStaffUuid")) return null;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<String>();

		sbf.append("select a.uuid, a.serviceStaffUuid, a.customerUuid, a.drugReaction, a.cureNote, a.createTime, a.delFlag");
		sbf.append(" from doctor_advice_main as a");

		sbf.append(columns.isEmpty()?" where":" and").append(" a.customerUuid = ?");
		columns.add(RequestUtil.getFormValue(form, "customerUuid"));

		sbf.append(columns.isEmpty()?" where":" and").append(" a.serviceStaffUuid = ?");
		columns.add(RequestUtil.getFormValue(form, "serviceStaffUuid"));

		sbf.append(" order by a.createTime desc").toString();

		Object[] params = columns.isEmpty() ? null : columns.toArray();
		return dao.query(sbf.toString(), params, null, getCache());
	}

	@Override
	public List<Map<String, Object>> selectChildList(String mainUuid) {
		if(ObjectUtils.isEmpty(mainUuid)) return null;
		StringBuffer sbf = new StringBuffer(1000);		
		sbf.append("select a.directions, a.dosage, a.frequency, a.unit, a.food,a.medicalDateEnd,medicalDateBegin,a.state,")
		.append(" a.type,a.medicineUuid, b.uuid, c.productName from doctor_advice as a")
		.append(" left join doctor_advice_main as b on a.mainUuid=b.uuid")
		.append(" left join product_main as c on a.medicineUuid=c.uuid")
		.append(" where a.mainUuid = ? order by a.createTime");
		return dao.query(sbf.toString(), new Object[]{mainUuid}, null, getQueryCache());
	}

	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "DoctorAdviceMainService";}
}

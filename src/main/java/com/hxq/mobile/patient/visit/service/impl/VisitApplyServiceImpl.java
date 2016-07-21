package com.hxq.mobile.patient.visit.service.impl;


import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.patient.visit.service.VisitApplyService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.util.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("com.hxq.mobile.patient.visit.service.VisitApplyService")
public class VisitApplyServiceImpl extends SpringJdbcSimpleEntityService
	implements VisitApplyService {

	@Override
	protected String getCacheName() {return "h1";}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}

	@Override
	public int insert(AbstractEntity<?> bean) throws Exception {
		VisitApply va = (VisitApply) bean;
		Object[] parms = new Object[]{va.getCustomerUuid(), va.getServiceStaffUuid()};
		String uuid = dao.queryForObject(
				"select uuid from visit_apply where applyState='1' and customerUuid=? and serviceStaffUuid=? limit 0, 1",
				parms, null, String.class);
		if(ObjectUtils.isEmpty(uuid)==false) return 0;//已存在无需再申请

		dao.update("delete from visit_apply where applyState='0' and customerUuid=? and serviceStaffUuid=?",
				new Object[]{va.getCustomerUuid(), va.getServiceStaffUuid()}, null);
		clearQueryCache();
		return super.insert(bean);
	}
	/*
	 *  通过患者和申请状态查询医生id
	 */
	@Override
	public String selectDoctorFromApply(String customerUuid, String applyState) {
		String sql="select a.serviceStaffUuid from visit_apply a where a.customerUuid=? and a.applyState=?";
		Object[][] array = this.dao.queryForArray(sql, new Object[]{
				customerUuid, applyState}, null, false, super.getCache());
		return ObjectUtils.isEmpty(array) ? null : (String) array[0][0];
	}
	/*
	 * 根据患者id和申请状态查询visitRecordUuid
	 */
	@Override
	public Map<String, Object> selectCustomerFromVisitRecordUuid(String customerUuid, String applyState) {
		String sql = "select a.serviceStaffUuid,a.visitRecordUuid,a.visitPreceptUuid from visit_apply a where a.customerUuid=? and a.applyState=?";
		List<String> staff = new ArrayList<String>();
		staff.add(customerUuid);
		staff.add(applyState);
		Object[] staffparams = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> staffReturn = dao.query(sql, staffparams, null, getCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn.get(0));
	}
	/*
	 * 根据医生ID查找所有随访患者Uuid 
	 * 
	 */
	@Override
	public List<Map<String, Object>> selectCustomeruidByDoctorUuid(String doctorUuid) {
		String hql = " select o.customerUuid from visit_apply o where o.applyState='1' and o.serviceStaffUuid=? order by o.createTime desc";
		List<String> staff = new ArrayList<>();
		staff.add(doctorUuid);
		Object[] staffparams = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> staffReturn = dao.query(hql, staffparams, null, getCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn);
	}

	@Override
	protected String getQueryCacheName() {
		return "VisitApplyService";
	}


}

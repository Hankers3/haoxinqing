package com.hxq.mobile.patient.visit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.patient.visit.service.VisitRecordService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.patient.visit.service.VisitRecordService")
public class VisitRecordServiceImpl  extends SpringJdbcSimpleEntityService implements VisitRecordService{

	private String TABLE_COLUMNS="uuid,delFlag,opeTime,oper,createTime,customerUuid,preceptUuid,"
			+ "serviceStaffUuid,visitUuid,refuseReason,applyState,visitType,visitState";

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination)  {
		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<String>();

		sbf.append("select ").append(TABLE_COLUMNS).append(" from visit_record as a");

		if(RequestUtil.isEmpty(form, "customerUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.customerUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "customerUuid"));
		}
		if(RequestUtil.isEmpty(form, "serviceStaffUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.serviceStaffUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "serviceStaffUuid"));
		} else if(RequestUtil.isEmpty(form, "doctorUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.serviceStaffUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "doctorUuid"));
		}

		if(RequestUtil.isEmpty(form, "visitState")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.visitState = ?");
			columns.add(RequestUtil.getFormValue(form, "visitState"));
		}

		sbf.append(" order by a.createTime desc");

		Object[] params = columns.isEmpty() ? null : columns.toArray();
		return dao.query(sbf.toString(), params, null, getQueryCache());
	}
	/**
	 * 根据医生id 获取随访患者的数量
	 * @param doctorId
	 * @return
	 */
	@Override
	public int selectVisitRecordNumByDoctorId(String doctorId) {
		
		String hql = "select count(uuid) from visit_record o where o.serviceStaffUuid = ? and o.visitState = '1'";
		List<String> columns= new ArrayList<String>();
		columns.add(doctorId);
		Object[] params = columns.isEmpty() ? null : columns.toArray();
//		List<Map<String, Object>> lstReturn = dao.query(hql, params, null, getCache());
//		if(lstReturn == null) return 0;
//		return  lstReturn.size();
		Integer intReturn =  dao.queryForObject(hql.toString(), params, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;	
	}

	@Override
	public VisitRecord selectRecentVisitRecordByPreceptUuid(String customerUuid, String serviceStaffUuid, String preceptUuid, Date createTime) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(serviceStaffUuid)
				|| ObjectUtils.isEmpty(preceptUuid) || ObjectUtils.isEmpty(createTime)) return null;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<String>();

		sbf.append("select ").append(TABLE_COLUMNS).append(" from visit_record as a");
		sbf.append(columns.isEmpty()?" where":" and").append(" a.customerUuid=?");
		columns.add(StringUtils.trimToEmpty(customerUuid));

		sbf.append(columns.isEmpty()?" where":" and").append(" a.serviceStaffUuid=?");
		columns.add(StringUtils.trimToEmpty(serviceStaffUuid));

		sbf.append(columns.isEmpty()?" where":" and").append(" a.preceptUuid=?");
		columns.add(StringUtils.trimToEmpty(preceptUuid));

		sbf.append(columns.isEmpty()?" where":" and").append(" a.createTime>=?");
		columns.add(StringUtils.trimToEmpty(preceptUuid));

		sbf.append(" order by a.createTime desc limit 0,1");

		Object[] params = columns.isEmpty() ? null : columns.toArray();
		List<Map<String, Object>> lst = dao.query(sbf.toString(), params, null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : SimpleBean2DBHelper.map2Bean(lst.get(0), VisitRecord.class);
	}

	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return null;}
}

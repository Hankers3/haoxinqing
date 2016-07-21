package com.hxq.mobile.doctor.visit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.visit.service.VisitRecordExtendService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.visit.service.VisitRecordExtendService")
public class VisitRecordExtendServiceImpl extends SpringJdbcSimpleEntityService
	implements VisitRecordExtendService {

	@Resource(name="com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	@Override
	public Map<String, Object> selectSleep(String visitRecordUuid) {
		if(ObjectUtils.isEmpty(visitRecordUuid)) return null;
		List<Map<String, Object>> lstReturn = dao.query(
				"select result,state from visit_record_extend where visitRecordUuid=? and type='1' limit 0, 1",
				new String[]{visitRecordUuid}, null, getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0);
	}

	@Override
	public Map<String, Object> selectEat(String visitRecordUuid) {
		if(ObjectUtils.isEmpty(visitRecordUuid)) return null;
		List<Map<String, Object>> lstReturn = dao.query(
				"select result,state from visit_record_extend where visitRecordUuid=? and type='2' limit 0, 1",
				new String[]{visitRecordUuid}, null, getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0);
	}

	@Override
	public Map<String, Object> selectOther(String visitRecordUuid) {
		if(ObjectUtils.isEmpty(visitRecordUuid)) return null;
		List<Map<String, Object>> lstReturn = dao.query(
				"select result from visit_record_extend where visitRecordUuid=? and type='3' limit 0, 1",
				new String[]{visitRecordUuid}, null, getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0);
	}

	@Override
	public List<Map<String, Object>> selectCheckResult(String visitRecordUuid) {
		if(ObjectUtils.isEmpty(visitRecordUuid)) return null;
		List<Map<String, Object>> lstReturn = dao.query(
				  "select a.uuid,a.name,b.name as vpeName,a.result from visit_record_extend as a "
				+ "LEFT JOIN visit_precept_extend as b on b.uuid = a.preceptExtendUuid where a.visitRecordUuid = ? and a.type='4'",
				new String[]{visitRecordUuid}, null, getCache());
		if(ObjectUtils.isEmpty(lstReturn)) return null;
		Image4App[] imgs = null;
		for(Map<String, Object> row : lstReturn) {
			imgs = imgUploadService.selectImagesByIds(((String) row.get("images")).split(","));
			if(ObjectUtils.isEmpty(imgs)==false) row.put("imgs", imgs);
			if(ObjectUtils.isEmpty(row.get("name"))) row.put("name", row.get("vpeName"));
		}
		return lstReturn;
	}

	@Override
	public Map<String, Object> selectWeight(String visitRecordUuid) {
		if(ObjectUtils.isEmpty(visitRecordUuid)) return null;
		List<Map<String, Object>> lstReturn = dao.query(
				"select result from visit_record_extend where visitRecordUuid=? and type='5' limit 0, 1",
				new String[]{visitRecordUuid}, null, getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0);
	}

	@Override
	protected String getCacheName() {return "h1";}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<String>();

		sbf.append("select uuid,createTime from visit_record as v");
		if(RequestUtil.isEmpty(form, "customerUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" v.customerUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "customerUuid"));
		}

		if(RequestUtil.isEmpty(form, "serviceStaffUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" v.serviceStaffUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "serviceStaffUuid"));
		} else if(RequestUtil.isEmpty(form, "doctorUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" v.serviceStaffUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "doctorUuid"));
		}

		sbf.append(" ORDER BY v.createTime");

		Object[] params = columns.isEmpty() ? null : columns.toArray();
		return dao.query(sbf.toString(), params, null, getCache());
	}

	@Override
	protected String getQueryCacheName() {return null;}

	@Override
	public List<Map<String, Object>> getAllVisitPreceptByPreceptUuid(String visitRecordUuid) {
		if(ObjectUtils.isEmpty(visitRecordUuid)){
			return null;
		}
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT UUID,oper,NAME,period,result,period,result,TYPE,visitRecordUuid FROM visit_record_extend where visitRecordUuid=?");
		List<Map<String, Object>> lstReturn = dao.query(sqlBuff.toString()	,		new String[]{visitRecordUuid}, null, getCache());
		if(ObjectUtils.isEmpty(lstReturn)){
			return null;
		}
		String[] imgs = null;
		for(Map<String, Object> row : lstReturn) {
			imgs = imgUploadService.selectImagesByTableName("visit_record_extend",(String)row.get("uuid"));
			if(ObjectUtils.isEmpty(imgs)==false) row.put("imgs", imgs);
		}
		return lstReturn;
	}
}

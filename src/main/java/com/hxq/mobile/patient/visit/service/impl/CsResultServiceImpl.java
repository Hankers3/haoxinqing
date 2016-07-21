package com.hxq.mobile.patient.visit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.patient.visit.service.CsResultService;
import com.hxq.mobile.util.repository.WXDBSpringJdbcSimpleEntityService;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Service("com.hxq.mobile.patient.visit.service.CsResultService")
public class CsResultServiceImpl extends WXDBSpringJdbcSimpleEntityService implements CsResultService{

	/*通过患者id得到自评的历史记录*/
	@Override
	public List<Map<String, Object>> selectHistory(String customerUuid, String self) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.id,a.complete_date,a.score,b.result ,c.id as subject_id from cs_zy_result as a")
		.append(" LEFT JOIN cs_zy_evaluation as b on a.subject_id=b.subject_id and a.score>=b.min_value and a.score<=b.max_value")
		.append(" LEFT JOIN cs_zy_subject  as c on c.id =a.subject_id")
		.append(" where a.usrfrm='1' and a.openid=? and c.self = ? order by complete_date desc");
		return dao.query(sbf.toString(),new Object[]{customerUuid,self},null,getQueryCache());
	}

	/*
	 * 自评量表_列表
	 * 通过患者id 和医生id 和 selfTests主题  查询列表
	 */
	@Override
	public Map<String, Object> selectResult(String customerUuid, String SubjectId, Date complete_date) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select b.score,c.result,c.analys from cs_zy_result as b")
		.append(" LEFT JOIN cs_zy_evaluation as c on b.subject_id = c.subject_id")
		.append(" and b.score >= c.min_value and b.score <= c.max_value")
		.append(" where usrfrm='1' and b.openid=? and b.subject_id=? and complete_date>=? limit 0,1");
		List<Map<String, Object>> lst =  dao.query(sbf.toString(),
				new Object[]{customerUuid, SubjectId, complete_date}, null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : lst.get(0);
	}

	/**
	 * 检查本随访周期内该患者是否填写自评医评
	 */
	@Override
	public Integer select4PeriodCheck(String subject_id,String customerUuid, Date complete_date) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(complete_date)) return null;
		List<Map<String, Object>> lst = dao.query(
				"select score from cs_zy_result where usrfrm='1' and subject_id=? and openid=? and complete_date>=? LIMIT 0,1",
				new Object[]{StringUtils.trimToEmpty(subject_id), StringUtils.trimToEmpty(customerUuid), complete_date},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? -1 : MathUtils.toInt(lst.get(0).get("score"), 0);
	}

	/**
	 * 通过主题id和患者id 查询自评医评的详细答题记录
	 */
	@Override
	public List<Map<String, Object>> selectDetailedRecord(String customerUuid, String subjectId,String complete_date) {
		StringBuffer sbf = new StringBuffer(1000);
//		sbf.append("select  a.result_id,c.subject_id, c.content,c.sort+1 as sort ,c.input_value,b.score,a.answer1  from cs_zy_answer  as a ")
//		.append("LEFT JOIN cs_zy_result as b on b.id = a.result_id ")
//		.append("LEFT JOIN cs_zy_item as c on  a.item_id = c.id ")
//		.append("where b.id=? and b.openid =? ORDER BY c.sort ASC");
		sbf.append("select c.sort+1 as sort ,c.content ,b.answer1  from cs_zy_result as a")
		.append(" LEFT JOIN cs_zy_answer as b on b.result_id = a.id")
		.append(" LEFT JOIN cs_zy_item as c on c.id = b.item_id and c.subject_id = a.subject_id")
		.append(" where a.subject_id =? and a.openid =? and a.complete_date=? ORDER BY c.sort ASC");
		
		List<String> staff = new ArrayList<>();
		staff.add(subjectId);
		staff.add(customerUuid);
		staff.add( complete_date);
		Object[] staffparams = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> lst =  dao.query(sbf.toString(),staffparams, null, getQueryCache());
		return lst;
	}
	
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "CsResultService";}

}

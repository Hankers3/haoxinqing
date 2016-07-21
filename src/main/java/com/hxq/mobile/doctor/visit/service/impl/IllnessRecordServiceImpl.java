package com.hxq.mobile.doctor.visit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.visit.service.IllnessRecordService;
import com.hxq.mobile.doctor.visit.service.VisitPreceptService;
import com.hxq.mobile.doctor.visit.service.VisitRecordExtendService;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.util.ObjectUtils;


/**
 * 病情变化记录
 *
 */
@Service("com.hxq.mobile.doctor.visit.service.IllnessRecordService")
public class IllnessRecordServiceImpl extends SpringJdbcSimpleEntityService implements IllnessRecordService {

	@Resource(name="com.hxq.mobile.doctor.visit.service.VisitRecordExtendService")
	private VisitRecordExtendService visitRecordExtendService;

	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitPreceptService")
	private VisitPreceptService visitPreceptService;
	
	@Override
	public List<Map<String, Object>> selectByVisitRecord(String visitRecordUuid) {
		if(ObjectUtils.isEmpty(visitRecordUuid)) return null;
		return dao.query(
			"select newCondition,previons from illness_record where visitRecordUuid=?",
			new Object[]{visitRecordUuid}, null, getCache());
	}

	/**
	 * 查询某患者的病情变化历史列表
	 */
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		if(RequestUtil.isEmpty(form,"serviceStaffUuid") || RequestUtil.isEmpty(form,"customerUuid")) return null;
		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<String>();
		sbf.append("select b.uuid,a.newCondition,a.previons,a.createTime from illness_record as a")
		.append(" left join visit_record as b on a.visitRecordUuid=b.uuid where b.visitState='1'")
		.append(" and b.serviceStaffUuid=? and b.customerUuid=? order by a.createTime desc");
		columns.add(RequestUtil.getFormValue(form, "serviceStaffUuid"));
		columns.add(RequestUtil.getFormValue(form, "customerUuid"));
		return dao.query(sbf.toString(), columns.toArray(), null, getQueryCache());
	}

	/**
	 * 通过随访记录id查询某患者的病情变化详情：包括病情记录、睡眠情况、饮食情况和其他情况
	 */
	@Override
	public AbstractEntity<?> select(AbstractEntity<?> id) throws Exception {
		List<Map<String, Object>> lst = dao.query(
			"select newCondition,previons from illness_record where visitRecordUuid=?",
			new Object[]{id.getId()}, null, getCache());
		VisitRecord bean = (VisitRecord) id;
		bean.setIllnessRecord(ObjectUtils.isEmpty(lst) ? null : lst.get(0));
		bean.setSleep(visitRecordExtendService.selectSleep((String) id.getId()));
		bean.setEat(visitRecordExtendService.selectEat((String) id.getId()));
		bean.setOther(visitRecordExtendService.selectOther((String) id.getId()));
		return bean;
	}


	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "IllnessRecordService";}
}
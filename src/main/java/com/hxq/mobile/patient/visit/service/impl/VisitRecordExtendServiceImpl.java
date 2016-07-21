package com.hxq.mobile.patient.visit.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.visit.service.VisitPreceptService;
import com.hxq.mobile.entity.visit.DrugReaction;
import com.hxq.mobile.entity.visit.IllnessRecord;
import com.hxq.mobile.entity.visit.VisitRecordExtend;
import com.hxq.mobile.patient.visit.service.IllnessRecordService;
import com.hxq.mobile.patient.visit.service.VisitRecordExtendService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Service("com.hxq.mobile.patient.visit.service.VisitRecordExtendService")
public class VisitRecordExtendServiceImpl extends SpringJdbcSimpleEntityService
	implements VisitRecordExtendService {

	@Resource(name="com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitPreceptService")
	private VisitPreceptService visitPreceptService;

	@Resource(name = "com.hxq.mobile.patient.visit.service.IllnessRecordService")
	private IllnessRecordService illnessRecordService;

	@Override
	public int insertCheckResult(AbstractEntity<?> bean, String imgs) throws Exception {
		VisitRecordExtend vre = (VisitRecordExtend) bean;
		if(ObjectUtils.isEmpty(vre.getId())) vre.setId(IdentityHelper.uuid2());
		if(ObjectUtils.isEmpty(imgs)==false) {
			imgUploadService.updateForBindingBusiness("visit_record_extend",vre.getId(),imgs.split(","));
		}
		return super.insert(vre);
	}

	@Override
	public int insertIllnessRecord(Map<String, Object> params) throws Exception {
		String visitRecordUuid = RequestUtil.getFormValue(params, "visitRecordUuid");
		if(ObjectUtils.isEmpty(visitRecordUuid)) return 0;

		int intReturn = 0;
		VisitRecordExtend vre = null;
		if(RequestUtil.isEmpty(params, "sleepstate")==false) {
			vre = new VisitRecordExtend(IdentityHelper.uuid2(), visitRecordUuid);
			vre.setCustomerUuid(RequestUtil.getFormValue(params, "customerUuid"));
			vre.setResult(RequestUtil.getFormValue(params, "sleep"));
			vre.setState(RequestUtil.getFormValue(params, "sleepstate"));
			vre.setType("1");
			intReturn = super.insert(vre);
		}

		if(RequestUtil.isEmpty(params, "eatstate")==false) {
			vre = new VisitRecordExtend(IdentityHelper.uuid2(), visitRecordUuid);
			vre.setCustomerUuid(RequestUtil.getFormValue(params, "customerUuid"));
			vre.setResult(RequestUtil.getFormValue(params, "eat"));
			vre.setState(RequestUtil.getFormValue(params, "eatstate"));
			vre.setType("2");
			intReturn = super.insert(vre);
		}

		if(RequestUtil.isEmpty(params, "other")==false) {
			vre = new VisitRecordExtend(IdentityHelper.uuid2(), visitRecordUuid);
			vre.setCustomerUuid(RequestUtil.getFormValue(params, "customerUuid"));
			vre.setResult(RequestUtil.getFormValue(params, "other"));
			vre.setType("3");
			intReturn = super.insert(vre);
		}

		if(RequestUtil.isEmpty(params, "illnessstate")==false) {
			IllnessRecord ir = new IllnessRecord(IdentityHelper.uuid2(), visitRecordUuid);
			ir.setPrevions(RequestUtil.getFormValue(params, "illnessstate"));
			ir.setNewCondition(RequestUtil.getFormValue(params, "illness"));
			ir.setCustomerUuid(RequestUtil.getFormValue(params, "customerUuid"));
			ir.setServiceStaffUuid(RequestUtil.getFormValue(params, "serviceStaffUuid"));
			intReturn = illnessRecordService.insert(ir);
		}
		return intReturn;
	}

	@Override
	public int insertDrugReaction(Map<String, Object> params) throws Exception {
		String visitRecordUuid = RequestUtil.getFormValue(params, "visitRecordUuid");
		if(ObjectUtils.isEmpty(visitRecordUuid)) return 0;
		DrugReaction dr = new DrugReaction();
		dr.setVisitRecordUuid(visitRecordUuid);
		dr.setImpact(RequestUtil.getFormValue(params, "impact"));
		dr.setDosageTime(RequestUtil.getFormValue(params, "dosageTime"));
		dr.setFrequency(RequestUtil.getFormValue(params, "frequency"));
		dr.setOccurrenceTime(RequestUtil.getFormValue(params, "occurrenceTime"));
		return super.insert(dr);
	}

	/*检查本随访周期内该患者是否填写了睡眠情况*/
	@Override
	public Boolean select4SleepPeriodCheck(String customerUuid, Date opeTime) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(opeTime)) return null;
		List<Map<String, Object>> lst = dao.query(
				"select 1 from visit_record_extend where type='1'and customerUuid=? and opeTime >= ? LIMIT 0,1",
				new Object[]{StringUtils.trimToEmpty(customerUuid), opeTime},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst);
	}

	/*检查本随访周期内该患者是否填写了进食情况*/
	@Override
	public Boolean select4FoodPeriodCheck(String customerUuid, Date opeTime) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(opeTime)) return null;
		List<Map<String, Object>> lst = dao.query(
				"select 1 from visit_record_extend where type='2' and customerUuid=? and opeTime >= ? LIMIT 0,1",
				new Object[]{StringUtils.trimToEmpty(customerUuid), opeTime},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst);
	}

	/*检查本随访周期内该患者是否填写了其他情况*/
	@Override
	public Boolean select4OtherPeriodCheck(String customerUuid, Date opeTime) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(opeTime)) return null;
		List<Map<String, Object>> lst = dao.query(
				"select 1 from visit_record_extend where type='3' and customerUuid=? and opeTime >= ? LIMIT 0,1",
				new Object[]{StringUtils.trimToEmpty(customerUuid), opeTime},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst);
	}

	/*检查本随访周期内该患者是否填写了体重情况 */
	@Override
	public Boolean select4WeightPeriodCheck(String customerUuid, Date opeTime) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(opeTime)) return null;
		List<Map<String, Object>> lst = dao.query(
				"select 1 from visit_record_extend where type='5' and customerUuid=? and opeTime >= ? LIMIT 0,1",
				new Object[]{StringUtils.trimToEmpty(customerUuid), opeTime},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst);
	}

	/*检查本随访周期内该患者是否填写心电图*/
	@Override
	public Boolean select4ElectrocardiogramPeriodCheck(String customerUuid, Date opeTime) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(opeTime)) return null;
		List<Map<String, Object>> lst = dao.query(
				"select 1 from visit_record_extend as a where a.type='6' and a.customerUuid=? and a.opeTime >= ? LIMIT 0,1",
				new Object[]{StringUtils.trimToEmpty(customerUuid), opeTime},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst);
	}

	/*检查本随访周期内该患者是否填写血常规*/
	@Override
	public Boolean select4BloodRoutinePeriodCheck(String customerUuid, Date opeTime) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(opeTime)) return null;
		List<Map<String, Object>> lst = dao.query(
				"select 1 from visit_record_extend as a where a.type='7' and a.customerUuid=? and a.opeTime >= ? LIMIT 0,1",
				new Object[]{StringUtils.trimToEmpty(customerUuid), opeTime},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst);
	}

	/*肝功能 */
	@Override
	public Boolean select4HepaticPeriodCheck(String customerUuid, Date opeTime) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(opeTime)) return null;
		List<Map<String, Object>> lst = dao.query(
				"select 1 from visit_record_extend where type='8' and customerUuid=? and opeTime >=? LIMIT 0,1",
				new Object[]{StringUtils.trimToEmpty(customerUuid), opeTime},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst);
	}

	/*其他项*/
	@Override
	public Boolean select4ExtendPeriodCheck(String customerUuid, String preceptExtendUuid, Date opeTime) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(preceptExtendUuid)
				|| ObjectUtils.isEmpty(opeTime)) return null;
		String sql = "select 1 from visit_record_extend where type='4' and customerUuid=?and preceptExtendUuid=? and opeTime>=? LIMIT 0,1";
		List<Map<String, Object>> lst = dao.query(sql, new Object[]{StringUtils.trimToEmpty(customerUuid), preceptExtendUuid, opeTime},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return null;}
}

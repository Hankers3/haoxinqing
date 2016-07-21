package com.hxq.mobile.doctor.visit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.doctor.visit.service.DoctorAdviceService;
import com.hxq.mobile.doctor.visit.service.DrugReactionService;
import com.hxq.mobile.doctor.visit.service.IllnessRecordService;
import com.hxq.mobile.doctor.visit.service.VisitPreceptService;
import com.hxq.mobile.doctor.visit.service.VisitRecordExtendService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.entity.weixin.CsZySubject;
import com.hxq.mobile.entity.weixin.CszyEvaluation;
import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.hxq.mobile.patient.visit.service.CszyEvaluationService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.hxq.mobile.weixin.service.CsZyResultService;
import com.hxq.mobile.weixin.service.CsZySubjectService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.ObjectUtils;

/**
 * 随访表单（记录）
 *
 */
@Service("com.hxq.mobile.doctor.visit.service.VisitRecordService")
public class VisitRecordServiceImpl extends SpringJdbcSimpleEntityService implements VisitRecordService {

	@Resource(name="com.hxq.mobile.common.service.CustomerInfoService")
	private CustomerInfoService customerInfoService;

	@Resource(name="com.hxq.mobile.doctor.visit.service.IllnessRecordService")
	private IllnessRecordService illnessRecordService;

	@Resource(name="com.hxq.mobile.doctor.visit.service.DrugReactionService")
	private DrugReactionService drugReactionService;

	@Resource(name="com.hxq.mobile.doctor.visit.service.DoctorAdviceService")
	private DoctorAdviceService doctorAdviceService;

	@Resource(name="com.hxq.mobile.doctor.visit.service.VisitRecordExtendService")
	private VisitRecordExtendService visitRecordExtendService;
	
	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitPreceptService")
	private VisitPreceptService visitPreceptService;
	
	@Resource(name = "com.hxq.mobile.weixin.service.CsZyResultService")
	private CsZyResultService csZyResultService;
	
	@Resource(name = "com.wxsupport.web.service.CszyEvaluationService")
	private CszyEvaluationService cszyEvaluationService;
	
	@Resource(name="com.hxq.mobile.weixin.service.csZySubjectService")
	private CsZySubjectService csZySubjectService;

	@Resource(name="com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	@Autowired
	private FileService fileService;
	
	@Override
	public AbstractEntity<?> select(AbstractEntity<?> id) throws Exception {
		VisitRecord bean = (VisitRecord) super.select(id);
		if(bean == null) return null;

		List<Map<String, Object>> lstIlness = illnessRecordService.selectByVisitRecord((String) id.getId());
		bean.setIllnessRecord(ObjectUtils.isEmpty(lstIlness) ? null : lstIlness.get(0));

		Map<String, Object> form = new HashMap<String, Object>();
		form.put("visitRecordUuid", id.getId());
		List<Map<String, Object>> lstDrug = drugReactionService.selectList(form, null);
		bean.setDrugReaction(ObjectUtils.isEmpty(lstDrug) ? null : lstDrug.get(0));

		bean.setSleep(visitRecordExtendService.selectSleep((String) id.getId()));
		bean.setEat(visitRecordExtendService.selectEat((String) id.getId()));
		bean.setOther(visitRecordExtendService.selectOther((String) id.getId()));

		bean.setDoctorAdvice(doctorAdviceService.selectListByVisitRecord((String) id.getId()));
		bean.setCheckResult(visitRecordExtendService.selectCheckResult((String) id.getId()));
		bean.setWeight(visitRecordExtendService.selectWeight((String) id.getId()));
		return bean;
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<String>();

		sbf.append("select uuid,createTime,customerUuid,serviceStaffUuid,visitType,applyState,visitState,preceptUuid from visit_record as a");

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
		if(RequestUtil.isEmpty(form, "applyState")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.applyState = ?");
			columns.add(RequestUtil.getFormValue(form, "applyState"));
		}

		Object[] params = columns.isEmpty() ? null : columns.toArray();
		return dao.query(sbf.toString(), params, null, getCache());
	
	}

	public Map<String, Object> selectWaitProcessedVisitList(String doctorUuid,String customerUuid,String applyUuid,String applyState,String createTime){
		// 组装返回到客户端的患者信息
		CustomerInfo cim = null;
		Map<String, Object> visitApplyMap = new HashMap<>();
		if (!StringUtil.isEmpty(customerUuid)) {
			cim = customerInfoService.selectByCustomerUuid(customerUuid);
			//随访申请状态 0未查看 1允许 2拒绝
			if (cim != null && !StringUtil.isEmpty(doctorUuid) && "0".equals(applyState)) {
				visitApplyMap.put("realName", cim.getRealName());
				visitApplyMap.put("sex", cim.getSex()); // 1是男；2是女
				visitApplyMap.put("age", cim.getAge());
				if (!StringUtil.isEmpty(createTime) && createTime.length() > 10) {
					visitApplyMap.put("createTime", createTime.substring(0, 10));
				} else {
					visitApplyMap.put("createTime", createTime.substring(0, 10));
				}
				visitApplyMap.put("customerUuid", customerUuid);
				visitApplyMap.put("doctorUuid", doctorUuid);
				visitApplyMap.put("applyUuid", applyUuid);
				Image4App[] urls = CompatibleTools.getImages(imgUploadService, fileService,cim.getImage());
				if (!ObjectUtils.isEmpty(urls)) {
					visitApplyMap.put("imgUrl", urls[0]);
				}
				visitApplyMap.put("illnessDescription", cim.getIllnessDescription());
			}
		}
		return visitApplyMap;
	}

	public Map<String, Object> updateViewVisit(String visitRecordUuid) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		VisitRecord vrOut;
			vrOut = (VisitRecord) super.select(new VisitRecord(visitRecordUuid));
			if(vrOut != null) {//在医生查看详情时标识为已读状态
				VisitRecord vrTmp = new VisitRecord(visitRecordUuid);
				vrTmp.setApplyState("1");
				super.update(vrTmp);
				VisitPrecept visitPreceptModel = (VisitPrecept)visitPreceptService.select(new VisitPrecept(vrOut.getPreceptUuid()));
	
				if (!ObjectUtils.isEmpty(visitPreceptModel.getSelfTest())) {
					getSelfTest(resultMap, vrOut, visitPreceptModel);
				}
				if (!ObjectUtils.isEmpty(visitPreceptModel.getDoctorTest())) {
					getDoctorTest(resultMap, vrOut, visitPreceptModel);
				}
				resultMap.put("visitRecord", BeanUtils.bean2Map(vrOut));
			}
		return resultMap;	
	}

	@Override
	public Map<String, Object> countVisitRecordByDoctorUuid(String uuid) throws Exception {
		String sql = "select count(customerUuid) visitNum from visit_record where serviceStaffUuid =  ?";
		List<String> staff = new ArrayList<String>();
		staff.add(uuid);
		Object[] staffparams = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> staffReturn = dao.query(sql, staffparams, null, getQueryCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn.get(0));
	}

	@Override
	public Map<String, Object> selectVisitNumModel(String uuid) throws Exception {
		String sql = "select count(uuid) count from visit_record where visitState = '1' and serviceStaffUuid = ?";
		List<String> staff = new ArrayList<String>();
		staff.add(uuid);
		Object[] staffparams = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> staffReturn = dao.query(sql, staffparams, null, getQueryCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn.get(0));
	}

	private void getSelfTest(Map<String, Object> resultMap, VisitRecord vrOut, VisitPrecept visitPreceptModel) {
		String customerUuid = vrOut.getCustomerUuid();
		List<CsZySubject> selfList = this.getList(visitPreceptModel.getSelfTest());
		if (!ObjectUtils.isEmpty(selfList)) {
			List<Map<String, Object>> resultList = new ArrayList<>();
			for (CsZySubject subject : selfList) {
				if (ObjectUtils.isEmpty(subject)||ObjectUtils.isEmpty(subject.getId())){
					continue;
				}
				ProfessionResult professionResult = new ProfessionResult();
				professionResult.setSubjectId(subject.getId());
				professionResult.setOpenid(customerUuid);

				List<ProfessionResult> csZyResultList = csZyResultService.selectByCondition(professionResult);
				if (!ObjectUtils.isEmpty(csZyResultList)) {
					for (ProfessionResult result : csZyResultList) {

						List<CszyEvaluation> evaluation = cszyEvaluationService.queryByProfessionResult(result);
						if (!ObjectUtils.isEmpty(evaluation)) {
							Map<String, Object> tempResult = new HashMap<>();
							tempResult.put("resultId", result.getId());
							tempResult.put("score", result.getScore());
							tempResult.put("evaluation", evaluation.get(0).getResult());
							tempResult.put("analys", evaluation.get(0).getAnalys());
							tempResult.put("recommendTitle", evaluation.get(0).getRecommendTitle());
							tempResult.put("subject",subject.getTitle());
							tempResult.put("subjectId",subject.getId());
							resultList.add(tempResult);
						}
					}
				}
			}
			resultMap.put("selfList",resultList);
		}
	}

	private void getDoctorTest(Map<String, Object> resultMap, VisitRecord vrOut, VisitPrecept visitPreceptModel) {
		List<CsZySubject> doctorTest = this.getList(visitPreceptModel.getDoctorTest());
		String customerUuid = vrOut.getCustomerUuid();
		if (!ObjectUtils.isEmpty(doctorTest)) {
			List<Map<String, Object>> resultList = new ArrayList<>();
			for (CsZySubject subject : doctorTest) {
				if (ObjectUtils.isEmpty(subject)||ObjectUtils.isEmpty(subject.getId())){
					continue;
				}
				ProfessionResult professionResult = new ProfessionResult();
				professionResult.setSubjectId(subject.getId());
				professionResult.setOpenid(customerUuid);

				List<ProfessionResult> csZyResultList = csZyResultService.selectByCondition(professionResult);
				if (!ObjectUtils.isEmpty(csZyResultList)) {
					for (ProfessionResult result : csZyResultList) {

						List<CszyEvaluation> evaluation = cszyEvaluationService.queryByProfessionResult(result);
						if (!ObjectUtils.isEmpty(evaluation)) {
							Map<String, Object> tempResult = new HashMap<>();
							tempResult.put("resultId", result.getId());
							tempResult.put("score", result.getScore());
							tempResult.put("evaluation", evaluation.get(0).getResult());
							tempResult.put("analys", evaluation.get(0).getAnalys());
							tempResult.put("recommendTitle", evaluation.get(0).getRecommendTitle());
							tempResult.put("subject",subject.getTitle());
							tempResult.put("subjectId",subject.getId());
							resultList.add(tempResult);
						}
					}
				}
			}
			resultMap.put("doctorList",resultList);
		}
	}

	private List<CsZySubject> getList(String str){
		if (!ObjectUtils.isEmpty(str)){
			String[] arr;
			if (str.indexOf(",")>0){
				arr = str.split(",");
			}else if (str.indexOf("，")>0){
				arr = str.split("，");
			}else{
				arr = new String[]{str};
			}
			List<CsZySubject> list = new ArrayList<>();
			if (!ObjectUtils.isEmpty(arr)){
				for (String temp:arr){
					// 获取随访方案的集合列表
					CsZySubject subject = csZySubjectService.selectByPrimaryKey(temp);
					list.add(subject);
				}
			}
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 * 根据医生id,患者id,delFlag,从visit_record表中取出数据
	 * @param delFlag
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 * @throws Exception
	 */
	public VisitRecord getVisitRecordByDoctoridAndCustomerId(String delFlag,String doctorUuid,String customerUuid) throws Exception{
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("select ");
		sqlBuff.append(" UUID,delFlag,opeTime,oper,createTime,customerUuid,preceptUuid,");
		sqlBuff.append(" serviceStaffUuid,visitUuid,illnessRecord,refuseReason,applyState,");
		sqlBuff.append(" visitType,visitState ");
		sqlBuff.append(" from visit_record");
		sqlBuff.append(" where ");
		sqlBuff.append(" delFlag=? and serviceStaffUuid=? and customerUuid=?");

		List<String> info = new ArrayList<String>();
		info.add(delFlag);
		info.add(doctorUuid);
		info.add(customerUuid);
		Object[] cusInfo = info.isEmpty() ? null : info.toArray();
		List<Map<String, Object>> cusInfoReturn = dao.query(sqlBuff.toString(), cusInfo, null, getCache());
		return (ObjectUtils.isEmpty(cusInfoReturn) ? null : SimpleBean2DBHelper.map2Bean(cusInfoReturn.get(0), VisitRecord.class));
	}
	
	@Override
	protected String getCacheName() {return "h1";}

	@Override
	protected String getQueryCacheName() {return "VisitRecordService";}
}
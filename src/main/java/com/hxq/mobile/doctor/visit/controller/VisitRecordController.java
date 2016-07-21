package com.hxq.mobile.doctor.visit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.visitprecept.healthguide.service.HealthGuideService;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.service.VisitPreceptPushService;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushModel;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.doctor.visit.service.IllnessRecordService;
import com.hxq.mobile.doctor.visit.service.VisitApplyService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.entity.weixin.CsZySubject;
import com.hxq.mobile.entity.weixin.CszyEvaluation;
import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.hxq.mobile.patient.visit.service.CszyEvaluationService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.weixin.service.CsZyResultService;
import com.hxq.mobile.weixin.service.CsZySubjectService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.doctor.visit.controller.VisitRecordController")
public class VisitRecordController {
	Logger log = LoggerFactory.getLogger(VisitRecordController.class);

	@Resource(name="com.hxq.mobile.doctor.visit.service.IllnessRecordService")
	private IllnessRecordService illnessService;

	/* 消息service */
	@Autowired
	private InnerMessageService innerMessageService;

	/* 随访的service */
	@Autowired
	private VisitPreceptService visitPreceptService;
	
	@Autowired
	private VisitPreceptPushService visitPreceptPushService;

	@Autowired
	private ServicestaffService servicestaffService;

	/** 健康指导的service */
	@Autowired
	private HealthGuideService healthGuideService;

	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;

	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	/* 随访记录信息表 */
	@Resource(name="com.hxq.mobile.doctor.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;

	@Resource(name="com.hxq.mobile.weixin.service.csZySubjectService")
	private CsZySubjectService csZySubjectService;

	@Resource(name = "com.hxq.mobile.weixin.service.CsZyResultService")
	private CsZyResultService csZyResultService;

	@Resource(name = "com.wxsupport.web.service.CszyEvaluationService")
	private CszyEvaluationService cszyEvaluationService;

	@Resource(name="com.hxq.mobile.common.service.CustomerService")
	private CustomerService customerService;

	@Resource(name="com.hxq.mobile.common.service.CustomerInfoService")
	private CustomerInfoService customerInfoService;

	/**
	 * 获取患者详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mobile/doctor/visit/visitrecord/1.0/getCustomerInfo", method = RequestMethod.GET)
	public ResponseEntity<String> getCustomerInfo(WebRequest request) {
		Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		String uuid = RequestUtil.getFormValue(form, "uuid"); // 获取患者uuid
		if(ObjectUtils.isEmpty(uuid)) return null;

		Map<String, Object> InfoResultmap = new HashMap<String, Object>();
		try {
			Customer cus = (Customer) customerService.select(new Customer(uuid));
			if(cus != null) {
				InfoResultmap = BeanUtils.bean2Map(cus);
				CustomerInfo cusInfo = customerInfoService.selectByCustomerUuid(uuid);
				if(cus != null) {
					InfoResultmap.putAll(BeanUtils.bean2Map(cusInfo));
					InfoResultmap.put("uuid", uuid);
				}
			}
		} catch (Exception e) {
			log.error(uuid, e);
		}
		return JsonUtil.ResponseJson(JsonUtil.toJSONString(InfoResultmap, true, true));
	}
	
	/**
	 * 获取患者填写的随访表单（记录）
	 */
	@RequestMapping(value = "/mobile/doctor/visit/visitrecord/1.0/view/{visitRecordUuid}", method = RequestMethod.GET)
	public
	@ResponseBody
	String viewVisit(HttpServletResponse response, @PathVariable("visitRecordUuid") String visitRecordUuid) {
		if (ObjectUtils.isEmpty(visitRecordUuid)) return null;
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Map<String, Object> resultMap = new HashMap<>();
		Map<String,Object> runMap = new HashMap<>();

		runMap.put("success", SysConst.SUCCESS);
		runMap.put("message", SysConst.SUCCESSMESSAGE);
		resultMap.put("query", runMap);
		try {
			VisitRecord vrOut = (VisitRecord) visitRecordService.select(new VisitRecord(visitRecordUuid));
			if(vrOut != null) {//在医生查看详情时标识为已读状态
				VisitRecord vrTmp = new VisitRecord(visitRecordUuid);
				vrTmp.setApplyState("1");
				visitRecordService.update(vrTmp);
				VisitPreceptModel visitPreceptModel = visitPreceptService.getByUuid(vrOut.getPreceptUuid());

				if (!ObjectUtils.isEmpty(visitPreceptModel.getSelfTest())) {
					getSelfTest(runMap, vrOut, visitPreceptModel);
				}
				if (!ObjectUtils.isEmpty(visitPreceptModel.getDoctorTest())) {
					getDoctorTest(runMap, vrOut, visitPreceptModel);
				}

				resultMap.put("visitRecord", BeanUtils.bean2Map(vrOut));
			}
		} catch (Exception e) {
			log.error(visitRecordUuid, e);
		}
		HttpServletUtils.outJson(response, resultMap);
		return null;
	}

	private void getSelfTest(Map<String, Object> resultMap, VisitRecord vrOut, VisitPreceptModel visitPreceptModel) {
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

	private void getDoctorTest(Map<String, Object> resultMap, VisitRecord vrOut, VisitPreceptModel visitPreceptModel) {
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
					try {
						// 获取随访方案的集合列表
						CsZySubject subject = csZySubjectService.selectByPrimaryKey(temp);
						list.add(subject);
					} catch (Exception e) {
						e.getMessage();
					}
				}
			}
			return list;
		}else{
			return null;
		}
	}

    /**
     * 获取患者填写的病情变化历史记录列表
     */
    @RequestMapping(value="/mobile/doctor/visit/illness/1.0/search", method=RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>> searchIllness(WebRequest request) {
    	Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		try {
			return illnessService.selectList(form, false);
        } catch (Exception e) {
            log.error(RequestUtil.getFormValue(form, "serviceStaffUuid"), e);
        }
		return null;
    }

    /**
     * 获取患者填写的病情变化历史记录详情
     */
    @RequestMapping(value="/mobile/doctor/visit/illness/1.0/view/{visitRecordUuid}", method=RequestMethod.GET)
    public @ResponseBody VisitRecord viewIllness(@PathVariable("visitRecordUuid") String visitRecordUuid) {
    	if(ObjectUtils.isEmpty(visitRecordUuid)) return null;
		try {
			return (VisitRecord) illnessService.select(new VisitRecord(visitRecordUuid));
        } catch (Exception e) {
            log.error(visitRecordUuid, e);
        }
		return null;
    }
    
	/**
	 * 修改关联新的随访方案
	 * 
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/app/pub/doctor/1.0/updateVisitRecord", method = RequestMethod.POST)
	public  ResponseEntity<String> updateVisitRecord(@RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("visitPreceptUuid") String visitPreceptUuid) {//随访方案id
			 
		    BoolResult br = null;
		try {
			//判断新随访方案是否存在
			VisitPreceptModel vpm = visitPreceptService.getByUuid(visitPreceptUuid);
			if(vpm==null) {
				br = BoolResult.error("随访方案为空！");
				return JsonUtil.ResponseJson(JsonUtil.toJSONString(br));
			}
	
			//更新随访申请的随访方案
			Map<String, Object> form = new HashMap<String, Object>();
			form.put("doctorUuid", doctorUuid);
			form.put("customerUuid", customerUuid);
			form.put("applyState", "1");
			List<Map<String, Object>> lst = visitApplyService.selectList(form, false);
			if(ObjectUtils.isEmpty(lst)) {
				Map<String, Object> row = lst.get(0);
				VisitApply va = new VisitApply((String) row.get("uuid"));
				va.setVisitPreceptUuid(visitPreceptUuid);
				visitApplyService.update(va);
			}

			//失效原来的随访表单记录
			VisitRecord vr = null;
			form = new HashMap<String, Object>();
			form.put("serviceStaffUuid", doctorUuid);
			form.put("customerUuid", customerUuid);
			form.put("visitState", "0");
			List<Map<String, Object>> visitRecordList = visitRecordService.selectList(form, false);
			if (visitRecordList != null && visitRecordList.size() > 0) {
				for (Map<String, Object> row : visitRecordList) {
					vr = new VisitRecord((String) row.get("uuid"));
					vr.setVisitState("1");//填写状态已完成
					visitRecordService.update(vr);
				}
			}

			//生成新随访记录表单
			vr = new VisitRecord();
			vr.setCustomerUuid(customerUuid);
			vr.setPreceptUuid(visitPreceptUuid);
			vr.setServiceStaffUuid(doctorUuid);
			visitRecordService.insert(vr);

			/******* 16/02/01 同时添加 随访周期消息推送表 ************/
			// 根据随访id得倒随访推送表。
			VisitPreceptPushModel vppm =visitPreceptPushService.getByVisitPreceptUuid(doctorUuid,customerUuid);
			// 如果存在就更新。不存在就添加
			if (vppm == null) {
	            vppm = new VisitPreceptPushModel();
	            vppm.setCreateTime(DateFormatHelper.getNowTimeStr());// 创建时间
	            vppm.setDoctorUuid(doctorUuid);// 医生id
	            vppm.setCustomerUuid(customerUuid);// 患者id
	            vppm.setPeriod(vpm.getPeriod());// 方案周期
	            vppm.setPushTime(DateFormatHelper.getNowTimeStr());// 推送时间
	            vppm.setPushTimes(0);// 推送次数
	            vppm.setVisitPreceptUuid(vpm.getUuid());// 方案id
	            visitPreceptPushService.create(vppm);
	        } else {
	            if(!vppm.getUuid().equals(visitPreceptUuid)){
	                vppm.setVisitPreceptUuid(visitPreceptUuid);
	                vppm.setPushTime(DateFormatHelper.getNowTimeStr());// 推送时间
	                vppm.setPushTimes(vppm.getPushTimes() + 1);// 推送次数+1
	                visitPreceptPushService.update(vppm);// 更新
	            }
	        }

			//关联方案 保存新的健康指导 之前的指导全部设置为过期
			List<HealthGuideModel> guides = healthGuideService.getHealthGuideListByCustomerUuidAndDoctorUuid(doctorUuid, customerUuid);
			if(guides !=null && guides.size()>0){
				for(HealthGuideModel guide:guides){
					guide.setState("1");
					guide.setSpState("1");
					guide.setReState("1");
					healthGuideService.update(guide);
				}
			}
			HealthGuideModel guide = new HealthGuideModel();
			guide.setDiet(null);
			guide.setSports(null);
			guide.setRest(vpm.getSleep());
			guide.setState("0");
			healthGuideService.create(guide);

			/************************ 添加结束 *************************/
			// 保存消息到数据库，并推送到手机终端
			String doctorName = servicestaffService.getServiceStaffNameByUuid(doctorUuid);
			if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
				String content = doctorName + MessageHelper.getMessage("addVisitRecord.showmessage.newAdd");
				innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
						InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
						PushConst.push_client_customer, "doctorUuid", doctorUuid, InnerMessageTypeEnum.VISITDETAIL.getValue());
			}

			//return BoolResult.right();
			br = BoolResult.right("成功关联新的随访方案！");
			return JsonUtil.ResponseJson(JsonUtil.toJSONString(br));
		} catch (Exception e) {
			log.error(doctorUuid, e);
//			return BoolResult.error("修改出错，请稍后再试！");
			br = BoolResult.error("修改出错，请稍后再试！");
			return JsonUtil.ResponseJson(JsonUtil.toJSONString(br));
		}
	}
    
}

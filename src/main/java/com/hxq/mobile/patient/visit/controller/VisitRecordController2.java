package com.hxq.mobile.patient.visit.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.hxq.mobile.entity.visit.DoctorAdvice;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.entity.visit.VisitRecordExtend;
import com.hxq.mobile.patient.visit.service.CsResultService;
import com.hxq.mobile.patient.visit.service.CustomerDoctorReleService;
import com.hxq.mobile.patient.visit.service.IllnessRecordService;
import com.hxq.mobile.patient.visit.service.ProfessionGatherService;
import com.hxq.mobile.patient.visit.service.ProfessionSubjectService;
import com.hxq.mobile.patient.visit.service.VisitApplyService;
import com.hxq.mobile.patient.visit.service.VisitPreceptExtendService;
import com.hxq.mobile.patient.visit.service.VisitPreceptService;
import com.hxq.mobile.patient.visit.service.VisitRecordExtendService;
import com.hxq.mobile.patient.visit.service.VisitRecordService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Controller("com.hxq.mobile.patient.visit.controller.VisitRecordController2")
public class VisitRecordController2 {
	Logger log = LoggerFactory.getLogger(VisitRecordController2.class);//日志
	
	@Resource(name="com.hxq.mobile.patient.visit.service.VisitRecordExtendService")
	private VisitRecordExtendService extendservice;
	/* 随访记录信息表 */
	@Resource(name="com.hxq.mobile.patient.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;

	/*注入申请随访信息*/
	@Resource(name="com.hxq.mobile.patient.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;
	
	@Resource(name="com.hxq.mobile.patient.visit.service.ProfessionGatherService")
	private ProfessionGatherService professionGatherService;

	@Resource(name = "com.hxq.mobile.patient.visit.service.ProfessionSubjectService")
	private ProfessionSubjectService professionSubjectService;
	@Resource(name = "com.hxq.mobile.patient.visit.service.VisitPreceptExtendService")
	private VisitPreceptExtendService visitPreceptExtendService;
	@Resource(name = "com.hxq.mobile.patient.visit.service.CsResultService")
	private CsResultService csResultService;
	
	@Resource(name = "com.hxq.mobile.patient.visit.service.VisitPreceptService")
	private VisitPreceptService visitPreceptService;

	@Resource(name = "com.hxq.mobile.patient.visit.service.IllnessRecordService")
	private IllnessRecordService illnessRecordService;
	
	@Resource(name = "com.hxq.mobile.patient.visit.service.CustomerDoctorReleService")
	private CustomerDoctorReleService  customerDoctorReleService;

	/**
	 * 随访医生-随访表单-服药记录(NoVisit) 2.0
	 * @param bean
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/2.0/addMedicationRecordNoVisit", method = RequestMethod.POST)
	public @ResponseBody ApiResult addDoctorAdviceNoVisit2(DoctorAdvice bean) {
		try {
			BeanUtils.trimStringField(bean);
			bean.setVisitRecordUuid(queryOrCreateVisit(bean.getCustomerUuid(), bean.getServiceStaffUuid()));
			String strError = verifyDoctorAdvice(bean);
			if (StringUtil.isEmpty(strError)==false) {
				return ApiResult.error(ApiCode.BAD_REQUEST, strError);
			}
			// 获取随访表单信息
			VisitRecord visitRecord = (VisitRecord) extendservice.select(new VisitRecord(bean.getVisitRecordUuid()));
			if (visitRecord == null) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "随访记录信息不存在");
			}
			bean.setCustomerUuid(visitRecord.getCustomerUuid());// 新增服药记录
			if (!StringUtil.isEmpty(visitRecord.getServiceStaffUuid())) {
				bean.setServiceStaffUuid(visitRecord.getServiceStaffUuid());
			}
			bean.setType("0");
			extendservice.insert(bean);
			return	ApiResult.right();
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 随访医生-随访表单-体重（NoVisit）2.0
	 * @param result
	 * @param customerUuid
	 * @return
	 */
	@RequestMapping(value="/mobile/patient/visit/visitrecord/2.0/saveweightNoVisit", method=RequestMethod.POST)
	public @ResponseBody ApiResult saveWeightNoVisit2(WebRequest request) {
		String strError = null;
		try {
			Map<String, Object> params = RequestUtil.cleanParameterMap(request.getParameterMap());
            strError = verifyWeight(params);
            if (ObjectUtils.isEmpty(strError)) {
            	String customerUuid = RequestUtil.getFormValue(params, "customerUuid");
				VisitRecordExtend bean = new VisitRecordExtend(IdentityHelper.uuid2(),
						queryOrCreateVisit(customerUuid, RequestUtil.getFormValue(params, "serviceStaffUuid")));
    			bean.setResult(RequestUtil.getFormValue(params, "result"));
    			bean.setType("5");
    			extendservice.insert(bean);
    			return ApiResult.right();
            } else {
            	return ApiResult.error(ApiCode.BAD_REQUEST, strError);
            }
	    } catch (Exception e) {
	        log.error("", e);
	        return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
	    }
	}

	/**
	 * 随访医生-随访表单-添加药物不良反应(NoVisit)2.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/app/customer/patient/2.0/addDrugReactionNoVisit", method=RequestMethod.POST)
	public @ResponseBody ApiResult addDrugReactionNoVisit2(WebRequest request) {
		String strError = null;
        try {
        	Map<String, Object> params = RequestUtil.cleanParameterMap(request.getParameterMap());
            strError = verifyDrugReaction(params);
            if (ObjectUtils.isEmpty(strError)) {
            	params.put("visitRecordUuid", queryOrCreateVisit(
            			RequestUtil.getFormValue(params, "customerUuid"),
            			RequestUtil.getFormValue(params, "serviceStaffUuid")));
            	extendservice.insertDrugReaction(params);
            	return ApiResult.right();
            } else {
            	return ApiResult.error(ApiCode.BAD_REQUEST, strError);
            }
        } catch (Exception e) {
            log.error("", e);  
            return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
        }
	}

	/**
	 * 随访医生-随访表单-病情变化(NoVisit)2.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/app/customer/patient/2.0/addIllnessRecordNoVisit", method=RequestMethod.POST)
	public @ResponseBody ApiResult addIllnessRecordNoVisit2(WebRequest request) {
		String strError = null;
		try {
			Map<String, Object> params = RequestUtil.cleanParameterMap(request.getParameterMap());
			strError = verifyIllnessRecord(params);
			if (ObjectUtils.isEmpty(strError)) {
				params.put("visitRecordUuid", queryOrCreateVisit(
            			RequestUtil.getFormValue(params, "customerUuid"),
            			RequestUtil.getFormValue(params, "serviceStaffUuid")));
				extendservice.insertIllnessRecord(params);
            	return ApiResult.right();
            } else {
            	return ApiResult.error(ApiCode.BAD_REQUEST, strError);
            }
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 随访医生-随访表单-其他检查及结果(NoVisit)2.0
	 * @param name
	 * @param result
	 * @param imgs
	 * @param customerUuid
	 * @return
	 */
	@RequestMapping(value="/app/customer/patient/2.0/addVisitRecordExtendNoVisit", method=RequestMethod.POST)
	public @ResponseBody ApiResult addVisitRecordExtendNoVisitTwo(WebRequest request) {
		String strError = null;
		try {
			Map<String, Object> params = RequestUtil.cleanParameterMap(request.getParameterMap());
			strError = verifyVisitRecordExtend(params);
			if (ObjectUtils.isEmpty(strError)) {
				String customerUuid = RequestUtil.getFormValue(params, "customerUuid");
				VisitRecordExtend bean = new VisitRecordExtend(IdentityHelper.uuid2(),
						queryOrCreateVisit(customerUuid, RequestUtil.getFormValue(params, "serviceStaffUuid")));
				bean.setCustomerUuid(customerUuid);
				bean.setPreceptExtendUuid(RequestUtil.getFormValue(params, "preceptExtendUuid"));
				bean.setResult(RequestUtil.getFormValue(params, "result"));
				bean.setImages(RequestUtil.getFormValue(params, "images"));
				bean.setType("4");
				extendservice.insert(bean);
				return ApiResult.right();
			} else {
				return ApiResult.error(ApiCode.BAD_REQUEST, strError);
			}
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 返回随访周期内未填写的表单项目
	 * @param request
	 * @param customerUuid
	 * @param serviceStaffUuid
	 * @return
	 */
	@RequestMapping(value = "/mobile/patient/visit/visitrecord/1.0/checkVisitRecord", method = RequestMethod.GET)
	public @ResponseBody ApiResult checkVisitRecord(@RequestParam("customerUuid") String customerUuid,
			@RequestParam("serviceStaffUuid") String serviceStaffUuid) {

		customerUuid = StringUtils.trimToEmpty(customerUuid);
		if (ObjectUtils.isEmpty(customerUuid)) return ApiResult.error(ApiCode.BAD_REQUEST, "患者id不能为空！");

		try {
			serviceStaffUuid = StringUtils.trimToEmpty(serviceStaffUuid);
			if(StringUtil.isEmpty(serviceStaffUuid)) {
				serviceStaffUuid = customerDoctorReleService.selectDoctorid(customerUuid);
			}

			//查看 1睡眠情况，2进食情况，3其他情况，5体重，6心电图，7血常规，8肝功能，自评、医评  的时间
			Map<String, Object> visitPreceptPeriod = visitPreceptService.selectPeriod(serviceStaffUuid, customerUuid);
			if(ObjectUtils.isEmpty(visitPreceptPeriod)) return ApiResult.error(ApiCode.BAD_REQUEST, "患者没有关联随访方案！");

			Map<String, Object> mapReturn = new HashMap<String, Object>();
			mapReturn.put("uuid", visitPreceptPeriod.get("uuid"));

			//true未填写，false已填写
			Boolean exists = illnessRecordService.select4PeriodCheck(customerUuid, (Date) visitPreceptPeriod.get("period"));
			if(exists != null && exists.booleanValue()) mapReturn.put("illness", "1"); //病情变化 

			exists = extendservice.select4SleepPeriodCheck(customerUuid, (Date) visitPreceptPeriod.get("period"));
			if(exists != null && exists.booleanValue()) mapReturn.put("sleep", "1"); //睡眠 情况

			exists = extendservice.select4FoodPeriodCheck(customerUuid,  (Date) visitPreceptPeriod.get("period"));
			if(exists != null && exists.booleanValue()) mapReturn.put("food", "1");//进食情况 

			exists = extendservice.select4OtherPeriodCheck(customerUuid, (Date) visitPreceptPeriod.get("period"));
			if(exists != null && exists.booleanValue()) mapReturn.put("other", "1");//其他情况 

			exists = extendservice.select4WeightPeriodCheck(customerUuid, (Date)visitPreceptPeriod.get("weight"));
			if(exists != null && exists.booleanValue()) mapReturn.put("weight", "1");//体重记录

			exists = extendservice.select4ElectrocardiogramPeriodCheck(customerUuid, (Date)visitPreceptPeriod.get("hepatic"));
			if(exists != null && exists.booleanValue()) mapReturn.put("electrocardiogram", "1");//心电图

			exists = extendservice.select4HepaticPeriodCheck(customerUuid, (Date)visitPreceptPeriod.get("hepatic"));
			if(exists != null && exists.booleanValue()) mapReturn.put("hepatic", "1");//肝功能

			exists = extendservice.select4BloodRoutinePeriodCheck(customerUuid, (Date)visitPreceptPeriod.get("bloodRoutine"));
			if(exists != null && exists.booleanValue()) mapReturn.put("bloodRoutine", "1");//血常规

			//其他项的周期时间
			List<Map<String, Object>> lstOthers = visitPreceptExtendService.selectOtherPeriod((String) visitPreceptPeriod.get("uuid"));//查询周期时间
			if(ObjectUtils.isEmpty(lstOthers)!=true) {
				String uuidOther = null;
				Map<String,Object> mapOther = null;
				List<Map<String,Object>> lst = new ArrayList<Map<String,Object>>();
				for(Map<String, Object> row : lstOthers) {
					uuidOther = (String) row.get("uuid");
					exists = extendservice.select4ExtendPeriodCheck(customerUuid, uuidOther, (Date) row.get("period"));
					if(exists != null && exists.booleanValue()) {
						mapOther = new HashMap<String, Object>();
						mapOther.put("uuid", uuidOther);
						mapOther.put("name", row.get("name"));
					}
					lst.add(mapOther);
				}
				if(ObjectUtils.isEmpty(lst)!=true) mapReturn.put("preceptExtend", lst);
			}

			//自评
			Integer score = null;
			StringBuffer sbf = new StringBuffer(1000);
			String selfTest = (String) visitPreceptPeriod.get("selfTest");
			if(!ObjectUtils.isEmpty(selfTest)) {
				sbf.delete(0, sbf.length());
				String[] ids = selfTest.split(",");
				for(String id : ids) {
					score = csResultService.select4PeriodCheck(id, customerUuid, (Date) visitPreceptPeriod.get("selfPeriod"));
					if(score != null && score.intValue() > -1)
						StringUtils.appendBuffer(sbf, id, sbf.length() > 0 ? "," : "");
				}
				if(ObjectUtils.isEmpty(sbf)!=true) mapReturn.put("selfTest", sbf.toString());
			}
	
			//医评
			String doctorTest = (String)visitPreceptPeriod.get("doctorTest");
			if(!ObjectUtils.isEmpty(doctorTest)) {
				sbf.delete(0, sbf.length());
				String[] ids = doctorTest.split(",");
				for(String id : ids) {
					score = csResultService.select4PeriodCheck(id, customerUuid, (Date) visitPreceptPeriod.get("doctorPeriod"));
					if(score != null && score.intValue() > -1)
						StringUtils.appendBuffer(sbf, id, sbf.length() > 0 ? "," : "");
				}
				if(ObjectUtils.isEmpty(sbf)!=true) mapReturn.put("doctorTest", sbf.toString());
			}
			return ApiResult.right(mapReturn);
		} catch (Exception e) {
	        log.error(customerUuid, e);
	        return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 服药记录保存校验
	 * @param bean  保存对象
	 * @return
	 */
	private String verifyDoctorAdvice(DoctorAdvice bean) {
	    StringBuffer sbf = new StringBuffer(1000);
	    if (ObjectUtils.isEmpty(bean.getVisitRecordUuid()))
	    	StringUtils.appendBuffer(sbf, "缺少随访表单记录ID！", sbf.length() > 0 ? "\r\n" : null);
	    return sbf.length() > 0 ? sbf.toString() : null;
	}

	/**
	 * 体重记录保存校验
	 * @param form
	 * @return
	 */
	private String verifyWeight(Map<String, Object> form) {
	    StringBuffer sbf = new StringBuffer(1000);
	    if (RequestUtil.isEmpty(form, "customerUuid"))
	    	StringUtils.appendBuffer(sbf, "必须提交患者ID！", sbf.length() > 0 ? "\r\n" : null);
	    if (RequestUtil.isEmpty(form, "result"))
	    	StringUtils.appendBuffer(sbf, "必须填写体重！", sbf.length() > 0 ? "\r\n" : null);
	    return sbf.length() > 0 ? sbf.toString() : null;
	}

	/**
	 * 不良反应保存校验
	 * @param bean  保存对象
	 * @return
	 */
	private String verifyDrugReaction(Map<String, Object> form) {
	    StringBuffer sbf = new StringBuffer(1000);
	    if (RequestUtil.isEmpty(form, "customerUuid"))
	    	StringUtils.appendBuffer(sbf, "必须提交患者ID！", sbf.length() > 0 ? "\r\n" : null);
	    if (RequestUtil.isEmpty(form, "frequency"))
	    	StringUtils.appendBuffer(sbf, "必须填写症状描述！", sbf.length() > 0 ? "\r\n" : null);
	    return sbf.length() > 0 ? sbf.toString() : null;
	}

	/**
     * 病情变化保存校验
     * @param bean  保存对象
     * @return
     */
    private String verifyIllnessRecord(Map<String, Object> form) {
        StringBuffer sbf = new StringBuffer(1000);
	    if (RequestUtil.isEmpty(form, "customerUuid"))
	    	StringUtils.appendBuffer(sbf, "必须提交患者ID！", sbf.length() > 0 ? "\r\n" : null);
        if (RequestUtil.isEmpty(form, "illnessstate"))
        	StringUtils.appendBuffer(sbf, "必须填写病情变化！", sbf.length() > 0 ? "\r\n" : null);
        return sbf.length() > 0 ? sbf.toString() : null;
    }

    /**
     * 其他检查项保存校验
     * @param form
     * @return
     */
    private String verifyVisitRecordExtend(Map<String, Object> form) {
    	StringBuffer sbf = new StringBuffer(1000);
	    if(RequestUtil.isEmpty(form, "customerUuid"))
	    	StringUtils.appendBuffer(sbf, "必须提交患者ID！", sbf.length() > 0 ? "\r\n" : null);
        if(RequestUtil.isEmpty(form, "preceptExtendUuid"))
        	StringUtils.appendBuffer(sbf, "必须提交检查项ID！", sbf.length() > 0 ? "\r\n" : null);
        if (RequestUtil.isEmpty(form, "result") && RequestUtil.isEmpty(form, "images"))
        	StringUtils.appendBuffer(sbf, "检查结果和检查图像不能都为空！", sbf.length() > 0 ? "\r\n" : null);
        return sbf.length() > 0 ? sbf.toString() : null;
    }

    /**
     * 查找当前周期内的的随访（表单）记录，有返回记录id，没有则生成新纪录
     * @param customerUuid
     * @param serviceStaffUuid
     * @return
     * @throws Exception
     */
    private String queryOrCreateVisit(String customerUuid, String serviceStaffUuid) throws Exception {
    	if(ObjectUtils.isEmpty(serviceStaffUuid)) {
    		serviceStaffUuid = customerDoctorReleService.selectDoctorid(customerUuid);
    	}
    	if(ObjectUtils.isEmpty(serviceStaffUuid)) return null;

   		Map<String, Object> period = visitPreceptService.selectPeriod(serviceStaffUuid, customerUuid);
   		if(ObjectUtils.isEmpty(period)) return null;
   		String preceptUuid = (String) period.get("uuid");//随访方案id

   		VisitRecord vr = visitRecordService.selectRecentVisitRecordByPreceptUuid(
   				customerUuid, serviceStaffUuid, preceptUuid, (Date) period.get("period"));

		if(vr != null) {
			if(vr.getApplyState().equalsIgnoreCase("1")) {
				VisitRecord vrTmp = new VisitRecord(vr.getUuid());
				vrTmp.setApplyState("0");
				visitRecordService.update(vrTmp);
			}
		} else {
			vr = new VisitRecord(IdentityHelper.uuid2() );
			vr.setCustomerUuid(customerUuid);
			vr.setServiceStaffUuid(serviceStaffUuid);
			vr.setPreceptUuid(preceptUuid);
			visitRecordService.insert(vr);
		}
		return vr.getUuid();
    }
}

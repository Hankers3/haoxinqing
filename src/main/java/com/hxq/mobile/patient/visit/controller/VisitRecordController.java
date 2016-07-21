package com.hxq.mobile.patient.visit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.hxq.mobile.entity.visit.DoctorAdvice;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.entity.visit.VisitRecordExtend;
import com.hxq.mobile.patient.visit.service.VisitApplyService;
import com.hxq.mobile.patient.visit.service.VisitRecordExtendService;
import com.hxq.mobile.patient.visit.service.VisitRecordService;
import com.hxq.mobile.util.CompatibleTools;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Controller("com.hxq.mobile.patient.visit.controller.VisitRecordController")
public class VisitRecordController {
	Logger log = LoggerFactory.getLogger(VisitRecordController.class);

	@Resource(name="com.hxq.mobile.patient.visit.service.VisitRecordExtendService")
	private VisitRecordExtendService extendservice;

	/* 随访记录信息表 */
	@Resource(name="com.hxq.mobile.patient.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;
	/*注入申请随访信息*/
	@Resource(name="com.hxq.mobile.patient.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;
	
    /**
	 * 保存随访记录下的体重记录
	 */
	@RequestMapping(value="/mobile/patient/visit/visitrecord/1.0/saveweight", method=RequestMethod.POST)
	public @ResponseBody BoolResult saveWeight(@RequestParam("result") String result,
		   @RequestParam("visitRecordUuid") String visitRecordUuid) {
		BoolResult br = null;
		try {
			VisitRecordExtend bean = new VisitRecordExtend(IdentityHelper.uuid2(), visitRecordUuid);
			bean.setResult(result);
			bean.setType("5");
			extendservice.insert(bean);
			br = BoolResult.right("保存成功！");
	    } catch (Exception e) {
	        log.error(visitRecordUuid, e);
	        br = BoolResult.error("保存出错，请稍后再试！");
	    }
		return br;
	}

	@RequestMapping(value="/mobile/patient/visit/visitrecord/1.0/saveweightNoVisit", method=RequestMethod.POST)
	public @ResponseBody BoolResult saveWeightNoVisit(@RequestParam("result") String result,
			   @RequestParam("customerUuid") String customerUuid) {
			BoolResult br = null;
			try {
				String visitRecordUuid = queryOrCreateVisit(customerUuid);
				VisitRecordExtend bean = new VisitRecordExtend(IdentityHelper.uuid2(), visitRecordUuid);
				bean.setResult(result);
				bean.setType("5");
				extendservice.insert(bean);
				br = BoolResult.right("保存成功！");
		    } catch (Exception e) {
		        log.error(customerUuid, e);
		        br = BoolResult.error("保存出错，请稍后再试！");
		    }
			return br;
	}

	/**
	 * 保存随访记录下的其他检查及结果
	 */
	@RequestMapping(value="/app/customer/patient/1.0/addVisitRecordExtend", method=RequestMethod.POST)
	public @ResponseBody BoolResult addVisitRecordExtend(@RequestParam("checkName") String name,
			@RequestParam(value="result", required=false) String result,
			@RequestParam(value="image", required=false) String imgs,
			@RequestParam("visitRecordUuid") String visitRecordUuid) {
		BoolResult br = null;
		try {
			VisitRecordExtend bean = new VisitRecordExtend(IdentityHelper.uuid2(), visitRecordUuid);
			bean.setName(name);
			bean.setResult(result);
			bean.setType("4");
			extendservice.insertCheckResult(bean, imgs);
			br = BoolResult.right();
	    } catch (Exception e) {
	        log.error(visitRecordUuid, e);
	        br = BoolResult.error("保存出错，请稍后再试！");
	    }
		return br;
	}
	
	@RequestMapping(value="/app/customer/patient/1.0/addVisitRecordExtendNoVisit", method=RequestMethod.POST)
	public @ResponseBody BoolResult addVisitRecordExtendNoVisit(@RequestParam("checkName") String name,
			@RequestParam(value="result", required=false) String result,
			@RequestParam(value="image", required=false) String imgs,
			@RequestParam("customerUuid") String customerUuid) {
		BoolResult br = null;
		try {
			String visitRecordUuid = queryOrCreateVisit(customerUuid);
			VisitRecordExtend bean = new VisitRecordExtend(IdentityHelper.uuid2(), visitRecordUuid);
			bean.setName(name);
			bean.setResult(result);
			bean.setType("4");
			extendservice.insertCheckResult(bean, imgs);
			br = BoolResult.right();
		} catch (Exception e) {
			log.error(customerUuid, e);
			br = BoolResult.error("保存出错，请稍后再试！");
		}
		return br;
	}
	
	
	
	/**
	 * 保存随访记录下的病情变化
	 */
	@RequestMapping(value="/app/customer/patient/1.0/addIllnessRecord", method=RequestMethod.POST)
	public @ResponseBody BoolResult addIllnessRecord(WebRequest request) {
		String strError = null;
        try {
        	Map<String, Object> params = RequestUtil.cleanParameterMap(request.getParameterMap());
            strError = verifyIllnessRecord(params);
            if (ObjectUtils.isEmpty(strError)) {
            	extendservice.insertIllnessRecord(params);
            }
        } catch (Exception e) {
            log.error("", e);
            strError = "保存出错，请稍后再试！";
        }
        
        
        return ObjectUtils.isEmpty(strError) ? BoolResult.right() : BoolResult.error(strError);
	}
	
	@RequestMapping(value="/app/customer/patient/1.0/addIllnessRecordNoVisit", method=RequestMethod.POST)
	public @ResponseBody BoolResult addIllnessRecordNoVisit(WebRequest request) {
		String strError = null;
		try {
			Map<String, Object> params = RequestUtil.cleanParameterMap(request.getParameterMap());
			String customerUuid = RequestUtil.getFormValue(params, "customerUuid");
			String visitRecordUuid = queryOrCreateVisit(customerUuid);
			params.put("visitRecordUuid", visitRecordUuid);
			strError = verifyIllnessRecord(params);
			if (ObjectUtils.isEmpty(strError)) {
				extendservice.insertIllnessRecord(params);
			}
		} catch (Exception e) {
			log.error("", e);
			strError = "保存出错，请稍后再试！";
		}
		return ObjectUtils.isEmpty(strError) ? BoolResult.right() : BoolResult.error(strError);
	}

	/**
	 * 保存随访记录添加药物不良反应
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/app/customer/patient/1.0/addDrugReaction", method=RequestMethod.POST)
	public String addDrugReaction(WebRequest request, HttpServletResponse response) {
		String strError = null;
        try {
        	Map<String, Object> params = RequestUtil.cleanParameterMap(request.getParameterMap());
            strError = verifyDrugReaction(params);
            if (ObjectUtils.isEmpty(strError)) {
            	extendservice.insertDrugReaction(params);
            }
        } catch (Exception e) {
            log.error("", e);
            strError = "保存出错，请稍后再试！";
        }
        //return ObjectUtils.isEmpty(strError) ? BoolResult.right() : BoolResult.error(strError);
        //兼容原发布接口定义
        CompatibleTools.returnJsonByOldVersion(response, strError);
        return null;
	}

	@RequestMapping(value="/app/customer/patient/1.0/addDrugReactionNoVisit", method=RequestMethod.POST)
	public String addDrugReactionNoVisit(WebRequest request, HttpServletResponse response) {
		String strError = null;
        try {
        	Map<String, Object> params = RequestUtil.cleanParameterMap(request.getParameterMap());
        	String customerUuid = RequestUtil.getFormValue(params, "customerUuid");
			String visitRecordUuid = queryOrCreateVisit(customerUuid);
			params.put("visitRecordUuid", visitRecordUuid);
            strError = verifyDrugReaction(params);
            if (ObjectUtils.isEmpty(strError)) {
            	extendservice.insertDrugReaction(params);
            }
        } catch (Exception e) {
            log.error("", e);
            strError = "保存出错，请稍后再试！";
        }
        //return ObjectUtils.isEmpty(strError) ? BoolResult.right() : BoolResult.error(strError);
        //兼容原发布接口定义
        CompatibleTools.returnJsonByOldVersion(response, strError);
        return null;
	}
	/**
	 * 患者端随访记录添加服药记录接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/addMedicationRecord", method = RequestMethod.POST)
	public String addDoctorAdvice(DoctorAdvice bean, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try {
		// 判断参数是否为空
		String strError = verifyDoctorAdvice(bean);
		if (StringUtil.isEmpty(strError)==false) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, strError), "");
			return null;
		}
		// 获取随访表单信息
		VisitRecord visitRecord = (VisitRecord) extendservice.select(new VisitRecord(bean.getVisitRecordUuid()));
		if (visitRecord == null) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "随访记录信息不存在"), "");
			return null;
		}

		// 新增服药记录
		bean.setCustomerUuid(visitRecord.getCustomerUuid());
		if (!StringUtil.isEmpty(visitRecord.getServiceStaffUuid())) {
			bean.setServiceStaffUuid(visitRecord.getServiceStaffUuid());
		}
		bean.setType("0");
		//bean.setMedicalDateBegin(bean.getMedicalDateBegin());
		//bean.setMedicalDateEnd(bean.getMedicalDateEnd());

			extendservice.insert(bean);
			// 获取参数值并且进行非空判断
			Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
			HttpServletUtils.outJsonCallBack(response, jsonMap, "");
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/app/customer/patient/1.0/addMedicationRecordNoVisit", method = RequestMethod.POST)
	public String addDoctorAdviceNoVisit(DoctorAdvice bean, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try {
		// 判断参数是否为空
		String customerUuid = bean.getCustomerUuid();
		String visitRecordUuid = queryOrCreateVisit(customerUuid);
		bean.setVisitRecordUuid(visitRecordUuid);
		String strError = verifyDoctorAdvice(bean);
		if (StringUtil.isEmpty(strError)==false) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, strError), "");
			return null;
		}
		// 获取随访表单信息
		VisitRecord visitRecord = (VisitRecord) extendservice.select(new VisitRecord(bean.getVisitRecordUuid()));
		if (visitRecord == null) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "随访记录信息不存在"), "");
			return null;
		}

		// 新增服药记录
		bean.setCustomerUuid(visitRecord.getCustomerUuid());
		if (!StringUtil.isEmpty(visitRecord.getServiceStaffUuid())) {
			bean.setServiceStaffUuid(visitRecord.getServiceStaffUuid());
		}
		bean.setType("0");
		//bean.setMedicalDateBegin(DateUtils.parseDate(request.getParameter("medicalDateBegin")));
		//bean.setMedicalDateEnd(DateUtils.parseDate(request.getParameter("medicalDateEnd")));

			extendservice.insert(bean);
			// 获取参数值并且进行非空判断
			Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
			HttpServletUtils.outJsonCallBack(response, jsonMap, "");
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}
    /**
	 * 患者端 提交
	 * （visit-rcord） 
	 */
	@RequestMapping(value="/mobile/patient/visit/visitrecord/1.0/commit", method=RequestMethod.GET)
	public ResponseEntity<String> commit(@RequestParam("visitRecordUuid") String visitRecordUuid){
		BoolResult br = null;
		try {
			VisitRecord vr = new VisitRecord(visitRecordUuid);
			vr.setVisitState("1");//0未完成,1已完成
			visitRecordService.update(vr);
			br = BoolResult.right("提交成功！");
		} catch (Exception e) {
			br = BoolResult.error("提交异常！");
		}    	
		return JsonUtil.ResponseJson(JsonUtil.toJSONString(br));
	}
    /**
     * 病情变化保存校验
     * @param bean  保存对象
     * @return
     */
    private String verifyIllnessRecord(Map<String, Object> form) {
        StringBuffer sbf = new StringBuffer(1000);
        if (RequestUtil.isEmpty(form, "illnessstate"))
        	StringUtils.appendBuffer(sbf, "必须填写病情变化！", sbf.length() > 0 ? "\r\n" : null);
        if (RequestUtil.isEmpty(form, "visitRecordUuid"))
            StringUtils.appendBuffer(sbf, "随访表单编号不能为空！", sbf.length() > 0 ? "\r\n" : null);
        return sbf.length() > 0 ? sbf.toString() : null;
    }
    
    /**
     * 不良反应保存校验
     * @param bean  保存对象
     * @return
     */
    private String verifyDrugReaction(Map<String, Object> form) {
        StringBuffer sbf = new StringBuffer(1000);
        if (RequestUtil.isEmpty(form, "frequency"))
        	StringUtils.appendBuffer(sbf, "必须填写症状描述！", sbf.length() > 0 ? "\r\n" : null);
        if (RequestUtil.isEmpty(form, "visitRecordUuid"))
            StringUtils.appendBuffer(sbf, "随访表单编号不能为空！", sbf.length() > 0 ? "\r\n" : null);
        return sbf.length() > 0 ? sbf.toString() : null;
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
     * 服药
     * @param bean  保存对象
     * @return
     * @throws Exception 
     */
    private String queryOrCreateVisit(String customerUuid) throws Exception {
		Map<String, Object> mapOut = visitApplyService.selectCustomerFromVisitRecordUuid(customerUuid,"1");
		Map<String, Object> mapIn = new HashMap<String, Object>(); 
		String serviceStaffUuid = (String)mapOut.get("serviceStaffUuid");
		String visitPreceptUuid = (String)mapOut.get("visitPreceptUuid");
		mapIn.put("customerUuid", customerUuid);
		mapIn.put("serviceStaffUuid", serviceStaffUuid);
		mapIn.put("visitState", 0);
		List<Map<String, Object>> lst;
		String visitRecordUuid =  null;
		lst = visitRecordService.selectList(mapIn,false);
		if(lst.size()> 0) {
			visitRecordUuid = (String) lst.get(0).get("uuid");
		} else {
			visitRecordUuid = IdentityHelper.uuid2();
			//生成患者自定义的随访记录表单
			VisitRecord vr = new VisitRecord(visitRecordUuid);
			vr.setCustomerUuid(customerUuid);
			vr.setPreceptUuid(visitPreceptUuid);
			vr.setServiceStaffUuid(serviceStaffUuid);
			visitRecordService.insert(vr);
		}
		return visitRecordUuid;
    }
}

package com.hxq.mobile.doctor.visit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.hxq.mobile.doctor.visit.service.VisitApplyService;
import com.hxq.mobile.doctor.visit.service.VisitPreceptService;
import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.ObjectUtils;

@Controller
public class VisitApplyController2 {
	Logger log = LoggerFactory.getLogger(VisitApplyController2.class);

	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;

	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitPreceptService")
	private VisitPreceptService visitPreceptService;
	
/*	@Resource(name = "com.hxq.mobile.common.service.imp.CustomerInfoService")
	private CustomerInfoService customerInfoService;*/
	
	/**
	 * 获取随访申请详情页面
	 * 
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @param applyUuid
	 *            申请id
	 * @return
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/getApplyDetail", method = RequestMethod.GET)
	public @ResponseBody ApiResult getApplyDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("applyUuid") String applyUuid) {
		try{
		// 判断申请随访id不能为空
		if(ObjectUtils.isEmpty(applyUuid)) return ApiResult.error(ApiCode.BAD_REQUEST,null);
		// 获取随访申请详情
			VisitApply ApplyModel = (VisitApply) visitApplyService.select(new VisitApply(applyUuid));
			if (ApplyModel==null) ApiResult.error(ApiCode.BAD_REQUEST,"取随访申请不存在");
			String customerUuid = ApplyModel.getCustomerUuid();
			String doctorUuid = ApplyModel.getServiceStaffUuid();
			String symptoms = ApplyModel.getSymptoms();
			
			Map<String, Object> relist = visitApplyService.selectApplyDetail(customerUuid,applyUuid,doctorUuid,symptoms);
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error(applyUuid, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}
	
	/**
	 * 获取随访申请列表
	 * 
	 * @param response
	 *            response
	 * @param doctorid
	 *            医生id
	 * @return null
	 */
	@RequestMapping(value="/app/pub/doctor/2.0/getVisitApplyList", method=RequestMethod.GET)
	public @ResponseBody ApiResult getVisitApplyList(WebRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorid) {

		// 判断医生id不能为空
		if(ObjectUtils.isEmpty(doctorid)) return ApiResult.error(ApiCode.BAD_REQUEST,null);
		
		
		// 获取随访记录的集合列表
		List<Map<String, Object>> lstVisitApply = null;
		try {
			lstVisitApply = visitApplyService.selectList(
					RequestUtil.cleanParameterMap(request.getParameterMap()), false);
		} catch (Exception e) {
			log.error(doctorid, e);
			return ApiResult.right("查询随访申请失败");
		}

		List<Map<String, Object>> relist = new ArrayList<>();
		Map<String, Object> relistMap;
		try {
			if (lstVisitApply != null && lstVisitApply.size() > 0) {
				for (Map<String, Object> va : lstVisitApply) {
					// 医生、患者和随访记录的uuid
					String customerUuid = (String) va.get("customerUuid");
					String doctorUuid = (String) va.get("serviceStaffUuid");
					String applyUuid = (String) va.get("uuid");
					String createTime = (String) va.get("createTime");
					relistMap = visitApplyService.selectVisitApplyList(customerUuid,doctorUuid,applyUuid,createTime);
					relist.add(relistMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
		return ApiResult.right(relist);
	}	
	
	/**
	 * 随访申请-拒绝
	 * 
	 * @param response
	 * @param applyUuid
	 *            applyUuid
	 * @param refuseReason
	 *            拒绝理由
	 * @return
	 */
	@RequestMapping(value = "/app/public/refuseapply/2.0/refuseVivistApply", method = RequestMethod.POST)
	public @ResponseBody ApiResult refuseVivistApply(HttpServletResponse response,
			@RequestParam("applyUuid") String applyUuid,
			@RequestParam("refuseReason") String refuseReason) {

		ApiResult br = null;
		// 判断用户是否填写拒绝理由
		if (ObjectUtils.isEmpty(applyUuid) || ObjectUtils.isEmpty(refuseReason)) return ApiResult.error(ApiCode.BAD_REQUEST,"请填写拒绝理由");

		try {
			int success = visitApplyService.updateForRefuseVivistApply(applyUuid,refuseReason);
		    return ApiResult.right(success);	
		} catch (Exception e) {
			log.error(applyUuid, e);
			br = ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	    return br;
	}
	
	/**
	 * 同意并关联随访方案
	 * 
	 * @author xp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/addVisitRecord", method = RequestMethod.GET)
	public @ResponseBody ApiResult agreeVisitApply(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("visitUuid") String visitApplyUuid,
			@RequestParam("visitPreceptUuid") String visitPreceptUuid) {
		
		ApiResult br = null;
		if (ObjectUtils.isEmpty(visitApplyUuid) || ObjectUtils.isEmpty(visitPreceptUuid)) return ApiResult.error(ApiCode.BAD_REQUEST,null);
		VisitPrecept vpm;
		try {
			vpm = ObjectUtils.isEmpty(visitPreceptUuid) ? null
					: (VisitPrecept)visitPreceptService.select(new VisitPrecept(visitPreceptUuid));
			if (vpm == null) {
				return ApiResult.right("随访方案为空");
			}

			Map<String, Object> visitApply = visitApplyService.selectVisitApplyListByUuid(visitApplyUuid);
			if (ObjectUtils.isEmpty(visitApply)) {
				return ApiResult.right("随访申请为空");
			}

			String customerUuid = (String) visitApply.get("customerUuid");
			String doctorUuid = (String) visitApply.get("serviceStaffUuid");
			Integer period = vpm.getPeriod();

			int success = visitPreceptService.updateVisitRecord(customerUuid,doctorUuid,visitPreceptUuid,period);
			
			return ApiResult.right(success);	
		} catch (Exception e) {
			log.error(visitApplyUuid, e);
			br = ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
		return br;
	}
	
	/**
	 * 返回错误信息调用的组装jsonMap
	 * @param code
	 * @param message
	 * @return
	 */
	public Map<Object,Object> getFailJsonMap(String code,String message){
		Map<Object,Object> jsonMap = new HashMap<Object,Object>();
		Map<Object,Object> runMap = new HashMap<Object,Object>();
		runMap.put("success", code);
		runMap.put("message", message);
		jsonMap.put("query", runMap);
		return jsonMap;
	}
	
	/**
	 * 返回成功信息调用的组装jsonMap
	 * @param code
	 * @param message
	 * @return
	 */
	public Map<Object,Object> getSucJsonMap(String code,String message){
		Map<Object,Object> jsonMap = new HashMap<Object,Object>();
		Map<Object,Object> runMap = new HashMap<Object,Object>();
		runMap.put("success", code);
		runMap.put("message", message);
		jsonMap.put("query", runMap);
		return jsonMap;
	}
	
}

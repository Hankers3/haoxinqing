package com.hxq.mobile.doctor.visit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.doctor.visit.service.VisitPreceptService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.ObjectUtils;

@Controller
public class VisitRecordController2 {
	Logger log = LoggerFactory.getLogger(VisitRecordController2.class);


	/* 随访记录信息表 */
	@Resource(name="com.hxq.mobile.doctor.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;

	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitPreceptService")
	private VisitPreceptService visitPreceptService;

	@Resource(name="com.hxq.mobile.common.service.CustomerService")
	private CustomerService customerService;

	@Resource(name="com.hxq.mobile.common.service.CustomerInfoService")
	private CustomerInfoService customerInfoService;
	
	/**
	 * 获取患者详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mobile/doctor/visit/visitrecord/2.0/getCustomerInfo", method = RequestMethod.GET)
	public @ResponseBody ApiResult getCustomerInfo(WebRequest request) {
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
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
		return ApiResult.right(InfoResultmap);
	}
	
	/**
	 * 待处理随访任务-查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/getProcessedVisitList", method = RequestMethod.GET)
	public @ResponseBody ApiResult waitProcessedVisitList(WebRequest request) {
		ApiResult br = null;
		
		Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		form.put("applyState", 0);
		String doctorUuid = (String)form.get("doctorUuid");
		if (ObjectUtils.isEmpty(doctorUuid)) return ApiResult.error(ApiCode.BAD_REQUEST,"医生没登录！");
		// 获取随访记录的集合列表
		List<Map<String, Object>> visitApplyModelList;
		List<Map<String, Object>> relist = new ArrayList<>();
		Map<String, Object> relistMap;
		try {
			visitApplyModelList = visitRecordService.selectList(form, null);
			if (visitApplyModelList != null && visitApplyModelList.size() > 0) {
				for (Map<String, Object> va : visitApplyModelList) {
					String customerUuid = (String)va.get("customerUuid");
					String applyUuid = (String)va.get("uuid");
					String applyState = (String)va.get("applyState");
					//String createTime = (String) va.get("createTime");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String createTime = sdf.format(va.get("createTime")); 
					relistMap = visitRecordService.selectWaitProcessedVisitList(doctorUuid,customerUuid,applyUuid,applyState,createTime);
					relistMap.put("visitRecordUuid", va.get("uuid"));
					relist.add(relistMap);
				}
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}

	/**
	 * 获取患者填写的随访表单（记录）
	 */
	@RequestMapping(value = "/mobile/doctor/visit/visitrecord/2.0/view/{visitRecordUuid}", method = RequestMethod.GET)
	public @ResponseBody ApiResult viewVisit(HttpServletResponse response, @PathVariable("visitRecordUuid") String visitRecordUuid) {
		
		ApiResult br = null;
		//判断随访表单id是否有值
		if (ObjectUtils.isEmpty(visitRecordUuid)) return ApiResult.error(ApiCode.BAD_REQUEST,null);
		Map<String, Object> resultMap = new HashMap<>();

		try {
			resultMap = visitRecordService.updateViewVisit(visitRecordUuid);
			return ApiResult.right(resultMap);
		} catch (Exception e) {
			log.error(visitRecordUuid, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}
	
	/**
	 * 修改关联新的随访方案
	 * 
	 * @author 
	 * @return
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/updateVisitRecord", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateVisitRecord(@RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("visitPreceptUuid") String visitPreceptUuid) {//随访方案id
			ApiResult br = null;
			if (ObjectUtils.isEmpty(doctorUuid)) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"医生id不能为空");
			}
			if (ObjectUtils.isEmpty(customerUuid)) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"患者id不能为空");
			}
			if (ObjectUtils.isEmpty(visitPreceptUuid)) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"方案id不能为空");
			}
		    //判断新随访方案是否存在
			VisitPrecept vpm;
			try {
				vpm = (VisitPrecept)visitPreceptService.select(new VisitPrecept(visitPreceptUuid));
				if(vpm==null) {
					return  ApiResult.error(ApiCode.BAD_REQUEST,"随访方案为空！");
				}
				Integer period = vpm.getPeriod();
				int success = visitPreceptService.updateVisitRecord(doctorUuid,customerUuid,visitPreceptUuid,period);
				return ApiResult.right(success);
			} catch (Exception e) {
				e.printStackTrace();
				return ApiResult.error(ApiCode.SERVER_ERROR, null);
			}
	}
}

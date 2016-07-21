package com.hxq.mobile.patient.visit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.visit.MedicalRecord;
import com.hxq.mobile.patient.visit.service.DoctorAdviceMainService;
import com.hxq.mobile.patient.visit.service.HealthGuideService;
import com.hxq.mobile.patient.visit.service.MedicalRecordService;
import com.hxq.mobile.patient.visit.service.VisitApplyService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 医嘱指导
 * 
 *
 */
@Controller
//@RequestMapping("/app/patient/health")
public class DoctorsAdviceController2 {
	Logger log = LoggerFactory.getLogger(DoctorsAdviceController2.class);
	
	@Autowired
	private FileService fileService;
	/*
	 * 注入患者病例的Service
	 */
	@Resource(name = "com.hxq.mobile.patient.visit.service.MedicalRecordService")
	private MedicalRecordService medicalRecordService;
	/*
	 * 注入健康指导的Service
	 */
	@Resource(name = "com.hxq.mobile.patient.visit.service.HealthGuideService")
	private HealthGuideService healthGuideService;
	/*
	 * 注入医生详情的
	 */
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffService")
	private ServiceStaffService servicestaffService;
	
	/*
	 *注入申请随访  
	 */
	@Resource(name="com.hxq.mobile.patient.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;
	
	/*图片service*/
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgService;
	
	@Resource(name="com.hxq.mobile.patient.visit.service.DoctorAdviceMainService")
	private DoctorAdviceMainService adviceMainService;

	/**
	 * 健康小贴士-列表及详情2.0
	 */
	@RequestMapping(value = "/app/patient/health/2.0/getHealthGuideList", method = RequestMethod.GET)
	public @ResponseBody ApiResult getHealthGuideListTwo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="doctorUuid",required = false) String doctorUuid,@RequestParam("customerUuid") String customerUuid) {

		doctorUuid = StringUtils.trimToEmpty(doctorUuid);
		customerUuid = StringUtils.trimToEmpty(customerUuid);
		try {
			// 判断customerUuid是否为空
			if (StringUtil.isEmpty(customerUuid)) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "请登录");				 
			}
			//通过患者id和applyState [随访申请状态  0未查看，1允许， 2拒绝，3失效] 得到医生id
			if (StringUtil.isEmpty(doctorUuid)) {
				doctorUuid = visitApplyService.selectDoctorFromApply(customerUuid, "1");//获得医生id
			}
			// 判断doctorUuid是否为空
			if (StringUtil.isEmpty(doctorUuid)) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"该医生不存在");
			}
			// 根据医生和患者的id获取该患者的所有的健康小贴士
			 List<Map<String, Object>> healthGuideModelList = healthGuideService.selectHealthGuideListByCustomerUuidAndDoctorUuid(doctorUuid, customerUuid);
			if (ObjectUtils.isEmpty(healthGuideModelList)) {
				return ApiResult.right();
			}
			Map<String, Object> save = null;
			List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> dam : healthGuideModelList) {
				save = new HashMap<String, Object>();
				int guideType = 1;// 1代表已过期 ；0代表未过期
				String nowTime = DateFormatHelper.getNowTimeStr();// 获取系统当前时间
//				save.put("diet", dam.get("diet"));//饮食指导
//				save.put("sports", dam.get("sports"));//运动指导	
//				save.put("rest", dam.get("rest"));//睡眠指导--》建议指导
				save.put("note", dam.get("note"));//备注
				save.put("createTime", dam.get("createTime"));//创建时间
				save.put("state", dam.get("state"));  /* 过期状态 0：未过期 1：已过期  */
				if (nowTime != null && dam.get("createTime") != null) {
					if (nowTime.compareTo((String) dam.get("createTime")) < 0) {//nowTime<createTime 说明为过期
						guideType = 0;//未过期
					}
				}
				save.put("guideType", guideType);
				relist.add(save);
			}
			return ApiResult.right(relist);
		} catch(Exception e) {
			log.error(customerUuid, e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"查询异常！");
		}
	}
	
	/**
	 * 获取病例列表2.0
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	@RequestMapping(value = "/app/patient/health/2.0/getMedicalRecordList", method = RequestMethod.GET)
	public @ResponseBody ApiResult getMedicalRecordListTwo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="doctorUuid",required = false) String doctorUuid, @RequestParam("customerUuid") String customerUuid) {

		doctorUuid = StringUtils.trimToEmpty(doctorUuid);
		customerUuid = StringUtils.trimToEmpty(customerUuid);
		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
//			return BoolResult.error("请登录");
			return ApiResult.error(ApiCode.BAD_REQUEST,"请登录");
		}

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			doctorUuid = visitApplyService.selectDoctorFromApply(customerUuid, "1");//获得医生id
		}
		if (StringUtil.isEmpty(doctorUuid)) {
//			return BoolResult.error("该医生不存在");
			return ApiResult.error(ApiCode.BAD_REQUEST,"该医生不存在");
		}
		
		 ArrayList<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		// 根据医生和患者的id获取该患者的所有的病例
		 List<Map<String, Object>> list = medicalRecordService.selectListByCustomerUuidAndDoctorUuid(customerUuid,doctorUuid);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> mrm : list) {
				if (mrm != null) {
					Map<String, Object> save = new HashMap<String, Object>();
					save.put("UUID", mrm.get("UUID"));//病例uuid
					save.put("caseCategoryType", mrm.get("caseCategoryType"));// 病例类型 0住院病例 1门诊病例
					save.put("createTime", mrm.get("createTime"));
			   		relist.add(save);
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relist", relist);
//		return BoolResult.right(JsonUtil.toJSONString(map, false, false));
		return ApiResult.right(JSON.toJSON(map));
	}

	/**
	 * 获取病例详情2.0
	 */
	@RequestMapping(value = "/app/patient/health/2.0/getMedicalRecordDetail", method = RequestMethod.GET)
	public @ResponseBody ApiResult getMedicalRecordDetailTwo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("UUID") String UUID) {

		MedicalRecord mrm = null;
		Image4App[] imgUrls = null;
		ServiceStaff doctor = null;
		Map<String, Object> mapReturn = new HashMap<String, Object>();

		try {// 根据病例id获取病例的model
			mrm = (MedicalRecord) medicalRecordService.select(new MedicalRecord(UUID));
			if(mrm == null) return ApiResult.error(ApiCode.BAD_REQUEST, "没有此病例！");

			mapReturn.put("hospitalUuid", mrm.getHospitalUuid());// 就诊医院
			mapReturn.put("hospitalName", mrm.getHospitalName());// 就诊医生
			mapReturn.put("doctorUuid", mrm.getDoctorUuid());
			mapReturn.put("createTime", mrm.getCreateTime());// 创建时间

			doctor = (ServiceStaff) servicestaffService.select(new ServiceStaff(mrm.getDoctorUuid()));
			if(doctor != null) mapReturn.put("doctorName", doctor.getRealName());

			//* 病例类型 0住院号 1门诊号 2床位号 3病案号 4其他 */
			if(!ObjectUtils.isEmpty(mrm.getCaseCategoryType())) {
				mapReturn.put("caseCategoryType", mrm.getCaseCategoryType());// 就诊类型
				if(mrm.getCaseCategoryType().equals("1")) {
					mapReturn.put("seeDoctorTime", mrm.getSeeDoctorTime());// 就诊时间
				} else if(mrm.getCaseCategoryType().equals("0")) {
					mapReturn.put("startTime", mrm.getStartTime());// 住院时间
					mapReturn.put("endTime", mrm.getEndTime());// 出院时间
				}
			}

			if(ObjectUtils.isEmpty(mrm.getImage1())!=true){
				imgUrls = CompatibleTools.getImages(imgService, fileService, mrm.getImage1());
				if(!ObjectUtils.isEmpty(imgUrls)){ 
					mapReturn.put("image1",  imgUrls[0]);
				}
			}
			if(ObjectUtils.isEmpty(mrm.getImage2())!=true){
				imgUrls = CompatibleTools.getImages(imgService, fileService, mrm.getImage2());
				if(!ObjectUtils.isEmpty(imgUrls)) mapReturn.put("image2",  imgUrls[0]);
			}
			if(ObjectUtils.isEmpty(mrm.getImage3())!=true){
				imgUrls = CompatibleTools.getImages(imgService, fileService, mrm.getImage3());
				if(!ObjectUtils.isEmpty(imgUrls)) mapReturn.put("image3",  imgUrls[0]);
			}
			if(ObjectUtils.isEmpty(mrm.getImage4())!=true){
				imgUrls = CompatibleTools.getImages(imgService, fileService, mrm.getImage4());
				if(!ObjectUtils.isEmpty(imgUrls)) mapReturn.put("image4",  imgUrls[0]);
			}
			if(ObjectUtils.isEmpty(mrm.getImage5())!=true){
				imgUrls = CompatibleTools.getImages(imgService, fileService, mrm.getImage5());
				if(!ObjectUtils.isEmpty(imgUrls)) mapReturn.put("image5",  imgUrls[0]);
			}
			return ApiResult.right(mapReturn);
		} catch(Exception e) {
			log.error(UUID, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}
	
	/****************************************重要医嘱详情**********************************************************/	
	/**
	 * 重要医嘱详情2.0版
	 */
	@RequestMapping(value = "/mobile/patient/visit/2.0/getDoctorsAdviceList", method=RequestMethod.GET)
	public @ResponseBody ApiResult getDoctorAdviceListTwo(WebRequest request) {
    
		Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		String customerUuid = RequestUtil.getFormValue(form, "customerUuid");
		if(ObjectUtils.isEmpty(customerUuid)) return null;
		List<Map<String, Object>> lstMain = null;
		try {
					
			String serviceStaffUuid = RequestUtil.getFormValue(form, "serviceStaffUuid");
			if(ObjectUtils.isEmpty(serviceStaffUuid)){
				Map<String, Object> map = adviceMainService.selectVisitApplyByCustomer(customerUuid);
				serviceStaffUuid = StringUtils.trimToEmpty(map.get("serviceStaffUuid"));
				form.put("serviceStaffUuid", new String[]{serviceStaffUuid});
			}
	
			lstMain = adviceMainService.selectList(form, false);
			if(lstMain==null) return null;
			List<Map<String, Object>> lstChild = null;
			for(Map<String, Object> parent : lstMain) {
				lstChild = adviceMainService.selectChildList(StringUtils.trimToEmpty(parent.get("uuid")));
				if(!ObjectUtils.isEmpty(lstChild)) {
					parent.put("child", lstChild);
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		 ApiResult ar = null;
		 ar = ApiResult.right(lstMain);
		 return ar;
		//return JsonUtil.ResponseJson(JsonUtil.toJSONString(lstMain, true, true));
	}
	
	
}

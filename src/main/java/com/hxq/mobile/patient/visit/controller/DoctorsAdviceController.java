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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.visitprecept.healthguide.service.HealthGuideService;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.service.MedicalRecordService;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordModel;
import com.alibaba.fastjson.JSON;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.patient.visit.service.VisitApplyService;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;


/**
 * 健康小提示  病例列表
 * 
 *
 */
@Controller
@RequestMapping("/app/patient/health")
public class DoctorsAdviceController extends AppBaseController{
	Logger log = LoggerFactory.getLogger(DoctorsAdviceController.class);
	
	public DoctorsAdviceController( ) {
		super("", "", DoctorsAdviceController.class);
	
	}
	/*
	 * 注入患者病例的Service
	 */
	@Autowired
	private MedicalRecordService medicalRecordService;
	/*
	 * 注入健康指导的Service
	 */
	@Autowired
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
	
	/**********************************************健康小贴士-详情***********************************************************************/
	/**
	 * 健康小贴士-列表及详情
	 * 获取该医生的健康小贴士
	 * /app/patient/health/1.0/getHealthGuideList
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "1.0/getHealthGuideList", method = RequestMethod.GET)
	public ResponseEntity<String> getHealthGuideList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="doctorUuid",required = false) String doctorUuid,@RequestParam("customerUuid") String customerUuid) {

		doctorUuid = StringUtils.trimToEmpty(doctorUuid);
		customerUuid = StringUtils.trimToEmpty(customerUuid);

		try {
			// 判断customerUuid是否为空
			if (StringUtil.isEmpty(customerUuid)) {
				return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("请登录")));
			}
			if (StringUtil.isEmpty(doctorUuid)) {
				doctorUuid = visitApplyService.selectDoctorFromApply(customerUuid, "1");//获得医生id
			}
			// 判断doctorUuid是否为空
			if (StringUtil.isEmpty(doctorUuid)) {
				return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("该医生不存在")));
			}

			// 根据医生和患者的id获取该患者的所有的健康小贴士
			List<HealthGuideModel> healthGuideModelList = healthGuideService
					.getHealthGuideListByCustomerUuidAndDoctorUuid(doctorUuid, customerUuid);
			if (ObjectUtils.isEmpty(healthGuideModelList)) {
				return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.right()));
			}
	
			Map<String, Object> save = null;
			List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
			for (HealthGuideModel dam : healthGuideModelList) {
				save = new HashMap<String, Object>();
				int guideType = 1;// 1代表已过期 ；0代表未过期
				String nowTime = DateFormatHelper.getNowTimeStr();// 获取系统当前时间
				save.put("diet", dam.getDiet());//饮食指导
				save.put("sports", dam.getSports());//运动指导
				save.put("rest", dam.getRest());//睡眠指导
				save.put("createTime", dam.getCreateTime());//创建时间
				save.put("note", dam.getNote());//其他
				save.put("state", dam.getState());  /* 过期状态 0：未过期 1：已过期  */
				if (nowTime != null && dam.getCreateTime() != null) {
					if (nowTime.compareTo(dam.getCreateTime()) < 0) {
						guideType = 0;
					}
				}
				save.put("guideType", guideType);
				relist.add(save);
			}
			return JsonUtil.ResponseJson(JSON.toJSONString(relist));
		} catch(Exception e) {
			log.error(customerUuid, e);
			return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("查询异常！")));
		}
	}
	/*********************************************病例列表***************************************************/
	/**
	 * 
	 * 获取病例列表
	 * /app/patient/health/1.0/getMedicalRecordList
	 */
	@RequestMapping(value = "1.0/getMedicalRecordList", method = RequestMethod.GET)
	public ResponseEntity<String> getMedicalRecordList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="doctorUuid",required = false) String doctorUuid, @RequestParam("customerUuid") String customerUuid) {
		BoolResult br = null;
		
		doctorUuid = StringUtils.trimToEmpty(doctorUuid);
		customerUuid = StringUtils.trimToEmpty(customerUuid);
		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
//			return BoolResult.error("请登录");
			return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("请登录")));
		}

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			doctorUuid = visitApplyService.selectDoctorFromApply(customerUuid, "1");//获得医生id
		}
		if (StringUtil.isEmpty(doctorUuid)) {
//			return BoolResult.error("该医生不存在");
			return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("该医生不存在")));
		}
		
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		// 根据医生和患者的id获取该患者的所有的病例
		List<MedicalRecordModel> list = medicalRecordService.getMedicalRecordListByCustomerUuidAndDoctorUuid(customerUuid,
				doctorUuid);
		if (list != null && list.size() > 0) {
			for (MedicalRecordModel mrm : list) {
				if (mrm != null) {
					Map<String, Object> save = new HashMap<String, Object>();
					save.put("UUID", mrm.getUuid());//病例uuid
					// 病例类型 0住院病例 1门诊病例
					save.put("caseCategoryType", mrm.getCaseCategoryType());
					save.put("createTime", mrm.getCreateTime());
			   		relist.add(save);
				}
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relist", relist);
//		return BoolResult.right(JsonUtil.toJSONString(map, false, false));
		br = BoolResult.right(JSON.toJSON(map));
		return JsonUtil.ResponseJson(JSON.toJSONString(br));
	}
	/***************************************获得病例详情*********************************************/
	/**
	 * 
	 * 获取病例详情
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "1.0/getMedicalRecordDetail", method = RequestMethod.GET)
	public ResponseEntity<String> getMedicalRecordDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("UUID") String UUID) {

		BoolResult boolResult = null;
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		// 根据病例id获取病例的model
		MedicalRecordModel mrm = medicalRecordService.getByUuid(UUID);
		if (mrm != null) {
			Map<String, Object> save = new HashMap<String, Object>();
			String caseType = mrm.getCaseCategoryType();///* 病例类型 0住院号 1门诊号 2床位号 3病案号 4其他 */
			// 就诊医院
			save.put("hospitalUuid", mrm.getHospitalUuid());
			save.put("hospitalName", mrm.getHospitalName());
			// 就诊医生
			save.put("doctorUuid", mrm.getDoctorUuid());
			// 创建时间
			save.put("createTime", mrm.getCreateTime());
			// 病情记录			
			// 图片1
			save.put("img1Url", mrm.getImg1Url());
			// 图片2
			save.put("img2Url", mrm.getImg2Url());
			// 图片3
			save.put("img3Url", mrm.getImg3Url());
			// 图片4
			save.put("img4Url", mrm.getImg4Url());
			// 图片5
			save.put("img5Url", mrm.getImg5Url());

			try {
				ServiceStaff mapDoctor = (ServiceStaff) servicestaffService.select(new ServiceStaff(mrm.getDoctorUuid()));
				if(mapDoctor != null) {
					save.put("doctorName", mapDoctor.getRealName());
				}
			} catch (Exception e) {
				log.error("", e);
			}

			save.put("image1", mrm.getImage1());// 图片1
			save.put("image2", mrm.getImage2());// 图片2
			save.put("image3", mrm.getImage3());// 图片3
			save.put("image4", mrm.getImage4());// 图片4
			save.put("image5", mrm.getImage5());// 图片5

			if (!StringUtil.isEmpty(caseType) && caseType.equals("1")) {
				// 就诊类型
				save.put("caseCategoryType", "1");
				// 就诊时间
				save.put("seeDoctorTime", mrm.getSeeDoctorTime());
			}
			if (!StringUtil.isEmpty(caseType) && caseType.equals("0")) {
				// 就诊类型
				save.put("caseCategoryType", "0");
				// 住院时间
				save.put("startTime", mrm.getStartTime());
				// 出院时间
				save.put("endTime", mrm.getEndTime());
			}
 
			relist.add(save);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relist", relist);
//		return BoolResult.right(JsonUtil.toJSONString(map, false, false));
		boolResult = BoolResult.right(JSON.toJSON(map));
		return JsonUtil.ResponseJson(JSON.toJSONString(boolResult));
	}
}

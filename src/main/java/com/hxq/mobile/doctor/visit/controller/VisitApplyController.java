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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.service.CustomerDoctorReleService;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.service.VisitPreceptPushService;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushModel;
import com.hxq.mobile.doctor.visit.service.VisitApplyService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.doctor.visit.controller.VisitApplyController")
public class VisitApplyController {
	Logger log = LoggerFactory.getLogger(VisitApplyController.class);

	/* 消息service */
	@Autowired
	private InnerMessageService innerMessageService;
	
	/* 患者的详情的service */
	@Autowired
	private CustomerInfoService customerInfoService;

	/* 随访的service */
	@Autowired
	private VisitPreceptService visitPreceptService;

	/* 注入患者医生关联关系表 */
	@Autowired
	private CustomerDoctorReleService customerDoctorReleService;

	/** 积分的service */
	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;

	@Autowired
	private VisitPreceptPushService visitPreceptPushService;

	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;

	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	/* 随访记录信息表 */
	@Resource(name="com.hxq.mobile.doctor.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;

	/**
	 * 获取随访申请列表
	 * 
	 * @param response
	 *            response
	 * @param doctorid
	 *            医生id
	 * @return null
	 */
	@RequestMapping(value="/app/pub/doctor/1.0/getVisitApplyList", method=RequestMethod.GET)
	public String getVisitApplyList(WebRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断用户是否登陆
		if (StringUtil.isEmpty(doctorid)) {
			HttpServletUtils.outJson(response,CompatibleTools.getFailJsonMap(SysConst.FAIL,"医生未登录"));
			return null;
		}

		// 获取随访记录的集合列表
		List<Map<String, Object>> lstVisitApply = null;
		try {
			lstVisitApply = visitApplyService.selectList(
					RequestUtil.cleanParameterMap(request.getParameterMap()), false);
		} catch (Exception e) {
			log.error(doctorid, e);
			HttpServletUtils.outJson(response,CompatibleTools.getFailJsonMap(SysConst.FAIL,"查询随访申请失败"));
			return null;
		}

		List<Map<String, Object>> relist = new ArrayList<>();
		if (lstVisitApply != null && lstVisitApply.size() > 0) {
			for (Map<String, Object> va : lstVisitApply) {
				// 组装返回到客户端的患者信息
				Map<String, Object> visitApplyMap = new HashMap<>();
				// 医生、患者和随访记录的uuid
				String customerUuid = (String) va.get("customerUuid");
				String doctorUuid = (String) va.get("serviceStaffUuid");
				String applyUuid = (String) va.get("uuid");
				CustomerInfoModel cim;
				if (!StringUtil.isEmpty(customerUuid)) {
					cim = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
				/*	if (cim != null && !StringUtil.isEmpty(doctorUuid)) {
						visitApplyMap.put("realName", cim.getRealName());
						visitApplyMap.put("sex", cim.getSex());
						visitApplyMap.put("age", cim.getAge());
						String createTime = (String) va.get("createTime");
						if (!StringUtil.isEmpty(createTime) && createTime.length() > 10) {
							visitApplyMap.put("createTime",((String)va.get("createTime")).substring(0,10));
						} else {
							visitApplyMap.put("createTime", cim.getOpeTime().substring(0, 10));
						}
						visitApplyMap.put("customerUuid", customerUuid);
						visitApplyMap.put("doctorUuid", doctorUuid);
						visitApplyMap.put("applyUuid", applyUuid);
						visitApplyMap.put("imgUrl", cim.getImgUrl());
						visitApplyMap.put("illnessDescription", cim.getIllnessDescription());
						relist.add(visitApplyMap);
					}*/
					if (cim != null && !StringUtil.isEmpty(doctorUuid)) {
						visitApplyMap.put("realName", cim.getRealName());
						visitApplyMap.put("sex", cim.getSex());
						visitApplyMap.put("age", cim.getAge());
						String createTime = (String) va.get("createTime");
						if (!StringUtil.isEmpty(createTime) && createTime.length() > 10) {
							visitApplyMap.put("createTime",((String)va.get("createTime")).substring(0,10));
						} else {
							visitApplyMap.put("createTime", cim.getOpeTime().substring(0, 10));
						}
						visitApplyMap.put("customerUuid", customerUuid);
						visitApplyMap.put("doctorUuid", doctorUuid);
						visitApplyMap.put("applyUuid", applyUuid);
						visitApplyMap.put("imgUrl", cim.getImgUrl());
						visitApplyMap.put("illnessDescription", cim.getIllnessDescription());
					}
					relist.add(visitApplyMap);
				}
			}
		}

		// 设置返回信息
		Map<Object,Object> jsonMap=CompatibleTools.getSucJsonMap(SysConst.SUCCESS,SysConst.SUCCESSMESSAGE);
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

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
	@RequestMapping(value = "/app/pub/doctor/1.0/getApplyDetail", method = RequestMethod.GET)
	public String getApplyDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("applyUuid") String applyUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断用户是否存在
		if (StringUtil.isEmpty(applyUuid)) {
			HttpServletUtils.outJson(response,CompatibleTools.getFailJsonMap(SysConst.FAIL,"随访申请ID不能为空"));
			return null;
		}

		Map<String, Object> customerInfoMap = new HashMap<>();
		try {
			// 获取随访申请详情
			VisitApply ApplyModel = (VisitApply) visitApplyService.select(new VisitApply(applyUuid));
			if (ApplyModel==null) {
				HttpServletUtils.outJson(response,CompatibleTools.getFailJsonMap(SysConst.FAIL,"取随访申请不存在"));
				return null;
			}
				
			Map<String, Object> customerModel = visitApplyService.selectCustomerModel(ApplyModel.getCustomerUuid());
			if(ObjectUtils.isEmpty(customerModel)==false) {
				customerInfoMap.put("mobile", customerModel.get("mobile"));// 手机号
				customerInfoMap.put("email", customerModel.get("email"));// 电子邮箱
			}
	
			CustomerInfoModel customerInfo = customerInfoService.getCustomerInfoModelByCustomerUuid(
					ApplyModel.getCustomerUuid());
			if (customerInfo != null) {
				customerInfoMap.put("nickname", customerInfo.getNickName());// 昵称
				customerInfoMap.put("realName", customerInfo.getRealName());// 真实姓名
				customerInfoMap.put("sex", customerInfo.getSex());// 1是男 ，2是女
				customerInfoMap.put("certCode", customerInfo.getCertCode());// 出生年月
				customerInfoMap.put("marryState", customerInfo.getMarryState());// 婚姻状况
				customerInfoMap.put("industry", customerInfo.getIndustry());// 职业
				customerInfoMap.put("address", customerInfo.getAddress());// 住址
				customerInfoMap.put("diseaseTime", customerInfo.getDiseaseTime());// 病程
				customerInfoMap.put("firstDiagnosis", customerInfo.getFirstDiagnosis());// 首次就诊时间
				customerInfoMap.put("ifStart", customerInfo.getIfStart());// 是否首发
				customerInfoMap.put("seizureTimes",	customerInfo.getSeizureTimes());// 复发次数
				customerInfoMap.put("height", customerInfo.getHeight());// 身高
				customerInfoMap.put("weight", customerInfo.getWeight());// 体重
				customerInfoMap.put("nearlyDrugs", customerInfo.getNearlyDrugs());// 近3个月使用药物
				customerInfoMap.put("illnessDescription", customerInfo.getIllnessDescription());// 病情描述
				customerInfoMap.put("age", customerInfo.getAge());// 年龄
				customerInfoMap.put("diagnose",	customerInfo.getIllnessDescription());// 诊断描述
				customerInfoMap.put("applyUuid", applyUuid);// 申请uuid
				customerInfoMap.put("customerUuid",	ApplyModel.getCustomerUuid());// 患者uuid
				customerInfoMap.put("doctorUuid", ApplyModel.getServiceStaffUuid());// 医生uuid
				customerInfoMap.put("symptoms", ApplyModel.getSymptoms());// 症状表现和既往治疗经过symptoms
			}
	
			// 获取图片信息
			String[] imgs = imgUploadService.selectImagesByTableName("visit_apply", applyUuid);
			if (ObjectUtils.isEmpty(imgs) == false) customerInfoMap.put("imgs", imgs);
		} catch (Exception e) {
			log.error(applyUuid, e);
			HttpServletUtils.outJson(response,CompatibleTools.getFailJsonMap(SysConst.FAIL,"获取随访申请失败"));
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS,SysConst.SUCCESSMESSAGE);
		List<Map<String, Object>> relist = new ArrayList<>();
		relist.add(customerInfoMap);
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	/**
	 * 同意并关联随访方案
	 * 
	 * @author xp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/pub/doctor/1.0/addVisitRecord", method = RequestMethod.GET)
	public String agreeVisitApply(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("visitUuid") String visitApplyUuid,
			@RequestParam("visitPreceptUuid") String visitPreceptUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		VisitPreceptModel vpm = ObjectUtils.isEmpty(visitPreceptUuid) ? null
				: visitPreceptService.getByUuid(visitPreceptUuid);
		if (vpm == null) {
			HttpServletUtils.outJson(response,CompatibleTools.getFailJsonMap(SysConst.FAIL,"随访方案为空"));
			return null;
		}

		Map<String, Object> visitApply = visitApplyService.selectVisitApplyListByUuid(visitApplyUuid);
		if (ObjectUtils.isEmpty(visitApply)) {
			HttpServletUtils.outJson(response, CompatibleTools.getFailJsonMap(SysConst.FAIL,"随访申请为空"));
			return null;
		}

		String customerUuid = (String) visitApply.get("customerUuid");
		String doctorUuid = (String) visitApply.get("serviceStaffUuid");
		String doctorName = (String) visitApply.get("realName");

		CustomerDoctorReleModel cm = customerDoctorReleService.getByCustomerUuidAndDoctorUuid(
				customerUuid, doctorUuid);
		if (cm == null) {
			cm = new CustomerDoctorReleModel();
			cm.setCustomerUuid(customerUuid);
			cm.setDoctorUuid(doctorUuid);
			cm.setGroupUuid("");
			customerDoctorReleService.create(cm);
		}

		try {
			//同意并关联随访方案
			VisitApply va = new VisitApply(visitApplyUuid);
			va.setApplyState("1");
			va.setCustomerUuid(customerUuid);
			va.setVisitPreceptUuid(visitPreceptUuid);
			visitApplyService.update(va);

			//生成首次随访记录表单
			VisitRecord vr = new VisitRecord();
			vr.setCustomerUuid(customerUuid);
			vr.setPreceptUuid(visitPreceptUuid);
			vr.setServiceStaffUuid(doctorUuid);
			visitRecordService.insert(vr);

			/******* 16/02/01 同时添加 随访周期消息推送表 ************/
			VisitPreceptPushModel vppm = null;
			// 根据随访id得倒随访推送表。
			vppm = visitPreceptPushService.getByVisitPreceptUuid(doctorUuid, customerUuid);
			if (vppm == null) {// 如果存在就更新。不存在就添加
				vppm = new VisitPreceptPushModel();
				vppm.setCreateTime(DateFormatHelper.getNowTimeStr());// 创建时间
				vppm.setDoctorUuid(doctorUuid);// 医生id
				vppm.setCustomerUuid(customerUuid);// 患者id
				vppm.setPeriod(vpm.getPeriod());// 方案周期
				vppm.setPushTime(DateFormatHelper.getNowTimeStr());// 推送时间
				vppm.setPushTimes(0);// 推送次数
				vppm.setVisitPreceptUuid(visitPreceptUuid);// 方案id
				visitPreceptPushService.create(vppm);
			}
			/************************ 添加结束 *************************/
		
			// 添加随访患者成功，医生积分+20
			if (!StringUtil.isEmpty(doctorUuid)) {
				vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add",
						20, IntegralType.ADD_VISITRECORD.getValue(),
						VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC,
						"添加随访患者成功，积分加20", "");
			}
			// 添加随访患者成功，患者积分+5
			if (!StringUtil.isEmpty(customerUuid)) {
				vipclubIntegralLogService.saveVipIntegralLog(customerUuid,
						"add", 5, IntegralType.ADD_VISITRECORD.getValue(),
						VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS,
						"添加随访成功，积分加5", "");
			}

			// 保存消息到数据库，并推送到手机终端
			if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
				String content = doctorName	+ MessageHelper.getMessage("addVisitRecord.showmessage.newAdd");
				innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid,
						content, InnerMessageModel.ACCOUNT_TYPE_STORE,
						InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
						PushConst.push_client_customer, "doctorUuid",
						doctorUuid,
						InnerMessageTypeEnum.VISITDETAIL.getValue());
			}
		} catch (Exception e) {
			log.error(visitApplyUuid, e);
			HttpServletUtils.outJson(response, CompatibleTools.getFailJsonMap(SysConst.FAIL,"添加随访表单失败"));
			return null;
		}

		// 设置返回信息
		HttpServletUtils.outJson(response,CompatibleTools.getSucJsonMap(SysConst.SUCCESS,SysConst.SUCCESSMESSAGE));
		return null;
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
	@RequestMapping(value = "/app/public/refuseapply/1.0/refuseVivistApply", method = RequestMethod.POST)
	public String refuseVivistApply(HttpServletResponse response,
			@RequestParam("applyUuid") String applyUuid,
			@RequestParam("refuseReason") String refuseReason) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 判断用户是否填写拒绝理由
		if (StringUtil.isEmpty(refuseReason) && StringUtil.isEmpty(applyUuid)) {
			HttpServletUtils.outJson(response,CompatibleTools.getFailJsonMap(SysConst.FAIL,"请填写拒绝理由"));
			return null;
		}

		try {
			//拒绝患者的申请
			VisitApply va = new VisitApply(applyUuid);
			va.setApplyState("2");
			va.setRefuseReason(refuseReason);
			visitApplyService.update(va);

			va = (VisitApply) visitApplyService.select(new VisitApply(applyUuid));

			//发送消息
			if (!StringUtil.isEmpty(va.getServiceStaffUuid())
					&& !StringUtil.isEmpty(va.getCustomerUuid())) {
				String content = MessageHelper
						.getMessage("refuseVivistApply.showmessage.newAdd")
						+ refuseReason;
				innerMessageService.saveInnerMessageAndPush(
						va.getServiceStaffUuid(), va.getCustomerUuid(),
						content, InnerMessageModel.ACCOUNT_TYPE_STORE,
						InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
						PushConst.push_client_customer, "doctorUuid",
						va.getServiceStaffUuid(),
						InnerMessageTypeEnum.VISITDETAIL.getValue());
			}
		} catch (Exception e) {
			log.error(applyUuid, e);
		}

		HttpServletUtils.outJson(response,CompatibleTools.getSucJsonMap(SysConst.SUCCESS,SysConst.SUCCESSMESSAGE));
		return null;
	}
}

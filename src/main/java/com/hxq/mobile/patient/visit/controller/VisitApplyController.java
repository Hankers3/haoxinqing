package com.hxq.mobile.patient.visit.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.patient.visit.service.VisitApplyService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 患者-申请医生随访
 */
@Controller("com.hxq.mobile.patient.visit.controller.VisitApplyController")
@RequestMapping("/mobile/patient/apply")
public class VisitApplyController {
	Logger log = LoggerFactory.getLogger(VisitApplyController.class);

	/*
	 * 患者基础信息service
	 */
	@Autowired
	private CustomerInfoService customerInfoService;
	
	@Resource(name = "com.hxq.mobile.patient.visit.service.VisitApplyService")
	private VisitApplyService service;

	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	/**
	 * 申请医生随访
	 *@param customerUuid customerUuid
	 *@param doctorUuid doctorUuid
	 *@param symptoms symptoms
	 *@param imgs  imgs
	 *
	 * /mobile/patient/apply/1.0/applyVisit
	 */
	@RequestMapping(value = "1.0/applyVisit", method = RequestMethod.POST)
	public ResponseEntity<String> getApplyVisit(
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("doctorUuid") String doctorUuid,
			@RequestParam(value = "symptoms", required = false) String symptoms,
			@RequestParam(value = "imgs", required = false) String imgs) {

		BoolResult br = null;
		//查看个人信息是否添加完成
		//根据会员编号获取会员基础信息
		CustomerInfoModel cim = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		if(cim == null){
			br =  BoolResult.error("个人信息未填写完成");
			return JsonUtil.ResponseJson(JsonUtil.toJSONString(br));
		}

		try {
			VisitApply bean = new VisitApply(IdentityHelper.uuid2());//随访申请表
			bean.setCustomerUuid(customerUuid);//患者id
			bean.setServiceStaffUuid(doctorUuid);//医生id
			bean.setCreateTime(DateFormatHelper.getNowTimeStr());//创建时间
			bean.setOpeTime(DateFormatHelper.getNowTimeStr());//操作时间
			bean.setSymptoms(StringUtils.trimToEmpty(symptoms));
			service.insert(bean);

			//绑定申请记录与申请图片
			if(!ObjectUtils.isEmpty(imgs)){
				imgUploadService.updateForBindingBusiness("visit_apply", bean.getId(), imgs.split(","));
			}

			br = BoolResult.right("提交完成！");
		} catch(Exception e) {
			log.error(StringUtils.trimToEmpty(customerUuid), e);	
			br = BoolResult.error("提交出错，请稍后再试！");
		}
		return JsonUtil.ResponseJson(JsonUtil.toJSONString(br));
	}
}

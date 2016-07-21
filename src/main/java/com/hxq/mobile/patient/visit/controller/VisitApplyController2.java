package com.hxq.mobile.patient.visit.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerDoctorRele;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.patient.CommonLogic;
import com.hxq.mobile.patient.visit.service.CustomerDoctorReleService;
import com.hxq.mobile.patient.visit.service.VisitApplyService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 患者-申请医生随访
 */
@Controller("com.hxq.mobile.patient.visit.controller.VisitApplyController2")
@RequestMapping("/mobile/patient/apply")
public class VisitApplyController2 {
	Logger log = LoggerFactory.getLogger(VisitApplyController2.class);

	@Resource(name = "com.hxq.mobile.common.service.CustomerService")
	private CustomerService customerService;

	@Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
	private CustomerInfoService customerInfoService;

	@Resource(name = "com.hxq.mobile.patient.visit.service.VisitApplyService")
	private VisitApplyService service;

	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	@Resource(name = "com.hxq.mobile.patient.visit.service.CustomerDoctorReleService")
	private CustomerDoctorReleService customerDoctorReleService;
	/**
	 * 申请医生随访
	 *@param customerUuid customerUuid
	 *@param doctorUuid doctorUuid
	 *@param symptoms symptoms
	 *@param imgs  imgs
	 */
	@RequestMapping(value = "2.0/applyVisit", method = RequestMethod.POST)
	public @ResponseBody ApiResult getApplyVisit2(
		   @RequestParam("customerUuid") String customerUuid, 
		   @RequestParam("doctorUuid") String doctorUuid,
		   @RequestParam(value = "symptoms", required = false) String symptoms,
		   @RequestParam(value = "imgs", required = false) String imgs) {

		try {
			Customer customer = (Customer) customerService.select(new Customer(customerUuid));
			//查看个人信息是否添加完成
			CustomerInfo cim = customerInfoService.selectByCustomerUuid(customerUuid);
			if(CommonLogic.isComplated(customer, cim)!=true) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"个人信息未填写完成！");
			}

			VisitApply bean = new VisitApply(IdentityHelper.uuid2());//随访申请表
			bean.setCustomerUuid(customerUuid);//患者id
			bean.setServiceStaffUuid(doctorUuid);//医生id
			bean.setCreateTime(DateFormatHelper.getNowTimeStr());//创建时间
			bean.setOpeTime(DateFormatHelper.getNowTimeStr());//操作时间
			bean.setSymptoms(StringUtils.trimToEmpty(symptoms));
			bean.setImages(imgs);
			/*
			 * 判断 患者在申请随访医生后，医生又申请患者的同时，系统自动将两者建立随访关系  查看“患者与医生关联表”：customer_doctor_rele
			 */
			CustomerDoctorRele rele = customerDoctorReleService.selectCustomerDoctorRele(customerUuid, doctorUuid);
			if(!ObjectUtils.isEmpty(rele)){
				bean.setApplyState("1");//直接通过
			} else {
				bean.setApplyState("0");//待医生查看
			}
			int intReturn = service.insert(bean);
			return intReturn < 1 ? ApiResult.error(ApiCode.BAD_REQUEST,"已存在有效的随访申请！") : ApiResult.right();
		} catch(Exception e) {
			log.error(customerUuid, e);	
			return ApiResult.error(ApiCode.SERVER_ERROR,"提交出错，请稍后再试！");
		}
	}
}

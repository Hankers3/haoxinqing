package com.hxq.mobile.patient.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;

import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.entity.common.Concern;
import com.hxq.mobile.entity.message.InnerMessage;
import com.hxq.mobile.message.InnerMessage.service.InnerMessageService;
import com.hxq.mobile.patient.common.service.ConcernService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.patient.common.controller.AttentionDoctorsController")
public class AttentionDoctorsController {
	Logger log = LoggerFactory.getLogger(AttentionDoctorsController.class);
	
	/*患者关注医生信息表*/
	@Resource(name = "com.hxq.mobile.patient.common.service.ConcernService")
	private ConcernService concernService;
	/*患者信息service*/
	@Resource(name = "com.hxq.mobile.common.service.CustomerService")
	private CustomerService customerService; 
	
	/* */
	
	@Resource(name = "com.hxq.mobile.message.InnerMessage.service.InnerMessageService")
	private InnerMessageService innerMessageService;
	/**
	 * 患者端添加关注的医生 1.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "app/customer/patient/1.0/addAttentionDoctors", method = RequestMethod.POST)
	public @ResponseBody ApiResult addAttentionDoctors(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("doctorUuid") String doctorUuid) {
		
		if(ObjectUtils.isEmpty(doctorUuid)||ObjectUtils.isEmpty(customerUuid)){
			return ApiResult.error(ApiCode.BAD_REQUEST, "医生id或者患者id为空");
		}
		try {
			//查询信息，根据医生id和患者id
			InnerMessage innerMessage = innerMessageService.selectInnMageList(doctorUuid, customerUuid, "0");
			// 通过医生id和患者id查询出患者关注医生
			Concern concern = concernService.selectByCustomerAndDoctorUuid(customerUuid, doctorUuid);
			String uuid = IdentityHelper.uuid2();
			if (concern == null) {
				concern = new Concern(uuid);
				concern.setCustomerUuid(customerUuid);// 患者id
				concern.setDoctorUuid(doctorUuid);// 医生id
				concern.setCreateTime(DateFormatHelper.getNowTimeStr());// 关注时间
				concern.setState("1");// 关注状态？ 1是关注2是取消关注
				concernService.insert(concern);
			}
				//插入信息
				innerMessage = new InnerMessage(uuid);
				innerMessage.setSendUser(customerUuid);//发件人
				innerMessage.setReceiveUser(doctorUuid);//收件人
				innerMessage.setSendTime(DateFormatHelper.getNowTimeStr());//发送时间
				innerMessage.setMessageType("0");//消息类型
				innerMessage.setReadStatus("0");//未读
				innerMessage.setSendType("2");//发信人类别 0:平台 1:医生 2：患者
//				innerMessage.setTitle("");//消息标题
//				innerMessage.setContent("");//消息内容
				innerMessageService.insert(innerMessage);
			
			 /*String customerName = "";
			customerName = customerService.selectCustomerName(customerUuid);
			 szr 推送消息 扫医生二维码，患者关注医生成功
			String content = customerName + MessageHelper.getMessage("message.customerAttentionDoctor");
			// 关注我的人专用消息推送接口
			innerMessageService.saveInnerMessageAndPushForConcernDoctor(customerUuid, doctorUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_CUSTOMER, InnerMessageModel.ACCOUNT_TYPE_STORE,
					PushConst.push_client_service, "customerUuid", customerUuid, "");*/
	
			return ApiResult.right();
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"请稍后再关注！");
		}
	}
	
	/**
	 * 患者端取消关注的医生
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "app/customer/patient/1.0/cancleAttentionDoctors", method = RequestMethod.POST)
	public  @ResponseBody ApiResult cancleAttentionDoctors(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("doctorUuid") String doctorUuid) {
		
		try {
			// 通过医生id和患者id查询出患者关注医生model
			Concern concernModel = concernService.selectByCustomerAndDoctorUuid(customerUuid, doctorUuid);
			String uuid = "";
			if (concernModel != null) {
				uuid = concernModel.getUuid();
				concernService.delete(concernModel);
			}
			//根据uuid删除innermessage表中的数据
			if(!StringUtils.isEmpty(uuid)){
				InnerMessage inner = new InnerMessage();
				inner.setId(uuid);
				inner.setUuid(uuid);
				innerMessageService.delete(inner);
			}
			
			return ApiResult.right();
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"请稍后再取消关注！");
		}
	}

}

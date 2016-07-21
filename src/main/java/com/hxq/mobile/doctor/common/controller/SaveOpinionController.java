package com.hxq.mobile.doctor.common.controller;

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

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.hxq.mobile.doctor.common.service.CustomerAdviceService;
import com.hxq.mobile.entity.common.CustomerAdvice;
import com.hxq.mobile.patient.common.controller.PatientController2;
import com.hxq.mobile.util.CompatibleTools;
import com.wxcommon.util.IdentityHelper;

@Controller("com.hxq.mobile.doctor.common.controller.DoctorSaveOpinionController")
@RequestMapping("/app/pub/doctor")
public class SaveOpinionController {
	Logger log = LoggerFactory.getLogger(PatientController2.class);
	
	/* 会员意见信息表 */
	@Resource(name = "com.hxq.mobile.doctor.common.service.CustomerAdviceService")
	private CustomerAdviceService customerAdviceService;

	/**
	 *  医生端个人中心——保存意见信息
	 * @param request
	 * @param response
	 * @param customerId 医生的id
	 * @return
	 */
	@RequestMapping(value = "/1.0/saveOpinion", method = RequestMethod.POST)
	public String saveOpinion(HttpServletRequest request, HttpServletResponse response,
							  @RequestParam("customerId") String customerId) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = CompatibleTools.getParam(request, response, false,
				new String[]{"customerId,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		String opinion = request.getParameter("opinion");//意见内容
		String mobile = request.getParameter("mobile");//手机号
		String email = request.getParameter("email");//邮箱
		String qq = request.getParameter("qq");//qq

		/************************ 添加意见反馈信息 *************************/
		CustomerAdvice customerAdvice =  new CustomerAdvice(IdentityHelper.uuid2());
		if (!StringUtil.isEmpty(opinion)) {
			customerAdvice.setAdviceContent(opinion);
		}
		if (!StringUtil.isEmpty(customerId)) {
			customerAdvice.setCustomerUuid(customerId);
		}
		if (!StringUtil.isEmpty(email)) {
			customerAdvice.setCustomerEmail(email);
		}
		if (!StringUtil.isEmpty(mobile)) {
			customerAdvice.setCustomerMobile(mobile);
		}
		if (!StringUtil.isEmpty(qq)) {
			customerAdvice.setCustomerQQ(qq);
		}
		customerAdvice.setType("1");// 1代表是医生
		customerAdvice.setStatus("0");// 0代表是未处理
		customerAdvice.setCreateTime(DateFormatHelper.getNowTimeStr());
		try {
			customerAdviceService.insert(customerAdvice);
		} catch (Exception e) {
			log.error(customerId, e);
			e.printStackTrace();
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
}

package com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.utils.StringReplace;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.thirdinterface.emailmessagesend.service.SendEmailService;
import com.aebiz.b2b2c.thirdinterface.mobilemessagesend.service.SmsService;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.service.MessageTemplateService;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.service.ThirdInterfaceTemplateService;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateModel;

/**
 * 消息模板发送
 * @author tjj
 *
 */
@Service
@Transactional
public class MessageTemplateServiceImpl implements MessageTemplateService {
	@Autowired
	private ThirdInterfaceTemplateService templeteService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private InnerMessageService innerMsgService;

	/**
	 * @param userType 用户类型（消息接收对象）：customer、store
	 * @param accountUuid 用户（商户）UUID （消息接收对象）
	 * @param templateId 模板ID
	 * @param mobile 手机号
	 * @param email 邮箱
	 * @param param 参数
	 * @param innerMsgParam 站内信参数
	 */
	public void sendMessage(String userType, String accountUuid, String templateId, String mobile,
			String email, Map<String, Object> param, Map<String, Object> innerMsgParam) {
		ThirdInterfaceTemplateModel templateModel = templeteService.getThirdInterfaceTemplateModelByTemplateId(templateId);
		if(null == templateModel){
			return;
		}
		if(!ThirdInterfaceTemplateModel.START_USE.equals(templateModel.getTemplateState())){
			return;
		}
		
		boolean isCanSend = true;
		
		String messageRemindUuid = templateModel.getMessageRemindUuid();
		if(!StringUtil.isEmpty(messageRemindUuid)){
			if(ThirdInterfaceTemplateModel.TEMPLATETYPE_MOBILE.equals(templateModel.getTemplateType())){
				//校验该模板是否在网站提醒中设置了是否通知
				//isCanSend = remindSubServiceInteractive.checkIsCanSend(userType, accountUuid, messageRemindUuid, ThirdInterfaceTemplateModel.TEMPLATETYPE_MOBILE);
			}else if(ThirdInterfaceTemplateModel.TEMPLATETYPE_EMAIL.equals(templateModel.getTemplateType())){
				//isCanSend = remindSubServiceInteractive.checkIsCanSend(userType, accountUuid, messageRemindUuid, ThirdInterfaceTemplateModel.TEMPLATETYPE_EMAIL);
			}else if(ThirdInterfaceTemplateModel.TEMPLATETYPE_INNERMESSAGE.equals(templateModel.getTemplateType())){
				//isCanSend = remindSubServiceInteractive.checkIsCanSend(userType, accountUuid, messageRemindUuid, ThirdInterfaceTemplateModel.TEMPLATETYPE_INNERMESSAGE);
			}
		}
		if(!isCanSend){
			return;
		}
		
		if(ThirdInterfaceTemplateModel.TEMPLATETYPE_MOBILE.equals(templateModel.getTemplateType())){
			String mobileMessage = repalceStr(templateModel, param);
			
			// 调用短信接口发送短信
			smsService.sendSms(mobile, mobileMessage);
		}else if(ThirdInterfaceTemplateModel.TEMPLATETYPE_EMAIL.equals(templateModel.getTemplateType())){
			String title = templateModel.getTemplateTitle();
			String emailMessage = repalceStr(templateModel, param);
			
			// 调用邮件接口发送邮件
			sendEmailService.send(email, emailMessage, title);
		}else if(ThirdInterfaceTemplateModel.TEMPLATETYPE_INNERMESSAGE.equals(templateModel.getTemplateType())){
			if(null!=innerMsgParam && innerMsgParam.size()>0){
				String title = templateModel.getTemplateTitle();
				String innerMessage = repalceStr(templateModel, param);
				
				String sendUser = (String) innerMsgParam.get("sendUser");
				String receiveUser = (String) innerMsgParam.get("receiveUser");
				String messageType = (String) innerMsgParam.get("messageType");
				String accountType = (String) innerMsgParam.get("accountType");
				
				// 调用站内信接口发送站内信
				//innerMsgService.saveInnerMessage(sendUser, receiveUser, messageType, accountType, title, innerMessage);
			}
		}
		
	}
	
	private String repalceStr(ThirdInterfaceTemplateModel template, Map<String, Object> param) {
		String sendMessage = "";

		if (template == null) {
			return "";
		}
		sendMessage = template.getTemplateContent();
		
		if(StringUtil.isEmpty(sendMessage)){
			return "";
		}
		// 循环param 进行字符串的替换 (如：{orderId}或###order### ,表示当前的orderId值)
		String mailContent = repalceStr(sendMessage, param);

		// 生成最终内容并且返回最终内容
		return mailContent;
	}
	
	/**
	 * 模板替换并返回
	 * 
	 * @param filename
	 *            读取的文件模版
	 * @param replaceTbl
	 *            替换列表
	 * @return String 模板替换出来的字符串
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String repalceStr(String rstr, Map<String, Object> replaceTbl) {
		Set keys = replaceTbl.keySet();
		Iterator iter = keys.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = (String) replaceTbl.get(key);
			System.out.println("[" + key + "] = " + value);
			rstr = StringReplace.getReplaceString(rstr, key, value);
		}
		return rstr;
	}

}

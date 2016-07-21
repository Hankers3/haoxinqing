package com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.service;

import java.util.Map;

/**
 * 消息模板发送
 * @author tjj
 *
 */
public interface MessageTemplateService {

	/**
	 * @param userType 用户类型（消息接收对象）：customer、store
	 * @param accountUuid 用户（商户）UUID
	 * @param templateId 模板ID
	 * @param mobile 手机号
	 * @param email 邮箱
	 * @param param 参数
	 * @param innerMsgParam 站内信参数
	 */
	void sendMessage(String userType, String accountUuid, String templateId, String mobile,
			String email,  Map<String, Object> param, Map<String, Object> innerMsgParam);

}

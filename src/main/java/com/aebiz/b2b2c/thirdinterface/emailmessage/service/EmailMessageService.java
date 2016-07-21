package com.aebiz.b2b2c.thirdinterface.emailmessage.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.thirdinterface.emailmessage.vo.EmailMessageModel;
import com.aebiz.b2b2c.thirdinterface.emailmessage.vo.EmailMessageModelQueryModel;

public interface EmailMessageService extends
		BaseService<EmailMessageModel, EmailMessageModelQueryModel> {
	
	/**
	 * 获取邮件设置信息
	 * 
	 * @return
	 */
	public EmailMessageModel getModel();
}

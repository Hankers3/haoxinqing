package com.aebiz.b2b2c.thirdinterface.mobilemessagesend.service;

import java.io.UnsupportedEncodingException;

public interface SmsService {
	/**
	 * 发送短信接口
	 * 
	 * @param mobile
	 *            发送手机号
	 * @param message
	 *            发送的信息内容
	 * @return 返回0 代表发送成功
	 * @throws UnsupportedEncodingException
	 */
	public void sendSms(String mobile, String message);
}

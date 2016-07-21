package com.aebiz.b2b2c.thirdinterface.emailmessagesend.service;

public interface SendEmailService {
	/**
	 * 邮件发送
	 * 
	 * @param email
	 *            发送的邮箱
	 * @param content
	 *            发送的内容
	 * @param title
	 *            发送的内容标题
	 */
	public void send(String email, String content, String title);
}

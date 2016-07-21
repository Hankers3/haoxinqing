package com.aebiz.b2b2c.cms.storeback.web.messageremind.vo;

public class StoreMessageRemind {
	
	/*消息uuid*/
	private String messageUuid;

	/*消息设置uuid*/
	private String remindSubUuid;

	/*站内信名称*/
	private String remindName;
	
	/*是否手机提醒*/
	private String mobileSend;
	
	/*是否邮件提醒*/
	private String emailSend;
	
	/*是否站内信提醒*/
	private String innerSend;
	
	public String getMessageUuid() {
		return messageUuid;
	}

	public void setMessageUuid(String messageUuid) {
		this.messageUuid = messageUuid;
	}

	public String getRemindSubUuid() {
		return remindSubUuid;
	}

	public void setRemindSubUuid(String remindSubUuid) {
		this.remindSubUuid = remindSubUuid;
	}

	public String getRemindName() {
		return remindName;
	}

	public void setRemindName(String remindName) {
		this.remindName = remindName;
	}

	public String getMobileSend() {
		return mobileSend;
	}

	public void setMobileSend(String mobileSend) {
		this.mobileSend = mobileSend;
	}

	public String getEmailSend() {
		return emailSend;
	}

	public void setEmailSend(String emailSend) {
		this.emailSend = emailSend;
	}

	public String getInnerSend() {
		return innerSend;
	}

	public void setInnerSend(String innerSend) {
		this.innerSend = innerSend;
	}

}

package com.aebiz.b2b2c.thirdinterface.emailmessage.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;


@Entity
@Table(name = "email_message")
public class EmailMessageModel extends BaseModel {
	/** 发件服务器 **/
	private String smtpService = "";

	/** 用户账号 **/
	private String smtpUser = "";

	/** 账号密码 **/
	private String smtpPassword = "";

	/** 发信邮件地址 **/
	private String smtpAddress = "";

	/** 发件人中文名 **/
	private String smtpUserName = "";

	@Transient
	private String repeatPassWord = "";

	public String getSmtpService() {
		return smtpService;
	}

	public void setSmtpService(String smtpService) {
		this.smtpService = smtpService;
	}

	public String getSmtpUser() {
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public String getSmtpAddress() {
		return smtpAddress;
	}

	public void setSmtpAddress(String smtpAddress) {
		this.smtpAddress = smtpAddress;
	}

	public String getSmtpUserName() {
		return smtpUserName;
	}

	public void setSmtpUserName(String smtpUserName) {
		this.smtpUserName = smtpUserName;
	}

	public String getRepeatPassWord() {
		return repeatPassWord;
	}

	public void setRepeatPassWord(String repeatPassWord) {
		this.repeatPassWord = repeatPassWord;
	}

}

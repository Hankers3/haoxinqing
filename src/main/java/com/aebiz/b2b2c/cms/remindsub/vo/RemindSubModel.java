package com.aebiz.b2b2c.cms.remindsub.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="remind_sub")
public class RemindSubModel extends BaseModel{
	
	/*会员类型*/
	private String accountType;
	
	/*会员编号*/
	private String accountUuid;
	
	/*创建时间*/
	private String createTime;
	
	/*站内消息uuid*/
	private String messageRemindUuid;
	
	/*是否手机提醒*/
	private String mobileSend;
	
	/*是否邮件提醒*/
	private String emailSend;
	
	/*是否站内信提醒*/
	private String innerSend;
	
	public void setAccountType(String obj){
		this.accountType = obj;
	}
	public String getAccountType(){
		return this.accountType;
	}
	
	public void setAccountUuid(String obj){
		this.accountUuid = obj;
	}
	public String getAccountUuid(){
		return this.accountUuid;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	public String getMessageRemindUuid() {
		return messageRemindUuid;
	}
	public void setMessageRemindUuid(String messageRemindUuid) {
		this.messageRemindUuid = messageRemindUuid;
	}
	public void setMobileSend(String obj){
		this.mobileSend = obj;
	}
	public String getMobileSend(){
		return this.mobileSend;
	}
	
	public void setEmailSend(String obj){
		this.emailSend = obj;
	}
	public String getEmailSend(){
		return this.emailSend;
	}
	
	public void setInnerSend(String obj){
		this.innerSend = obj;
	}
	public String getInnerSend(){
		return this.innerSend;
	}
	@Override
	public String toString() {
		return "RemindSubModel [accountType=" + accountType + ", accountUuid="
				+ accountUuid + ", createTime=" + createTime
				+ ", messageRemindUuid=" + messageRemindUuid + ", mobileSend="
				+ mobileSend + ", emailSend=" + emailSend + ", innerSend="
				+ innerSend + "]";
	}
	
}

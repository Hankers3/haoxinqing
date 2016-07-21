package com.aebiz.b2b2c.cms.messageremind.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Entity
@Table(name="message_remind")
public class MessageRemindModel extends BaseModel{
	
	/*短信表单名称*/
	private String mobileName;
	
	/*邮箱表单名称*/
	private String emailName;
	
	/*站内信表单名称*/
	private String innerMessageName;
	
	/*网站提醒名称*/
	private String remindName;
	
	/*所属用户*/
	private String accountType;
	
	/*可用状态*/
	private String status;
	
	/*该提醒是否设置短信*/
	private String mobileCheck;
	
	/*该提醒是否设置邮箱*/
	private String emailCheck;
	
	/*该提醒是否设置邮箱*/
	private String innerMessageCheck;
	
	/*所属类型*/
	private String type;
	
	public void setMobileName(String obj){
		this.mobileName = obj;
	}
	public String getMobileName(){
		return this.mobileName;
	}
	
	public void setEmailName(String obj){
		this.emailName = obj;
	}
	public String getEmailName(){
		return this.emailName;
	}
	
	public void setInnerMessageName(String obj){
		this.innerMessageName = obj;
	}
	public String getInnerMessageName(){
		return this.innerMessageName;
	}
	
	public void setRemindName(String obj){
		this.remindName = obj;
	}
	public String getRemindName(){
		return this.remindName;
	}
	
	public void setAccountType(String obj){
		this.accountType = obj;
	}
	public String getAccountType(){
		if(!StringUtil.isEmpty(this.accountType)){
			return AccountType.getNameByValue(this.accountType);
		}
		return this.accountType;
	}
	public void setStatus(String obj){
		this.status = obj;
	}
	public String getStatus(){
		return this.status;
	}
	
	public void setMobileCheck(String obj){
		this.mobileCheck = obj;
	}
	public String getMobileCheck(){
		return this.mobileCheck;
	}
	
	public void setEmailCheck(String obj){
		this.emailCheck = obj;
	}
	public String getEmailCheck(){
		return this.emailCheck;
	}
	
	public void setInnerMessageCheck(String obj){
		this.innerMessageCheck = obj;
	}
	public String getInnerMessageCheck(){
		return this.innerMessageCheck;
	}
	
	public void setType(String obj){
		this.type = obj;
	}
	public String getType(){
		if(!StringUtil.isEmpty(this.type)){
			return MessageType.getNameByValue(this.type);
		}
		return this.type;
	}
	@Override
	public String toString() {
		return "MessageRemindModel [mobileName=" + mobileName + ", emailName="
				+ emailName + ", innerMessageName=" + innerMessageName
				+ ", remindName=" + remindName + ", accountType=" + accountType
				+ ", status=" + status + ", mobileCheck=" + mobileCheck
				+ ", emailCheck=" + emailCheck + ", innerMessageCheck="
				+ innerMessageCheck + ", type=" + type + "]";
	}	
}

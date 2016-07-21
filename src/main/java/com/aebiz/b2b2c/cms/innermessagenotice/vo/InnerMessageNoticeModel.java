package com.aebiz.b2b2c.cms.innermessagenotice.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;

/*平台消息通知表*/
@Entity
@Table(name="inner_message_notice")
@Component
public class InnerMessageNoticeModel extends BaseModel{
	
	@Transient
	private static InnerMessageService innerMessageService;
	@Autowired
	public  void setInnerMessageService(InnerMessageService messageService) {
		this.innerMessageService = messageService;
	}
	
	/*接收人编号*/
	private String userId;
	
	/*消息编号*/
	private String messageUuid;
	
	/*阅读状态*/
	private String readStatus;
	
	/*通知内容*/
	@Transient
	private InnerMessageModel innerMessageModel;
	

	public InnerMessageModel getInnerMessageModel() {
		InnerMessageModel im = innerMessageService.getByUuid(this.messageUuid);
		if(im != null){
			return im ;
		}
		return innerMessageModel;
	}
	public void setInnerMessageModel(InnerMessageModel innerModel) {
		this.innerMessageModel = innerModel;
	}
	public void setUserId(String obj){
		this.userId = obj;
	}
	public String getUserId(){
		return this.userId;
	}
	
	public void setMessageUuid(String obj){
		this.messageUuid = obj;
	}
	public String getMessageUuid(){
		return this.messageUuid;
	}
	
	public void setReadStatus(String obj){
		this.readStatus = obj;
	}
	public String getReadStatus(){
		return this.readStatus;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[userId=" + this.getUserId() + ",messageUuid=" + this.getMessageUuid() + ",readStatus=" + this.getReadStatus() + ",]";
	}	
}

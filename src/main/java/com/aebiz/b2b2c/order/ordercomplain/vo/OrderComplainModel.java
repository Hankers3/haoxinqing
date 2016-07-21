package com.aebiz.b2b2c.order.ordercomplain.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="order_complain")
public class OrderComplainModel extends BaseModel{
	private String orderMainUuid;
	private String customerUuid;
	private String commentTime;
	private String content;
	private String managerUuid;
	
	private String state;
	
	public void setOrderMainUuid(String obj){
		this.orderMainUuid = obj;
	}
	public String getOrderMainUuid(){
		return this.orderMainUuid;
	}
	
	public void setCustomerUuid(String obj){
		this.customerUuid = obj;
	}
	public String getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setCommentTime(String obj){
		this.commentTime = obj;
	}
	public String getCommentTime(){
		return this.commentTime;
	}
	
	public void setContent(String obj){
		this.content = obj;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setManagerUuid(String obj){
		this.managerUuid = obj;
	}
	public String getManagerUuid(){
		return this.managerUuid;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[orderMainUuid=" + this.getOrderMainUuid() + ",customerUuid=" + this.getCustomerUuid() + ",commentTime=" + this.getCommentTime() + ",content=" + this.getContent() + ",managerUuid=" + this.getManagerUuid() + ",state=" + this.getState() + ",]";
	}	
}

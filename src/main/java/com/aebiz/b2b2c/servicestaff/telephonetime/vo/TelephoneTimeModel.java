package com.aebiz.b2b2c.servicestaff.telephonetime.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 电话咨询的时间
 * 
 * 
 */
@Entity
@Table(name="telephone_time")
public class TelephoneTimeModel extends BaseModel{
	/*电话咨询时长*/
	private String telTime;
	/*电话咨询时长状态*/
	private String state;
	
	public void setTelTime(String obj){
		this.telTime = obj;
	}
	public String getTelTime(){
		return this.telTime;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[telTime=" + this.getTelTime() + ",state=" + this.getState() + ",]";
	}	
}

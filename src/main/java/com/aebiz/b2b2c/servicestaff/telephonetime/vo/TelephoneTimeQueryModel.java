package com.aebiz.b2b2c.servicestaff.telephonetime.vo;

public class TelephoneTimeQueryModel extends TelephoneTimeModel {
	private String telTime;
	
	public void setTelTime(String obj){
		this.telTime = obj;
	}
	public String getTelTime(){
		return this.telTime;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[telTime=" + this.getTelTime() + ",]";
	}
}

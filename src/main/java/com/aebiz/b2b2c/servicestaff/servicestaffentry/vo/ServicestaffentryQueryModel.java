package com.aebiz.b2b2c.servicestaff.servicestaffentry.vo;

public class ServicestaffentryQueryModel extends ServicestaffentryModel {
	private String id2;
	
	public void setId2(String obj){
		this.id2 = obj;
	}
	public String getId2(){
		return this.id2;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[id2=" + this.getId2() + ",]";
	}
}

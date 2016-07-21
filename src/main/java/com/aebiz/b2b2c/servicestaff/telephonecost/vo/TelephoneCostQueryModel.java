package com.aebiz.b2b2c.servicestaff.telephonecost.vo;

public class TelephoneCostQueryModel extends TelephoneCostModel {
	private String telCost;
	
	public void setTelCost(String obj){
		this.telCost = obj;
	}
	public String getTelCost(){
		return this.telCost;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[telCost=" + this.getTelCost() + ",]";
	}
}

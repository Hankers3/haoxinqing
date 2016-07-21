package com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo;

public class VisitCustomerCommonQueryModel extends VisitCustomerCommonModel {
	private String serviceStatffUuid;
	
	public void setServiceStatffUuid(String obj){
		this.serviceStatffUuid = obj;
	}
	public String getServiceStatffUuid(){
		return this.serviceStatffUuid;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[serviceStatffUuid=" + this.getServiceStatffUuid() + ",]";
	}
}

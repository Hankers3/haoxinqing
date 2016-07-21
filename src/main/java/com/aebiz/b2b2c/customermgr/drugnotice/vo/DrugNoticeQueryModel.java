package com.aebiz.b2b2c.customermgr.drugnotice.vo;

public class DrugNoticeQueryModel extends DrugNoticeModel {
	private String customerUuid;
	
	public void setCustomerUuid(String obj){
		this.customerUuid = obj;
	}
	public String getCustomerUuid(){
		return this.customerUuid;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[customerUuid=" + this.getCustomerUuid() + ",]";
	}
}

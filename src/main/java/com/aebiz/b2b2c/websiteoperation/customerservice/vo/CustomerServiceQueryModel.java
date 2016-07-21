package com.aebiz.b2b2c.websiteoperation.customerservice.vo;

public class CustomerServiceQueryModel extends CustomerServiceModel {
	private String customerServiceName;
	
	public void setCustomerServiceName(String obj){
		this.customerServiceName = obj;
	}
	public String getCustomerServiceName(){
		return this.customerServiceName;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[customerServiceName=" + this.getCustomerServiceName() + ",]";
	}
}

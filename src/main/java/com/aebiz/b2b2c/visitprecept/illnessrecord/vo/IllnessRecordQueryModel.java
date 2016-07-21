package com.aebiz.b2b2c.visitprecept.illnessrecord.vo;

public class IllnessRecordQueryModel extends IllnessRecordModel {
	
	
	/*用户名*/
	private String customerName="";
	
	/*医生名*/
	private String doctoerName="";
	
	
	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getDoctoerName() {
		return doctoerName;
	}


	public void setDoctoerName(String doctoerName) {
		this.doctoerName = doctoerName;
	}


	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}

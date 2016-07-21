package com.aebiz.b2b2c.visitprecept.doctoradvice.vo;

public class DoctorAdviceQueryModel extends DoctorAdviceModel {
	
	/*用户名*/
	private String customerName="";
	
	/*医生名*/
	private String serviceStaffName="";
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getServiceStaffName() {
		return serviceStaffName;
	}

	public void setServiceStaffName(String serviceStaffName) {
		this.serviceStaffName = serviceStaffName;
	}

	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}

package com.aebiz.b2b2c.visitprecept.visitrecord.vo;

public class VisitRecordQueryModel extends VisitRecordModel {
	
	/*用户名*/
	private String customerName="";
	
	/*用户电话*/
	private String customerMobile="";
	
	/*医生名*/
	private String doctoerName="";
	
	/*医生名*/
	private String doctorMobile="";
	/*随访状态*/
	private String visitType1="";
	
	
	public String getVisitType1() {
        return visitType1;
	}



	public void setVisitType1(String visitType1) {
        this.visitType1 = visitType1;
	}



	public String getCustomerMobile() {
		return customerMobile;
	}



	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}



	public String getDoctorMobile() {
		return doctorMobile;
	}



	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}



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

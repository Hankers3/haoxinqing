package com.aebiz.b2b2c.customermgr.consultrecord.vo;

public class ConsultRecordQueryModel extends ConsultRecordModel {
	
	private String  customerName="";
	
	private String  doctorName="";
	
	private String  customerMobileq="";
	
	private String  doctorMobileq="";
	/*医生是否开通审核功能*/
	private String examq = "";
	public String getExamq() {
		return examq;
	}

	public void setExamq(String examq) {
		this.examq = examq;
	}

	public String getCustomerMobileq() {
		return customerMobileq;
	}

	public void setCustomerMobileq(String customerMobileq) {
		this.customerMobileq = customerMobileq;
	}

	public String getDoctorMobileq() {
		return doctorMobileq;
	}

	public void setDoctorMobileq(String doctorMobileq) {
		this.doctorMobileq = doctorMobileq;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}

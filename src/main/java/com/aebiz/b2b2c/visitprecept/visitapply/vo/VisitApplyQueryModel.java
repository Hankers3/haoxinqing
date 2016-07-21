package com.aebiz.b2b2c.visitprecept.visitapply.vo;

public class VisitApplyQueryModel extends VisitApplyModel {
	private String customerUuid;
	private String customerMoblie = "";
	private String doctorqName = "";
	private String customerqName = "";
	private String doctorMoblie = "";
	
	private String startTime ="";
	private String endTime ="";

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public String getCustomerMoblie() {
		return customerMoblie;
	}

	public void setCustomerMoblie(String customerMoblie) {
		this.customerMoblie = customerMoblie;
	}

	public String getDoctorMoblie() {
		return doctorMoblie;
	}

	public void setDoctorMoblie(String doctorMoblie) {
		this.doctorMoblie = doctorMoblie;
	}

	public String getDoctorqName() {
		return doctorqName;
	}

	public void setDoctorqName(String doctorqName) {
		this.doctorqName = doctorqName;
	}

	public String getCustomerqName() {
		return customerqName;
	}

	public void setCustomerqName(String customerqName) {
		this.customerqName = customerqName;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[customerUuid=" + this.getCustomerUuid() + ",]";
	}
}

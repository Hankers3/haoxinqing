package com.aebiz.b2b2c.visitprecept.medicalrecord.vo;

public class MedicalRecordQueryModel extends MedicalRecordModel {

	private String customerMoblie = "";
	private String doctorqName = "";

	private String customerqName = "";

	private String doctorMoblie = "";

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
				+ " ,[]";
	}
}

package com.aebiz.b2b2c.visitprecept.healthguide.vo;

public class HealthGuideQueryModel extends HealthGuideModel {
	/* 医生名 */// 模糊查询时用到
	private String doctorName = "";
	/* 患者名 */
	private String customerName = "";

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

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[]";
	}
}

package com.aebiz.b2b2c.finance.withdrawapply.vo;

public class WithdrawApplyQueryModel extends WithdrawApplyModel {

	/* 查询前几个月的时间，如前三个月，则time为3 */
	private String time = "";

	private String doctorNameq = "";

	private String doctorMobileq = "";

	public String getDoctorNameq() {
		return doctorNameq;
	}

	public void setDoctorNameq(String doctorNameq) {
		this.doctorNameq = doctorNameq;
	}

	public String getDoctorMobileq() {
		return doctorMobileq;
	}

	public void setDoctorMobileq(String doctorMobileq) {
		this.doctorMobileq = doctorMobileq;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}

package com.aebiz.b2b2c.servicestaff.doctorbill.vo;

public class DoctorBillQueryModel extends DoctorBillModel {
	
	/*医生名*/
	private String  doctorName="";
	
	/*开始时间*/
	private String startTime="";
	
	/*结束时间*/
	private String endTime ="";
	
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

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

	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}

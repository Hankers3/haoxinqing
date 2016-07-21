package com.aebiz.b2b2c.websiteoperation.doctorshare.vo;

public class DoctorShareQueryModel extends DoctorShareModel {
	private String doctorUuid;
	
	public void setDoctorUuid(String obj){
		this.doctorUuid = obj;
	}
	public String getDoctorUuid(){
		return this.doctorUuid;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[doctorUuid=" + this.getDoctorUuid() + ",]";
	}
}

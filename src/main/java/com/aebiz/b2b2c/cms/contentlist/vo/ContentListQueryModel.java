package com.aebiz.b2b2c.cms.contentlist.vo;

public class ContentListQueryModel extends ContentListModel {

	private String doctorName ="";
	
	
	private String contentName ="";
	
	private String beginTime ="";
	
	
	private String endTime ="";
	
	
	
	public String getDoctorName() {
		return doctorName;
	}



	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}



	public String getContentName() {
		return contentName;
	}



	public void setContentName(String contentName) {
		this.contentName = contentName;
	}



	public String getBeginTime() {
		return beginTime;
	}



	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[doctorUuid=" + this.getDoctorUuid() + ",]";
	}
}

package com.aebiz.b2b2c.userfront.platad.vo;

public class PlatAdQueryModel extends PlatAdModel {
	private String createTime2;
	
	/* 创建时间开始*/
	private String createStartTime;
	/* 创建时间结束*/
	private String createEndTime;
	
	public String getCreateStartTime() {
		return createStartTime;
	}
	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}
	public String getCreateEndTime() {
		return createEndTime;
	}
	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}
	public void setCreateTime2(String obj){
		this.createTime2 = obj;
	}
	public String getCreateTime2(){
		return this.createTime2;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[createTime2=" + this.getCreateTime2() + ",]";
	}
}

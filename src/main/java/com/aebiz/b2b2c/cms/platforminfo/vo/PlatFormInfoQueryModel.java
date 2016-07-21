package com.aebiz.b2b2c.cms.platforminfo.vo;

public class PlatFormInfoQueryModel extends PlatFormInfoModel {
	private String createTime2;
	
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

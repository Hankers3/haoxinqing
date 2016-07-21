package com.aebiz.b2b2c.basicdata.province.vo;

public class ProvinceQueryModel extends ProvinceModel {
	private String createTime;

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[createTime=" + this.getCreateTime() + ",]";
	}
}

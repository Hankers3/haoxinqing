package com.aebiz.b2b2c.websiteoperation.options.vo;

public class OptionsQueryModel extends OptionsModel {
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

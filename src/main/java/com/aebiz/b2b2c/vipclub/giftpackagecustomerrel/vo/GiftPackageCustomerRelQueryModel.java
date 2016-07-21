package com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo;

public class GiftPackageCustomerRelQueryModel extends
		GiftPackageCustomerRelModel {
	private String createTime2;

	public void setCreateTime2(String obj) {
		this.createTime2 = obj;
	}

	public String getCreateTime2() {
		return this.createTime2;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[createTime2=" + this.getCreateTime2() + ",]";
	}
}

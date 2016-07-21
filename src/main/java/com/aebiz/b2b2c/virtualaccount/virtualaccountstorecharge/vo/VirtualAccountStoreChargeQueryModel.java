package com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo;

public class VirtualAccountStoreChargeQueryModel extends
		VirtualAccountStoreChargeModel {

	/* 体现根据时间查询时的时间类型 1:3个月以内 2:3个月之前 3:1年内 */
	private String timeType;

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[]";
	}
}

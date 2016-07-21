package com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo;

/**
 * 会员统计表查询实体类
 * 
 * @author huyingying
 * 
 */
public class VipclubIntegralStatQueryModel extends VipclubIntegralStatModel {
	/* 会员名 */
	private String customerName;
	
	/* 最小积分数 */
	private int intergralMin;
	
	/* 最大积分数 */
	private int intergralMax;

	public void setCustomerName(String obj) {
		this.customerName = obj;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setIntergralMin(int obj) {
		this.intergralMin = obj;
	}

	public int getIntergralMin() {
		return this.intergralMin;
	}

	public void setIntergralMax(int obj) {
		this.intergralMax = obj;
	}

	public int getIntergralMax() {
		return this.intergralMax;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[customerName=" + this.getCustomerName()
				+ ",intergralMin=" + this.getIntergralMin() + ",intergralMax="
				+ this.getIntergralMax() + ",]";
	}
}

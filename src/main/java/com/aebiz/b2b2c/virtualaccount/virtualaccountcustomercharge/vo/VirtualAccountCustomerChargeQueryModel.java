package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo;

public class VirtualAccountCustomerChargeQueryModel extends
		VirtualAccountCustomerChargeModel {

	/* 用户名 */
	private String userName = "";
	
	/* 真实姓名 */
	private String realName = "";

	/* 患者名 */
	private String customerName = "";

	/* 电话号 */
	private String mobile = "";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[]";
	}
}

package com.aebiz.b2b2c.customermgr.customer.vo;

public class CustomerQueryModel extends CustomerModel {

	private String sexQ;
	private String realNameQ;
	/* 省份code */
	private String province = "";

	/* 城市code */
	private String city = "";

	/* 查询的结束年龄 */
	private String beginAge = "";

	/* 查询的结束年龄 */
	private String endAge = "";

	public String getBeginAge() {
		return beginAge;
	}

	public void setBeginAge(String beginAge) {
		this.beginAge = beginAge;
	}

	public String getEndAge() {
		return endAge;
	}

	public void setEndAge(String endAge) {
		this.endAge = endAge;
	}

	public String getRealNameQ() {
		return realNameQ;
	}

	public void setRealNameQ(String realNameQ) {
		this.realNameQ = realNameQ;
	}

	public String getSexQ() {
		return sexQ;
	}

	public void setSexQ(String sexQ) {
		this.sexQ = sexQ;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}

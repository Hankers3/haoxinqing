package com.aebiz.b2b2c.customermgr.customertemp.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "customer_temp")
public class CustomerTempModel extends BaseModel {
	/* 会员名称 */
	private String customerName = "";

	/* 手机号 */
	private String mobile = "";

	/* 邮箱 */
	private String email = "";

	/* 昵称 */
	private String nickName = "";

	/* 性别 */
	private String sex = "";

	/* 生日 */
	private String birthday = "";

	/* 爱好 */
	private String hobby = "";

	/* 真实姓名 */
	private String realName = "";

	/* 省 */
	private String province = "";

	/* 市 */
	private String city = "";

	/* 区 */
	private String region = "";

	/* 详细地址 */
	private String address = "";

	/* 头像 */
	private String image = "";

	/* 婚姻状况 */
	private String marryState = "";

	/* 月收入 */
	private String income = "";

	/* 证件号码 */
	private String certCode = "";

	/* 教育程度 */
	private String education = "";

	/* 所在行业 */
	private String industry = "";

	/* 邮编 */
	private String zipCode = "";

	/* 证件类型 */
	private String certType = "";

	public void setCustomerName(String obj) {
		this.customerName = obj;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setMobile(String obj) {
		this.mobile = obj;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String obj) {
		this.email = obj;
	}

	public String getEmail() {
		return this.email;
	}

	public void setNickName(String obj) {
		this.nickName = obj;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setSex(String obj) {
		this.sex = obj;
	}

	public String getSex() {
		return this.sex;
	}

	public void setBirthday(String obj) {
		this.birthday = obj;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setHobby(String obj) {
		this.hobby = obj;
	}

	public String getHobby() {
		return this.hobby;
	}

	public void setRealName(String obj) {
		this.realName = obj;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setProvince(String obj) {
		this.province = obj;
	}

	public String getProvince() {
		return this.province;
	}

	public void setCity(String obj) {
		this.city = obj;
	}

	public String getCity() {
		return this.city;
	}

	public void setRegion(String obj) {
		this.region = obj;
	}

	public String getRegion() {
		return this.region;
	}

	public void setAddress(String obj) {
		this.address = obj;
	}

	public String getAddress() {
		return this.address;
	}

	public void setImage(String obj) {
		this.image = obj;
	}

	public String getImage() {
		return this.image;
	}

	public void setMarryState(String obj) {
		this.marryState = obj;
	}

	public String getMarryState() {
		return this.marryState;
	}

	public void setIncome(String obj) {
		this.income = obj;
	}

	public String getIncome() {
		return this.income;
	}

	public void setCertCode(String obj) {
		this.certCode = obj;
	}

	public String getCertCode() {
		return this.certCode;
	}

	public void setEducation(String obj) {
		this.education = obj;
	}

	public String getEducation() {
		return this.education;
	}

	public void setIndustry(String obj) {
		this.industry = obj;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setZipCode(String obj) {
		this.zipCode = obj;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setCertType(String obj) {
		this.certType = obj;
	}

	public String getCertType() {
		return this.certType;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[customerName=" + this.getCustomerName() + ",mobile="
				+ this.getMobile() + ",email=" + this.getEmail() + ",nickName="
				+ this.getNickName() + ",sex=" + this.getSex() + ",birthday="
				+ this.getBirthday() + ",hobby=" + this.getHobby()
				+ ",realName=" + this.getRealName() + ",province="
				+ this.getProvince() + ",city=" + this.getCity() + ",region="
				+ this.getRegion() + ",address=" + this.getAddress()
				+ ",image=" + this.getImage() + ",marryState="
				+ this.getMarryState() + ",income=" + this.getIncome()
				+ ",certCode=" + this.getCertCode() + ",education="
				+ this.getEducation() + ",industry=" + this.getIndustry()
				+ ",zipCode=" + this.getZipCode() + ",certType="
				+ this.getCertType() + ",]";
	}
}

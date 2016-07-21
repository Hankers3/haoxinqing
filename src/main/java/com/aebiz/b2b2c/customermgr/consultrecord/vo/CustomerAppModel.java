package com.aebiz.b2b2c.customermgr.consultrecord.vo;

/**
 * 临时Model
 * 
 * @author szr
 * 
 */

public class CustomerAppModel {
	
	private String consultRecordUuid = "";
	
	private String customerName = "";
	
	private String customerUuid = "";
	
	private String realName = "";

	private String sex = "";

	private String age = "";

	private String iconImage = "";

	private String content = "";

	private String reply = "";

	private String createTime = ""; 
	
	public String getConsultRecordUuid() {
		return consultRecordUuid;
	}

	public void setConsultRecordUuid(String consultRecordUuid) {
		this.consultRecordUuid = consultRecordUuid;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}

}

package com.aebiz.b2b2c.finance.withdrawapply.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 用户类型，当前类型可分为以下几种：<br />
 * 
 * 1.会员类型：member<br />
 * 2.家政员类型：merchant<br />
 * 
 * 
 * @author xueli  20150323 update
 * 
 */
public enum UserType {
	MEMBER(MessageHelper.getMessage("withdrawapply.qm.member"),"1"), 
	MERCHANT(MessageHelper.getMessage("withdrawapply.qm.merchant"), "2");

	String value = "";
	String name = "";

	private UserType(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getNameByKey(String value) {
		for (UserType spe : UserType.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}

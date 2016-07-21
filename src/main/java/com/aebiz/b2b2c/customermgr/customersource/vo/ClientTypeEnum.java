package com.aebiz.b2b2c.customermgr.customersource.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 客户端来源:移动端、PC端<br/>
 * 1.移动端<br/>
 * 2.pc端<br/>
 * 
 * @author likun
 * 
 */
public enum ClientTypeEnum {
	MOBILECLIENT("1", MessageHelper
			.getMessage("customersource.clientType.mobileClient")), PCCLIENT(
			"2", MessageHelper.getMessage("customersource.clientType.PCClient"));

	private String key;
	private String value;

	private ClientTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String getValue(String key) {
		for (ClientTypeEnum ct : ClientTypeEnum.values()) {
			if (key.equals(ct.key)) {
				return ct.value;
			}
		}
		return "";
	}

}

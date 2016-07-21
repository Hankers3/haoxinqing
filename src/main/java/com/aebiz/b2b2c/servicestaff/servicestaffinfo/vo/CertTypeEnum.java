package com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 会员实名认证证件类型�?br/>
 * 
 * 1.身份�?br/>
 * 2.军官�?br/>
 * 
 * @author likun
 * 
 */
public enum CertTypeEnum {
	IDCARD("1", MessageHelper.getMessage("customerinfo.certType.IDcard")), OFFICERCARD(
			"2", MessageHelper.getMessage("customerinfo.certType.officerCard"));

	private String key = "";
	private String value = "";

	private CertTypeEnum(String key, String value) {
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
		for (CertTypeEnum ct : CertTypeEnum.values()) {
			if (key.equals(ct.key)) {
				return ct.value;
			}
		}
		return "";
	}
}

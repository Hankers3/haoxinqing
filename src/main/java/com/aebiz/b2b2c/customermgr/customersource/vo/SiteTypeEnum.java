package com.aebiz.b2b2c.customermgr.customersource.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 来源站点 :来源分为本站注册、第三方外站注册、会员导入 <br/>
 * 1.本站注册<br/>
 * 2.外站注册<br/>
 * 3.会员导入<br/>
 * 
 * @author likun
 * 
 */
public enum SiteTypeEnum {
	HOMESTATION("1", MessageHelper
			.getMessage("customersource.siteType.homeStation")), OTHERSTATION(
			"2", MessageHelper
					.getMessage("customersource.siteType.otherStation")), IMPORTSTATION(
			"3", MessageHelper.getMessage("customersource.siteType.import"));

	private String key;
	private String value;

	private SiteTypeEnum(String key, String value) {
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
		for (SiteTypeEnum st : SiteTypeEnum.values()) {
			if (key.equals(st.key)) {
				return st.value;
			}
		}
		return "";
	}
}

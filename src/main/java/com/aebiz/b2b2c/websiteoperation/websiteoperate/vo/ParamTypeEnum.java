package com.aebiz.b2b2c.websiteoperation.websiteoperate.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 参数类型：<br>
 * 1.text 文本型<br>
 * 2.radio 单选型<br>
 * 
 * @author likun
 * 
 */
public enum ParamTypeEnum {
	text("1", MessageHelper.getMessage("websiteoperate.paramType.text")), radio(
			"2", MessageHelper.getMessage("websiteoperate.paramType.radio"));

	private String key;
	private String value;

	private ParamTypeEnum(String key, String value) {
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
		for (ParamTypeEnum pt : ParamTypeEnum.values()) {
			if (key.equals(pt.key)) {
				return pt.value;
			}
		}
		return "";
	}
}

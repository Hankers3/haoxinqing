package com.aebiz.b2b2c.basicdata.sensitiveword.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 关键词的敏感级别
 * 
 * 1.一般敏感：nomal <br />
 * 2.比较敏感 :sensitive<br />
 * 3.严重敏感 :verysensitive<br />
 * 
 * @author duandeyi
 * 
 */
public enum SensitiveWordEnum {

	NOMAL(MessageHelper.getMessage("sensitiveword.level.nomal"), "1"), SENSITIVE(
			MessageHelper.getMessage("sensitiveword.level.sensitive"), "2"), VERYSENSITIVE(
			MessageHelper.getMessage("sensitiveword.level.verysensitive"), "3");

	String value = "";
	String name = "";

	private SensitiveWordEnum(String name, String value) {
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
		for (SensitiveWordEnum spe : SensitiveWordEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}

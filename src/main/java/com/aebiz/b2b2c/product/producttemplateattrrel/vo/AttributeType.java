package com.aebiz.b2b2c.product.producttemplateattrrel.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 属性类型
 * TEXT ------ 文本框
 * SELECT ---- 下拉框
 * RADIO ----- 单选框
 * CHECKBOX -- 复选框
 * 
 * @author huangpinpin
 * 
 */
public enum AttributeType {
	//文本框
	TEXT("01", MessageHelper.getMessage("producttemplateattrrel.type.text")), 
	//下拉框
	SELECT("02", MessageHelper.getMessage("producttemplateattrrel.type.select")), 
	//单选框
	RADIO("03", MessageHelper.getMessage("producttemplateattrrel.type.radio")),
	//复选框
	CHECKBOX("04", MessageHelper.getMessage("producttemplateattrrel.type.checkbox"));
	
	private String value = "";
	private String name = "";

	private AttributeType(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	public static String getNameByKey(String value) {
		for (AttributeType c : AttributeType.values()) {
			if (c.getValue().equals(value)) {
				return c.getName();
			}
		}
		return "";
	}

}

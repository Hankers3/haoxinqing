package com.aebiz.b2b2c.cms.tagscategoy.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 标签类型
 * 
 * @author huangpinpin
 * 
 */
public enum TagsType {
	PRODUCT("01", MessageHelper.getMessage("tagscategory.type.product")), 
	APP("02", MessageHelper.getMessage("tagscategory.type.app")), 
	CUSTOMER("03", MessageHelper.getMessage("tagscategory.type.customer")), 
	OTHER("04", MessageHelper.getMessage("tagscategory.type.other"));
	
	private String value = "";
	private String name = "";

	private TagsType(String value, String name) {
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
		for (TagsType c : TagsType.values()) {
			if (c.getValue().equals(value)) {
				return c.getName();
			}
		}
		return "";
	}

	public static void main(String[] args) {
		System.out.println(TagsType.getNameByKey("01"));
		System.out.println(TagsType.APP.value);
	}

}

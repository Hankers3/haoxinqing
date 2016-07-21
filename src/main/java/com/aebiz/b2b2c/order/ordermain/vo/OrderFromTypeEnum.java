package com.aebiz.b2b2c.order.ordermain.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 
 * 订单来源状态分为： <br />
 * 
 * 1.手机:FROMMOBILE <br />
 * 2.网站:FROMWEBSITE <br />
 * 
 * @author duandeyi
 * 
 */
public enum OrderFromTypeEnum {

	FROMMOBILE(MessageHelper.getMessage("order.from.mobile"), "1"), FROMWEBSITE(
			MessageHelper.getMessage("order.from.website"), "2");

	String value = "";
	String name = "";

	private OrderFromTypeEnum(String name, String value) {
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
		for (OrderFromTypeEnum spe : OrderFromTypeEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}

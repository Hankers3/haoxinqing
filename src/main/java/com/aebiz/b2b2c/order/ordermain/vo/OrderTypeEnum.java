package com.aebiz.b2b2c.order.ordermain.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 
 * 订单类型分为： <br />
 * 1.实物订单<br />
 * 2.虚拟订单-本地生活订单<br />
 * 3.预售订单<br />
 * 4.团购订单<br />
 * 5.秒杀订单<br />
 * 
 * @author duandeyi
 * 
 */
public enum OrderTypeEnum {

	NOMALTYPE(MessageHelper.getMessage("ordermain.type.nomaltype"), "1"), O2OTYPE(
			MessageHelper.getMessage("ordermain.type.o2otype"), "2"), PRESELLTYPE(
			MessageHelper.getMessage("ordermain.type.preselltype"), "3"), GROUPBUYTYPE(
			MessageHelper.getMessage("ordermain.type.groupbuytype"), "4"), SECKILLTYPE(
			MessageHelper.getMessage("ordermain.type.seckilltype"), "5");

	String value = "";
	String name = "";

	private OrderTypeEnum(String name, String value) {
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
		for (OrderTypeEnum spe : OrderTypeEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}

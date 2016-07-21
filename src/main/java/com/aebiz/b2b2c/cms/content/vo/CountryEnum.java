package com.aebiz.b2b2c.cms.content.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.order.ordermain.vo.OrderFromTypeEnum;
/**
 * app资讯筛选类别
 * @author xueli
 * 1：中国
 * 2：美国
 * 3：英国
 * 4：国际
 * 5：其他
 * 
 */
public enum CountryEnum {
	CHINA(MessageHelper.getMessage("country.m.China"), "1"),
	AMERICA(MessageHelper.getMessage("country.m.America"), "2"),
	ENGLAND(MessageHelper.getMessage("country.m.England"), "3"),
	INTERNATIONAL(MessageHelper.getMessage("country.m.internation"), "4"),
	OTHER(MessageHelper.getMessage("country.m.other"), "5");
	
	String value = "";
	String name = "";

	private CountryEnum(String name, String value) {
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

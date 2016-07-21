package com.aebiz.b2b2c.order.ordermain.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 
 * 订单类型分为： <br />
 * 1.在线支付<br />
 * 2.货到付款<br />
 * 3.银行转账:<br />
 * 4.邮局汇款<br />
 * 
 * @author duandeyi
 * 
 */
public enum OrderPayTypeEnum {

	PAYTYPECOD(MessageHelper.getMessage("ordermain.paytype.cod"), "1"), PAYTYPENOCOD(
			MessageHelper.getMessage("ordermain.paytype.nocod"), "2"), PAYTYPEBANK(
			MessageHelper.getMessage("ordermain.paytype.bank"), "3"), PAYTYPEPOST(
			MessageHelper.getMessage("ordermain.paytype.post"), "4");

	String value = "";
	String name = "";

	private OrderPayTypeEnum(String name, String value) {
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
		for (OrderPayTypeEnum spe : OrderPayTypeEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}

package com.aebiz.b2b2c.order.ordermain.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 
 * 订单状态分为： <br />
 * 1.等待买家付款 <br />
 * 2.订单取消<br />
 * 3.已支付(待审核)<br />
 * 4.待执行（审核通过）<br />
 * 5.待回访<br />
 * 6.已完成<br />
 * 7.转动态订单<br />
 * 8.已拒绝<br />
 * 
 * @author xueli
 * 
 */
public enum OrderStatusEnum {

	WAITPAY(MessageHelper.getMessage("ordermain.status.waitpay"), "1"), ORDERCANCEL(
			MessageHelper.getMessage("ordermain.m.ordercancel"), "2"), PAID(
			MessageHelper.getMessage("ordermain.m.paid"), "3"), CONFIRMED(
			MessageHelper.getMessage("ordermain.m.confirmed"), "4"), DROPIN(
			MessageHelper.getMessage("ordermain.m.dropIn"), "5"), DROPINCOMPLETE(
			MessageHelper.getMessage("ordermain.m.completed"), "6"), DONGTAI(
			MessageHelper.getMessage("ordermain.m.working"),"7"),
			REFUSE(MessageHelper.getMessage("ordermain.m.ordermain.status.refuse"),"8");;

	String value = "";
	String name = "";

	private OrderStatusEnum(String name, String value) {
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
		for (OrderStatusEnum spe : OrderStatusEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}

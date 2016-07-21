package com.aebiz.b2b2c.finance.invoice.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
/**
 * 交易类型枚举类型
 * @author Administrator
 *
 */
public enum SaleType {
	
	/* 交易类型  账单结算*/
	SALE_TYPE_BILL_SETTLE("1", MessageHelper.getMessage("invoice.saleType.bill_settle")),

	/* 交易类型  商户订单*/
	SALE_TYPE_STORE_ORDER("2", MessageHelper.getMessage("invoice.saleType.store_order")),
	
	/* 交易类型 礼品卡购买 */
	SALE_TYPE_GIFTCARD_BUY("3", MessageHelper.getMessage("invoice.saleType.gift_buy")),
	
	/* 交易类型 续缴服务费 */
	SALE_TYPE_PAY_SERVICEFEE("4", MessageHelper.getMessage("invoice.saleType.pay_servicefee"));
	
	/* 积分类型值 */
	private String value = "";
	
	/* 积分类型名称 */
	private String name = "";

	private SaleType(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * 获取方法
	 * 
	 * @paparm value
	 * @return name
	 */
	public static String getNameByKey(String value) {
		for (SaleType c : SaleType.values()) {
			if (c.getValue().equals(value)) {
				return c.getName();
			}
		}
		return "";
	}
}

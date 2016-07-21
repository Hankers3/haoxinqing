package com.aebiz.b2b2c.finance.invoice.vo;

public enum SaleTypeInvoice {
	
	/* 获取交易类型为账单结算，商户订单，续缴服务费的发票*/
	BILL_ORDER_SERVICEFEE("1"),

	/* 获取交易类型为账单结算，商户订单的发票*/
	BILL_ORDER("2"),
	
	/* 获取交易类型为账单结算，礼品卡购买，续缴服务费的发票 */
	BILL_GIFT_SERVICEFEE("3"),
	
	/* 获取交易类型为续缴服务费的发票 */
	SERVICEFEE("4");
	
	/* 类型值 */
	private String value = "";

	private SaleTypeInvoice(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}

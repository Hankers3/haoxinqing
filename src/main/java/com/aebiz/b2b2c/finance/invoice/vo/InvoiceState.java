package com.aebiz.b2b2c.finance.invoice.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
/**
 * 
 * @author Administrator
 *	发票状态枚举类型
 */
public enum InvoiceState {
	
	/* 发票状态 未开票 */
	UNINVOICED("0", MessageHelper.getMessage("invoice.invoiceState.uninvoiced")),

	/* 发票状态 已开票 */
	INVOICED("1", MessageHelper.getMessage("invoice.invoiceState.invoiced"));
	
	/* 发票状态值 */
	private String value = "";
	
	/* 发票状态名称 */
	private String name = "";

	private InvoiceState(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	/*** 
	 * 获取方法
	 * @paparm value
	 * @return name
	 */
	public static String getNameByKey(String value) {
		for (InvoiceState c : InvoiceState.values()) {
			if (c.getValue().equals(value)) {
				return c.getName();
			}
		}
		return "";
	}
}

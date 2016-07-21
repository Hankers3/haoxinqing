package com.aebiz.b2b2c.finance.storeservicecharge.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 服务费的支付状态状态的枚举<br>
 * 0:待支付   <br>
 * 1：支付成功  <br>
 * 2：支付失败
 *
 * @author tangyunkai
 *
 * @date 2014年10月29日 下午9:57:29
 *
 */
public enum PayState {

	// 0:待支付  
	UNDER_PAY("0", MessageHelper.getMessage("storebondcharge.m.underPay")),

	// 1：支付成功 
	PAY_SUCCESS("1", MessageHelper.getMessage("storebondcharge.m.paySuccess")),

	// 2：支付失败
	PAY_FAIL("2", MessageHelper.getMessage("storebondcharge.m.payFail"));

	private String value = "";

	private String name = "";

	private PayState(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	/**
	 * 对外提供枚举方法
	 * 
	 * @param value
	 * @return String
	 */
	public static String getNameByValue(String value) {
		for (PayState p : PayState.values()) {
			if (p.getValue().equals(value)) {
				return p.getName();
			}
		}
		return "";
	}
}

package com.aebiz.b2b2c.finance.customerbankrel.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 保证金操作状态的枚举<br>
 * 0: 未验证   <br>
 * 1：待验证  <br>
 * 2：验证成功 <br>
 * 3 :验证失败
 *
 * @author tangyunkai
 *
 * @date 2014年10月29日 下午9:57:29
 *
 */
public enum ValidateState {

	// 0: 未验证
	NO_CHECK("0", MessageHelper.getMessage("customerbankrel.m.nocheck")),

	// 1：待验证
	UNDER_CHECK("1", MessageHelper.getMessage("customerbankrel.m.undercheck")),

	// 2：验证成功
	CHECK_SUCCESS("2", MessageHelper.getMessage("customerbankrel.m.checkSuccess")),
	
	// 3 :验证失败
	CHECK_FAIL("2", MessageHelper.getMessage("customerbankrel.m.checkFail"));

	private String value = "";

	private String name = "";

	private ValidateState(String value, String name) {
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
		for (ValidateState p : ValidateState.values()) {
			if (p.getValue().equals(value)) {
				return p.getName();
			}
		}
		return "";
	}
}

package com.aebiz.b2b2c.customermgr.customer.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 账户冻结类型： <br/>
 * 
 * 1.账户被盗 <br/>
 * 2.严重违规 <br/>
 * 3.异常登录 <br/>
 * 4.其他原因<br/>
 * 
 * @author likun
 */
public enum FrozenTypeEnum {
	ACCOUNTSTOLEN("1", MessageHelper
			.getMessage("customer.frozentype.accountStolen")), BREAKRULE("2",
			MessageHelper.getMessage("customer.frozentype.breakRule")), UNUSUALLOGIN(
			"3", MessageHelper.getMessage("customer.frozentype.unusualLogin")), OTHERREASON(
			"4", MessageHelper.getMessage("customer.frozentype.otherReason"));

	private String key;
	private String value;

	private FrozenTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String getValue(String key) {
		for (FrozenTypeEnum ft : FrozenTypeEnum.values()) {
			if (key.equals(ft.key)) {
				return ft.value;
			}
		}
		return "";
	}
}

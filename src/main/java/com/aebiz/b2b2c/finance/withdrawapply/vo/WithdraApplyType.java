package com.aebiz.b2b2c.finance.withdrawapply.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 提现类型，当前类型可分为以下几种：<br />
 * 
 * 1.家政员帐户类型：merchantBalance<br />
 * 2.会员帐户类型：memberBalance<br />
 * 
 * 
 * @author xueli 20150323 update 
 * 
 */
public enum WithdraApplyType {
	MERCHANTBALANCE(MessageHelper.getMessage("withdrawapply.qm.merchantBalance"), "1"),
	MEMBERBALANCE(MessageHelper.getMessage("withdrawapply.qm.memberBalance"),"2"), 
	MERCHANGMARGIN(MessageHelper.getMessage("withdrawapply.qm.merchantMargin"), "3");

	String value = "";
	String name = "";

	private WithdraApplyType(String name, String value) {
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
		for (WithdraApplyType spe : WithdraApplyType.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}

}

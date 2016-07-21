package com.aebiz.b2b2c.finance.withdrawapply.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 家政员后台提现状态，当前类型可分为以下几种：<br />
 * 
 * 0：表示未处理	untreated
 * 1：表示已转账（提现成功）withdrawSuccess
 * 2：表示驳回（提现失败）	withdrawFail
 * @author xueli  20150323 update
 * 
 */
public enum StateType {
	UNTREATED(MessageHelper.getMessage("withdrawapply.qm.untreated"), "0"),
	WITHDRAWSUCCESS(MessageHelper.getMessage("withdrawapply.qm.withdrawSuccess"), "1"),
	WITHDRAWFAIL(MessageHelper.getMessage("withdrawapply.qm.withdrawFail"), "2");

	String value = "";
	String name = "";

	private StateType(String name, String value) {
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
		for (StateType spe : StateType.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}

}

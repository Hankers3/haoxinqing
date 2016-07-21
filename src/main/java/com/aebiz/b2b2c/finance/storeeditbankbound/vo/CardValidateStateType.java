package com.aebiz.b2b2c.finance.storeeditbankbound.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.finance.withdrawapply.vo.StateType;

/**
 * 验证状态<br/>
 * 0表示未验证<br/>
 * 1表示验证成功<br/>
 * 2表示验证失败<br/>
 * 3表示等待验证<br/>
 * @author XiaoY
 *
 */
public enum CardValidateStateType {

	UNTREATED(MessageHelper.getMessage("editbankbound.m.Untreated"), "0"),
	VILIDATESUCCESS(MessageHelper.getMessage("editbankbound.m.vilidateSuccess"), "1"),
	VILIDATEFAIL(MessageHelper.getMessage("editbankbound.m.vilidateFail"), "2"),
	VILIDATEFWAIT(MessageHelper.getMessage("editbankbound.m.vilidateWait"), "3");
	String value = "";
	String name = "";

	private CardValidateStateType(String name, String value) {
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

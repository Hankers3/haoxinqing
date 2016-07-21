package com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

public enum SysPermitEnum {

	// 1.菜单权限 //2.按钮权限
	MENUPERMIT("1", MessageHelper.getMessage("syspermit.m.menupermit")), BUTTONPERMIT(
			"2", MessageHelper.getMessage("syspermit.m.buttonpermit"));

	String value = "";
	String name = "";

	private SysPermitEnum(String name, String value) {
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
		for (SysPermitEnum spe : SysPermitEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}

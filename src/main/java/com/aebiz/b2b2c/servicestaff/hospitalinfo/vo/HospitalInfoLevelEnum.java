package com.aebiz.b2b2c.servicestaff.hospitalinfo.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 医院级别分为： <br />
 * 一级<br />
 * 二级甲等<br />
 * 二级乙等<br />
 * 二级丙等<br />
 * 三级特等<br />
 * 三级甲等<br />
 * 三级乙等<br />
 * 三级丙等<br />
 * 
 * @author szr
 * 
 */
public enum HospitalInfoLevelEnum {

	FIRST("一级", "1"), 
	SECOND_A("二级甲等", "2"), 
	SECOND_B("二级乙等", "3"), 
	SECOND_C("二级丙等", "4"), 
	THIRD_SP("三级特等", "5"), 
	THIRD_A("三级甲等", "6"),
	THIRD_B("三级乙等", "7"),
	THIRD_C("三级丙等", "8");
	String value = "";
	String name = "";

	private HospitalInfoLevelEnum(String name, String value) {
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
		for (HospitalInfoLevelEnum spe : HospitalInfoLevelEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}

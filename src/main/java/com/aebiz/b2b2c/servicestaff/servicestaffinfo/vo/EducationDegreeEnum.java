package com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 会员教育程度�?br/>
 * 
 * 1.初中 <br/>
 * 2.高中<br/>
 * 3.中专<br/>
 * 4.大专<br/>
 * 5.本科<br/>
 * 6.硕士<br/>
 * 7.博士<br/>
 * 8.博士�?br/>
 * 9.其他<br/>
 * 
 * @author likun
 * 
 */
public enum EducationDegreeEnum {
	JUNIOR("1", MessageHelper.getMessage("customerinfo.m.junior")), SENIOR("2",
			MessageHelper.getMessage("customerinfo.m.senior")), SECONDARY("3",
			MessageHelper.getMessage("customerinfo.m.secondary")), JUNIORCOLLEGE(
			"4", MessageHelper.getMessage("customerinfo.m.juniorCollege")), UNDERGRADUATE(
			"5", MessageHelper.getMessage("customerinfo.m.undergraduate")), MASTER(
			"6", MessageHelper.getMessage("customerinfo.m.master")), DOCTOR(
			"7", MessageHelper.getMessage("customerinfo.m.doctor")), POSTDOCTOR(
			"8", MessageHelper.getMessage("customerinfo.m.postdoctor")), OTHER(
			"9", MessageHelper.getMessage("customerinfo.m.other"));

	private String key = "";
	private String value = "";

	private EducationDegreeEnum(String key, String value) {
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
		for (EducationDegreeEnum ed : EducationDegreeEnum.values()) {
			if (key.equals(ed.key)) {
				return ed.value;
			}
		}
		return "";
	}
}

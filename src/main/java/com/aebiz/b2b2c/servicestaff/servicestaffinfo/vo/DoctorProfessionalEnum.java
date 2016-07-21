package com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 医生的职称信息 <br/>
 * 
 * 1.主任医师<br/>
 * 2.副主任医师<br/>
 * 3.主治医师<br/>
 * Attending 4.住院医师<br/>
 * 5.助理医师<br/>
 * 6.实习医师<br/>
 * @author xl
 * 
 */
public enum DoctorProfessionalEnum {
	DIRECTOR("主任医师", MessageHelper.getMessage("doctor.professional.DIRECTOR")), DEPUTY(
			"副主任医师", MessageHelper.getMessage("doctor.professional.DEPUTY")), ATTEND(
			"主治医师", MessageHelper.getMessage("doctor.professional.ATTEND")), HOSPITAL(
			"住院医师", MessageHelper.getMessage("doctor.professional.HOSPITAL")), ASSISTANT(
			"助理医师", MessageHelper.getMessage("doctor.professional.ASSISTANT")), PRACTICE(
			"实习医师", MessageHelper.getMessage("doctor.professional.PRACTICE"));

	private String key = "";
	private String value = "";

	private DoctorProfessionalEnum(String key, String value) {
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
		for (DoctorProfessionalEnum ct : DoctorProfessionalEnum.values()) {
			if (key.equals(ct.key)) {
				return ct.value;
			}
		}
		return "";
	}

	public static String getkey(String value) {
		for (DoctorProfessionalEnum ct : DoctorProfessionalEnum.values()) {
			if (value.equals(ct.value)) {
				return ct.key;
			}
		}
		return "";
	}
}

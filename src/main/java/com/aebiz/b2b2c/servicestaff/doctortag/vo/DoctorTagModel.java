package com.aebiz.b2b2c.servicestaff.doctortag.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * @author szr 加号设置中医生和标签的关系表
 */
@Entity
@Table(name = "doctor_tag")
public class DoctorTagModel extends BaseModel {

	/* 医生id */
	private String doctorUuid;

	/* 标签id */
	private String tagUuid;

	public String getDoctorUuid() {
		return doctorUuid;
	}

	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}

	public String getTagUuid() {
		return tagUuid;
	}

	public void setTagUuid(String tagUuid) {
		this.tagUuid = tagUuid;
	}

}

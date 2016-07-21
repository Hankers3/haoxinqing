package com.aebiz.b2b2c.servicestaff.packagedoctor.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 套餐医生关联关系表
 * 
 * @author szr
 * 
 */
@Entity
@Table(name = "package_doctor")
public class PackageDoctorModel extends BaseModel {
	/* 医生id */
	private String doctorUuid;
	/* 套餐id */
	private String packageUuid;

	public void setDoctorUuid(String obj) {
		this.doctorUuid = obj;
	}

	public String getDoctorUuid() {
		return this.doctorUuid;
	}

	public void setPackageUuid(String obj) {
		this.packageUuid = obj;
	}

	public String getPackageUuid() {
		return this.packageUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[doctorUuid=" + this.getDoctorUuid() + ",packageUuid="
				+ this.getPackageUuid() + ",]";
	}
}

package com.aebiz.b2b2c.servicestaff.doctorimport.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.departmentinfo.service.DepartmentInfoService;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.service.HospitalInfoService;

@Entity
@Table(name = "doctor_import")
@Component
public class DoctorImportModel extends BaseModel {
	/* 注解医院service */
	@Transient
	private static HospitalInfoService hospitalInfoService;

	@Autowired
	// service层用这个
	public void setHospitalInfoService(HospitalInfoService hospitalInfoService) {
		this.hospitalInfoService = hospitalInfoService;
	}

	/* 注解科室service */
	@Transient
	private static DepartmentInfoService departmentInfoService;

	@Autowired
	// service层用这个
	public void setDepartmentInfoService(
			DepartmentInfoService departmentInfoService) {
		this.departmentInfoService = departmentInfoService;
	}

	/* 执业医院名称 */
	@Transient
	private String hospitalName = "";
	/* 执业科室名称 */
	@Transient
	private String departmentName = "";
	/* 医生Id */
	private String doctorId;

	/* 医生姓名 */
	private String realName;

	/* 手机号 */
	private String mobile;
	/* 电子邮箱 */
	private String email;
	/* 性别 */
	private String sex;

	/* 执业医院uuid */
	private String hospitalUuid;
	/* 执业科室uuid */
	private String departmentUuid;
	/* 创建时间 */
	private String createTime;
	/* 简介 */
	private String synopsis;
	/* 职称 */
	private String professional;
	/* 职务 */
	private String doctorClassify;

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getDoctorClassify() {
		return doctorClassify;
	}

	public void setDoctorClassify(String doctorClassify) {
		this.doctorClassify = doctorClassify;
	}

	public void setRealName(String obj) {
		this.realName = obj;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setMobile(String obj) {
		this.mobile = obj;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String obj) {
		this.email = obj;
	}

	public String getEmail() {
		return this.email;
	}

	public void setSex(String obj) {
		this.sex = obj;
	}

	public String getSex() {
		return this.sex;
	}

	public void setHospitalUuid(String obj) {
		this.hospitalUuid = obj;
	}

	public String getHospitalUuid() {
		return this.hospitalUuid;
	}

	public void setDepartmentUuid(String obj) {
		this.departmentUuid = obj;
	}

	public String getDepartmentUuid() {
		return this.departmentUuid;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getHospitalName() {
		if (!StringUtil.isEmpty(this.hospitalUuid)) {
			return hospitalInfoService.getHospitalNameByUuid(hospitalUuid);
		}
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDepartmentName() {

		if (!StringUtil.isEmpty(this.departmentUuid)) {
			return departmentInfoService
					.getDepartmentNameByUuid(departmentUuid);
		}
		return this.departmentName;

	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "DoctorImportModel [realName=" + realName + ", mobile=" + mobile
				+ ", email=" + email + ", sex=" + sex + ", hospitalUuid="
				+ hospitalUuid + ", departmentUuid=" + departmentUuid
				+ ", createTime=" + createTime + ", synopsis=" + synopsis
				+ ", professional=" + professional + ", doctorClassify="
				+ doctorClassify + "]";
	}

}

package com.aebiz.b2b2c.servicestaff.servicestaff.vo;

import java.util.List;

public class ServicestaffQueryModel extends ServicestaffModel {

	private List<String> doctorUuids;
	
	private String checkTags;

	/* 身份证号 */
	private String serviceStaffinfoCertCode = "";
	/* 等级名称 */
	private String serviceStaffLevelName = "";
	/* 性别 */
	private String serviceStaffinfoSex = "";
	/* 医院名称 */
	private String hospitaln = "";
	/* 培训计划编号 */
	private String planUuid;

	private String searchDate = "";

	private String startTime = "";

	private String endTime = "";

	private String type = "";

	private String moblie = "";

	private String serviceStaffName = "";

	private String status = "";

	private String certificationType = "";

	/* 省份code */
	private String province = "";

	/* 城市code */
	private String city = "";
	
	
	
        public String getHospitaln() {
        return hospitaln;
        }

        public void setHospitaln(String hospitaln) {
        this.hospitaln = hospitaln;
        }

        public String getCheckTags() {
        return checkTags;
        }

        public void setCheckTags(String checkTags) {
        this.checkTags = checkTags;
        }

        public List<String> getDoctorUuids() {
                return doctorUuids;
        }

        public void setDoctorUuids(List<String> doctorUuids) {
                this.doctorUuids = doctorUuids;
        }

        private String id2;

        public void setId2(String obj) {
                this.id2 = obj;
        }

        public String getId2() {
                return this.id2;
        }

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCertificationType() {
		return certificationType;
	}

	public void setCertificationType(String certificationType) {
		this.certificationType = certificationType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public String getServiceStaffName() {
		return serviceStaffName;
	}

	public void setServiceStaffName(String serviceStaffName) {
		this.serviceStaffName = serviceStaffName;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPlanUuid() {
		return planUuid;
	}

	public void setPlanUuid(String planUuid) {
		this.planUuid = planUuid;
	}

	public String getServiceStaffinfoSex() {
		return serviceStaffinfoSex;
	}

	public void setServiceStaffinfoSex(String serviceStaffinfoSex) {
		this.serviceStaffinfoSex = serviceStaffinfoSex;
	}

	public String getServiceStaffLevelName() {
		return serviceStaffLevelName;
	}

	public void setServiceStaffLevelName(String serviceStaffLevelName) {
		this.serviceStaffLevelName = serviceStaffLevelName;
	}

	public String getServiceStaffinfoCertCode() {
		return serviceStaffinfoCertCode;
	}

	public void setServiceStaffinfoCertCode(String serviceStaffinfoCertCode) {
		this.serviceStaffinfoCertCode = serviceStaffinfoCertCode;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[id2=" + this.getId2() + ",]";
	}
}

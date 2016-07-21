package com.hxq.mobile.entity.common;import java.util.List;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Transient;import com.wxcommon.repository.AbstractEntity;import com.wxcommon.util.ObjectUtils;/** * 医生表 */@SuppressWarnings("serial")@Entity(name = "service_staff")public class ServiceStaff extends AbstractEntity<String> {    /* 编号 */    @Id    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")    public String uuid;    @Column(columnDefinition="default 1")    public String delFlag;    public String opeTime;    public String oper;    /* 邀请码 */    public String activeCode;    /* 创建时间 */    public String createTime;    /* 档期日期【此日期是标示在工作台设置的开始工作还是休息的日期。此日期只设置当天有效。如果第二天的当天需要设置休息则更新此档期日期。】 */    public String date;    /* 推送设备标识 */    public String deviceToken;    /* 邮箱 */    public String email;    /* 冻结状态 0:未冻结 1：已冻结 */    @Column(columnDefinition="default 0")    public String frozenSate;    /* 类型 */    public String frozenTyp;    /* 左后登录错误时间 */    public String lastWrongLoginTime;    /* 登录错误次数 */    @Column(columnDefinition="default 0")    public String loginErrorTimes;    /* 手机号 */    public String mobile;    /* 登录密码 */    public String password;    /* 医生等级uuid */    public String serviceStaffLevel;    /* 用户名 */    public String serviceStaffName;    /* 账户金额 */    @Column(columnDefinition="default 0")    public Double accountAmount;    /* 可用金额 */    @Column(columnDefinition="default 0")    public Double availableAmount;    /* 押金 */    public String deposit;    /* 押金状态 */    public String depositState;    /* 押金时间 */    public String depositTime;    /* 审核状态 0待审核/未认证； 1审核通过/已认证； 2未通过审核/未通过认证；3认证中 */    @Column(columnDefinition="default 0")    public String sate;    /* 是否代付完成 0未完成 1完成 */    public String yonStatus;    /* 认证状态 */    @Column(columnDefinition="default 0")    public String certificationTyp;    /* 认证申请时间 */    public String certificationApplyTime;    /* 认证审核时间 */    public String certificationTime;    /* 认证审核原因 */    public String certificationNote;    /* 审核时间 */    public String auditTime;    /* 审核原因 */    public String auditNote;    /* 冻结时间 */    public String frozenTime;    /* 冻结原因 */    public String frozenNote;    /* 轮询时间 */    public String traversetime;    /* 状态 */    public String status;    /* 执业医院 */    public String hospital;    /*医院名称*/    @Transient    private String hospitalName;    /* 执业科室 */    public String department;	/* 执业科室名称 */	@Transient	private String departmentName;    /* 科室电话 */    public String departmentLine;    /* 真实姓名 */    public String realName;    public String serialNumber;    //名医风采排序字段    public Integer sort;	/* 医生擅长 */	@Transient	private String serviceStaffTerritory;	/* 医生职称 */	@Transient	private String professional;        /* 医生标签 */    public String doctorTag;	/* 标签名和id */	@Transient	private List<Tags> doctorTags;    /* 医生编号 流水号 */    public String doctorNo;    /* 医生类型 1是名医风采的类型 ； 2不是名医风采的类型 */    public String doctorType;    /* 注册状态 ，默认为0，0：未成功，1：成功 */    @Column(columnDefinition="default 0")    public String regState;    /* 医生总收入 */    @Column(columnDefinition="default 0")    public Double totalPrice;    /*服务次数*/    @Column(columnDefinition="default 0")    public Integer serviceTimes;     /*地址*/    @Transient    private String address;        public ServiceStaff() {super();}    public ServiceStaff(String uuid) {        super();        this.uuid = uuid;    }    @Override    public String getId() {        return uuid;    }    @Override    public void setId(String id) {        this.uuid = id;    }	public String getUuid() {		return uuid;	}	public void setUuid(String uuid) {		this.uuid = uuid;	}	public String getDelFlag() {		return delFlag;	}	public void setDelFlag(String delFlag) {		this.delFlag = delFlag;	}	public String getOpeTime() {		return opeTime;	}	public void setOpeTime(String opeTime) {		this.opeTime = opeTime;	}	public String getOper() {		return oper;	}	public void setOper(String oper) {		this.oper = oper;	}	public String getActiveCode() {		return activeCode;	}	public void setActiveCode(String activeCode) {		this.activeCode = activeCode;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getDate() {		return date;	}	public void setDate(String date) {		this.date = date;	}	public String getDeviceToken() {		return deviceToken;	}	public void setDeviceToken(String deviceToken) {		this.deviceToken = deviceToken;	}	public String getEmail() {		return email;	}	public void setEmail(String email) {		this.email = email;	}	public String getFrozenSate() {		return frozenSate;	}	public void setFrozenSate(String frozenSate) {		this.frozenSate = frozenSate;	}	public String getFrozenTyp() {		return frozenTyp;	}	public void setFrozenTyp(String frozenTyp) {		this.frozenTyp = frozenTyp;	}	public String getLastWrongLoginTime() {		return lastWrongLoginTime;	}	public void setLastWrongLoginTime(String lastWrongLoginTime) {		this.lastWrongLoginTime = lastWrongLoginTime;	}	public String getLoginErrorTimes() {		return loginErrorTimes;	}	public void setLoginErrorTimes(String loginErrorTimes) {		this.loginErrorTimes = loginErrorTimes;	}	public String getMobile() {		return mobile;	}	public void setMobile(String mobile) {		this.mobile = mobile;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public String getServiceStaffLevel() {		return serviceStaffLevel;	}	public void setServiceStaffLevel(String serviceStaffLevel) {		this.serviceStaffLevel = serviceStaffLevel;	}	public String getServiceStaffName() {		return serviceStaffName;	}	public void setServiceStaffName(String serviceStaffName) {		this.serviceStaffName = serviceStaffName;	}	public Double getAccountAmount() {		return accountAmount;	}	public void setAccountAmount(Double accountAmount) {		this.accountAmount = accountAmount;	}	public Double getAvailableAmount() {		return availableAmount;	}	public void setAvailableAmount(Double availableAmount) {		this.availableAmount = availableAmount;	}	public String getDeposit() {		return deposit;	}	public void setDeposit(String deposit) {		this.deposit = deposit;	}	public String getDepositState() {		return depositState;	}	public void setDepositState(String depositState) {		this.depositState = depositState;	}	public String getDepositTime() {		return depositTime;	}	public void setDepositTime(String depositTime) {		this.depositTime = depositTime;	}	public String getSate() {		return sate;	}	public void setSate(String sate) {		this.sate = sate;	}	public String getYonStatus() {		return yonStatus;	}	public void setYonStatus(String yonStatus) {		this.yonStatus = yonStatus;	}	public String getCertificationTyp() {		return certificationTyp;	}	public void setCertificationTyp(String certificationTyp) {		this.certificationTyp = certificationTyp;	}	public String getCertificationApplyTime() {		return certificationApplyTime;	}	public void setCertificationApplyTime(String certificationApplyTime) {		this.certificationApplyTime = certificationApplyTime;	}	public String getCertificationTime() {		return certificationTime;	}	public void setCertificationTime(String certificationTime) {		this.certificationTime = certificationTime;	}	public String getCertificationNote() {		return certificationNote;	}	public void setCertificationNote(String certificationNote) {		this.certificationNote = certificationNote;	}	public String getAuditTime() {		return auditTime;	}	public void setAuditTime(String auditTime) {		this.auditTime = auditTime;	}	public String getAuditNote() {		return auditNote;	}	public void setAuditNote(String auditNote) {		this.auditNote = auditNote;	}	public String getFrozenTime() {		return frozenTime;	}	public void setFrozenTime(String frozenTime) {		this.frozenTime = frozenTime;	}	public String getFrozenNote() {		return frozenNote;	}	public void setFrozenNote(String frozenNote) {		this.frozenNote = frozenNote;	}	public String getTraversetime() {		return traversetime;	}	public void setTraversetime(String traversetime) {		this.traversetime = traversetime;	}	public String getStatus() {		return status;	}	public void setStatus(String status) {		this.status = status;	}	public String getHospital() {		return hospital;	}	public void setHospital(String hospital) {		this.hospital = hospital;	}	public String getDepartment() {		return department;	}	public void setDepartment(String department) {		this.department = department;	}	public String getDepartmentLine() {		return departmentLine;	}	public void setDepartmentLine(String departmentLine) {		this.departmentLine = departmentLine;	}	public String getRealName() {		return realName;	}	public void setRealName(String realName) {		this.realName = realName;	}	public String getSerialNumber() {		return serialNumber;	}	public void setSerialNumber(String serialNumber) {		this.serialNumber = serialNumber;	}	public String getDoctorTag() {		return doctorTag;	}	public void setDoctorTag(String doctorTag) {		this.doctorTag = doctorTag;	}	public String getDoctorNo() {		return doctorNo;	}	public void setDoctorNo(String doctorNo) {		this.doctorNo = doctorNo;	}	public String getDoctorType() {		return doctorType;	}	public void setDoctorType(String doctorType) {		this.doctorType = doctorType;	}	public String getRegState() {		return regState;	}	public void setRegState(String regState) {		this.regState = regState;	}	public Double getTotalPrice() {		return totalPrice;	}	public void setTotalPrice(Double totalPrice) {		this.totalPrice = totalPrice;	}	public Integer getServiceTimes() {		return serviceTimes;	}	public void setServiceTimes(Integer serviceTimes) {		this.serviceTimes = serviceTimes;	}	public String getHospitalName() {		if(ObjectUtils.isEmpty(hospitalName) && !ObjectUtils.isEmpty(hospital) && hospital.equals("1")){			hospitalName = "其他医院";		}		return hospitalName;	}	public void setHospitalName(String hospitalName) {		this.hospitalName = hospitalName;	}	public String getDepartmentName() {		return departmentName;	}	public void setDepartmentName(String departmentName) {		this.departmentName = departmentName;	}	public String getServiceStaffTerritory() {		return serviceStaffTerritory;	}	public void setServiceStaffTerritory(String serviceStaffTerritory) {		this.serviceStaffTerritory = serviceStaffTerritory;	}	public String getProfessional() {		if(!ObjectUtils.isEmpty(professional)){			if(professional.equals("1")){				professional = "主任医师";			}else if(professional.equals("2")){				professional = "副主任医师";			}else if(professional.equals("3")){				professional = "主治医师";			}else if(professional.equals("4")){				professional = "住院医师";			}else if(professional.equals("5")){				professional = "助理医师";			}else if(professional.equals("6")){				professional = "实习医师";			}else if(professional.equals("7")){				professional = "护师（士）";	  		}else if(professional.equals("8")){	  			professional = "心理咨询师";	  		}else if(professional.equals("9")){	  			professional = "社工师";	  		}else if(professional.equals("10")){	  			professional = "药剂师";	  		}		}			return professional;	}			public Integer getSort() {		return sort;	}	public void setSort(Integer sort) {		this.sort = sort;	}	public void setProfessional(String professional) {		this.professional = professional;	}	public List<Tags> getDoctorTags() {		return doctorTags;	}		public void setDoctorTags(List<Tags> doctorTags) {		this.doctorTags = doctorTags;	}	public String getAddress() {		return address;	}	public void setAddress(String address) {		this.address = address;	}				}
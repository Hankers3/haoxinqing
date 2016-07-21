package com.aebiz.b2b2c.visitprecept.visitrecord.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;

/**
 * 随访记录表
 * 
 * @author wangbingning
 * 
 */
@Entity
@Table(name = "visit_record")
@Component
public class VisitRecordModel extends BaseModel {

	/* 注入会员service */
	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/* 注入患者service */
	@Transient
	private static CustomerInfoService customerInfoService;

	@Autowired
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}

	/* 注入随访方案service */
	@Transient
	private static VisitPreceptService visitPreceptService;

	@Autowired
	public void setVisitPreceptService(VisitPreceptService visitPreceptService) {
		this.visitPreceptService = visitPreceptService;
	}

	/* 注入医生service */
	@Transient
	private static ServicestaffService staffService;

	@Autowired
	public void setStaffService(ServicestaffService staffService) {
		this.staffService = staffService;
	}

	/* 状态 记录表里是否有病情变化等 0:没有，不可用 1有，可用 */
	private String visitState;

	/* 患者id */
	private String customerUuid;
	/* 会员名 */
	@Transient
	private String customerName;
	/* 医生编号 */
	private String serviceStaffUuid;
	/* 医生名 */
	@Transient
	private String doctorName;
	/* 随访方案编号 */
	private String preceptUuid;
	/* 创建时间 */
	private String createTime;
	/* 医生手机号 */
	@Transient
	private String doctorMobile;
	/* 患者ID */
	@Transient
	private String customerId;
	/* 医生ID */
	@Transient
	private String doctorNo;

	private String illnessRecord;

	/* 拒绝理由 */
	private String refuseReason;

	/* 随访申请状态 0:未查看 1：允许 2：拒绝 */
	private String applyState;
	

	public String getVisitState() {
		return visitState;
	}

	public void setVisitState(String visitState) {
		this.visitState = visitState;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	public String getIllnessRecord() {
		return illnessRecord;
	}

	public void setIllnessRecord(String illnessRecord) {
		this.illnessRecord = illnessRecord;
	}

	public String getDoctorNo() {
		if (!StringUtil.isEmpty(serviceStaffUuid)) {
			ServicestaffModel s = staffService.getByUuid(serviceStaffUuid);
			return s.getDoctorNo();
		}

		return doctorNo;
	}

	public void setDoctorNo(String doctorNo) {
		this.doctorNo = doctorNo;
	}

	public String getCustomerId() {
		if (!StringUtil.isEmpty(customerUuid)) {
			CustomerModel c = customerService.getByUuid(customerUuid);
			return c.getCustomerId();
		}
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/* 随访方案名称 */
	@Transient
	private String preceptName;

	public String getPreceptName() {
		if (!StringUtil.isEmpty(preceptUuid)) {
			return visitPreceptService.getPreceptNameByUUid(preceptUuid);
		}
		return preceptName;
	}

	public void setPreceptName(String preceptName) {
		this.preceptName = preceptName;
	}

	/* 随访方案 */
	@Transient
	private String customerMobile;

	public String getCustomerMobile() {
		if (!StringUtil.isEmpty(customerUuid)) {
			return customerService.getCustomerMobileByUuid(customerUuid);
		}
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getDoctorMobile() {
		if (!StringUtil.isEmpty(serviceStaffUuid)) {

			return staffService.getMobileByUuid(serviceStaffUuid);

		}

		return doctorMobile;
	}

	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}

	// public String getRealName() {
	// if(!StringUtil.isEmpty(customerUuid)){
	// CustomerInfoModel c = customerInfoService.getByUuid(customerUuid);
	// return c.getRealName();
	// }
	//
	// return realName;
	// }
	// public void setRealName(String realName) {
	//
	// this.realName = realName;
	// }
	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {

		return this.customerUuid;
	}

	public void setServiceStaffUuid(String obj) {
		this.serviceStaffUuid = obj;
	}

	public String getServiceStaffUuid() {
		return this.serviceStaffUuid;
	}

	public void setPreceptUuid(String obj) {
		this.preceptUuid = obj;
	}

	public String getPreceptUuid() {
		return this.preceptUuid;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String getCustomerName() {
		if (!StringUtil.isEmpty(customerUuid)) {
			return customerService.getCustomerNameByCustomerUuid(customerUuid);
		}
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDoctorName() {
		if (!StringUtil.isEmpty(serviceStaffUuid)) {
			return staffService.getServiceStaffNameByUuid(serviceStaffUuid);
		}
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName() + "[customerUuid=" + this.getCustomerUuid()
				+ ",serviceStaffUuid=" + this.getServiceStaffUuid() + ",preceptUuid=" + this.getPreceptUuid()
				+ ",createTime=" + this.getCreateTime() + ",]";
	}
}

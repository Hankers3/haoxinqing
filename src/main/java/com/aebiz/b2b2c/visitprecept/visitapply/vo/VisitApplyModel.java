package com.aebiz.b2b2c.visitprecept.visitapply.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;

/**
 * 随访申请表
 * 
 * @author szr
 * 
 */
@Entity
@Table(name = "visit_apply")
@Component
public class VisitApplyModel extends BaseModel {
	/* 注入患者service */
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

	/* 注入随访表单service */
	@Transient
	private static VisitRecordService visitRecordService;

	@Autowired
	public void setVisitRecordService(VisitRecordService visitRecordService) {
		this.visitRecordService = visitRecordService;
	}

	/* 注入医生service */
	@Transient
	private static ServicestaffService servicestaffService;

	@Autowired
	public void setStaffService(ServicestaffService servicestaffService) {
		this.servicestaffService = servicestaffService;
	}
	
	

	/* 患者编号 */
	private String customerUuid;

	/* 患者真实名 */
	@Transient
	private String customerName;

	/* 患者ID */
	@Transient
	private String customerNo;

	/* 医生编号 */
	private String serviceStaffUuid;

	/* 医生名 */
	@Transient
	private String doctorName;
	/* 医生ID */
	@Transient
	private String doctorNo;

	/* 创建时间 */
	private String createTime;
	/* 随访方案编号 */
	private String visitPreceptUuid;

	/* 随访方案名 */
	@Transient
	private String visitPreceptName;
	/* 拒绝理由 */
	private String refuseReason;
	/* 随访申请状态  0:未查看 1：允许 2：拒绝*/
	private String applyState;
	/* 随访表单编号 */
	private String visitRecordUuid;

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

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setVisitPreceptUuid(String obj) {
		this.visitPreceptUuid = obj;
	}

	public String getVisitPreceptUuid() {
		return this.visitPreceptUuid;
	}

	public void setRefuseReason(String obj) {
		this.refuseReason = obj;
	}

	public String getRefuseReason() {
		return this.refuseReason;
	}

	public void setApplyState(String obj) {
		this.applyState = obj;
	}

	public String getApplyState() {
		return this.applyState;
	}

	public void setVisitRecordUuid(String obj) {
		this.visitRecordUuid = obj;
	}

	public String getVisitRecordUuid() {
		return this.visitRecordUuid;
	}

	public String getCustomerName() {
		if (!StringUtil.isEmpty(this.customerUuid)) {
			return customerInfoService.getRealNameByUuid(customerUuid);
		}
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNo() {
		if (!StringUtil.isEmpty(this.customerUuid)) {
			return customerService.getCustomerNoByCustomerUuid(customerUuid);
		}
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getDoctorName() {
		if (!StringUtil.isEmpty(this.serviceStaffUuid)) {
			return servicestaffService
					.getServiceStaffNameByUuid(serviceStaffUuid);
		}
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorNo() {
		if (!StringUtil.isEmpty(this.serviceStaffUuid)) {
			return servicestaffService.getDoctorNoByUuid(serviceStaffUuid);
		}
		return doctorNo;
	}

	public void setDoctorNo(String doctorNo) {
		this.doctorNo = doctorNo;
	}

	public String getVisitPreceptName() {
		if (!StringUtil.isEmpty(this.visitPreceptUuid)) {
			return visitPreceptService.getPreceptNameByUUid(visitPreceptUuid);
		}
		return visitPreceptName;
	}

	public void setVisitPreceptName(String visitPreceptName) {
		this.visitPreceptName = visitPreceptName;
	}



	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[customerUuid=" + this.getCustomerUuid()
				+ ",serviceStaffUuid=" + this.getServiceStaffUuid()
				+ ",createTime=" + this.getCreateTime() + ",visitPreceptUuid="
				+ this.getVisitPreceptUuid() + ",refuseReason="
				+ this.getRefuseReason() + ",applyState="
				+ this.getApplyState() + ",visitRecordUuid="
				+ this.getVisitRecordUuid() + ",]";
	}
}

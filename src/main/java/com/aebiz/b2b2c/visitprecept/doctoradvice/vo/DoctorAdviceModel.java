package com.aebiz.b2b2c.visitprecept.doctoradvice.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 治疗方案
 * 
 * @author wangbingning
 * 
 */
@Entity
@Table(name = "doctor_advice")
@Component
public class DoctorAdviceModel extends BaseModel {

	/* 注入会员service */
	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/* 注入医生service */
	@Transient
	private static ServicestaffService staffService;

	@Autowired
	public void setStaffService(ServicestaffService staffService) {
		this.staffService = staffService;
	}

	/* 注入医生service */
	@Transient
	private static ProductMainService productMainService;

	@Autowired
	public void setProductMainService(ProductMainService productMainService) {
		this.productMainService = productMainService;
	}

	/* 患者编号 */
	private String customerUuid;

	/* 会员名 */
	@Transient
	private String customerName;

	/* 医生编号 */
	private String serviceStaffUuid;

	/* 医生名 */
	@Transient
	private String doctorName;

	/* 药物名称 */
	private String medicineUuid;

	/* 病例编号 */
	private String medicalRecordUuid;

	/* 随访表单 */
	private String visitRecordUuid;

	/* 单量 */
	private String dosage;
	
	/* 单位*/
	private String unit;

	/* 频率 */
	private String frequency;

	/* 用法 */
	private String directions;

	/* 类型 0药物治疗方案 1 其他治疗 */
	private String type;

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	/** 与食物关系 */
	private String food;

	/* 创建时间 */
	private String createTime;

	/* 药物的名称 */
	@Transient
	private String productName;

	/* 其他治疗方案 */
	private String cureNote;

	/*医嘱是否过期 0：未过期 1：已过期  */
	private String state;

	/* 服药记录起始时间 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date medicalDateBegin;
	/* 服药记录截止时间 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date medicalDateEnd;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}

	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
	}

	public String getProductName() {
		if (!StringUtil.isEmpty(medicineUuid)) {
			return productMainService.getProductNameByUuid(medicineUuid);
		}
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustomerName() {
		if (!StringUtil.isEmpty(customerUuid)) {
			return customerService
					.getCustomerNameByCustomerUuid(this.customerUuid);
		}
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDoctorName() {
		if (!StringUtil.isEmpty(this.serviceStaffUuid)) {
			return staffService
					.getServiceStaffNameByUuid(this.serviceStaffUuid);
		}
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String getServiceStaffUuid() {
		return serviceStaffUuid;
	}

	public void setServiceStaffUuid(String serviceStaffUuid) {
		this.serviceStaffUuid = serviceStaffUuid;
	}

	public String getMedicineUuid() {
		return medicineUuid;
	}

	public void setMedicineUuid(String medicineUuid) {
		this.medicineUuid = medicineUuid;
	}

	public String getMedicalRecordUuid() {
		return medicalRecordUuid;
	}

	public void setMedicalRecordUuid(String medicalRecordUuid) {
		this.medicalRecordUuid = medicalRecordUuid;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCureNote() {
		return cureNote;
	}

	public void setCureNote(String cureNote) {
		this.cureNote = cureNote;
	}

	public Date getMedicalDateBegin() {
		return medicalDateBegin;
	}

	public void setMedicalDateBegin(Date medicalDateBegin) {
		this.medicalDateBegin = medicalDateBegin;
	}

	public Date getMedicalDateEnd() {
		return medicalDateEnd;
	}

	public void setMedicalDateEnd(Date medicalDateEnd) {
		this.medicalDateEnd = medicalDateEnd;
	}

	@Override
	public String toString() {
		return "DoctorAdviceModel [customerUuid=" + customerUuid
				+ ", customerName=" + customerName + ", serviceStaffUuid="
				+ serviceStaffUuid + ", doctorName=" + doctorName
				+ ", medicineUuid=" + medicineUuid + ", medicalRecordUuid="
				+ medicalRecordUuid + ", dosage=" + dosage + ", frequency="
				+ frequency + ", directions=" + directions + ", type=" + type
				+ ", createTime=" + createTime + ", productName=" + productName
				+ ", cureNote=" + cureNote + "]";
	}

}

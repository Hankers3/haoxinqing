package com.hxq.mobile.entity.visit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxcommon.repository.AbstractEntity;

/**
 * 重要医嘱
 *
 */
@SuppressWarnings("serial")
@Entity(name="doctor_advice")
public class DoctorAdvice extends AbstractEntity<String> {
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	private String uuid;
	/* 创建时间 */
	@GeneratedValue(strategy=GenerationType.AUTO, generator="now")
	public Date createTime;
	/* 用法：早，中，晚 */
	private String directions;
	/*用量(按天)：40，30，20*/
	private String dosage;
	/*时间(天)：1，2，3*/
	private String frequency;
	/* 药物id */
	private String medicineUuid;
	/*类型 0药物治疗方案 1 其他治疗*/
	@Column(columnDefinition="default 0")
	private String type;
	/*药物单位：mg,ml,粒,袋*/
	private String unit;
	/*与食物关系：饭后服用，饭前服用，不与餐同服*/
	private String food;
	private String opeTime;
	/*mainUuid*/
	private String mainUuid;
	/*随访方案/随访记录id*/
	private String visitRecordUuid;
	/*患者id*/
	private String customerUuid;
	/*医生id*/
	private String serviceStaffUuid;
	/*服药记录起始时间*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date medicalDateBegin;
	/*服药记录截止时间*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date medicalDateEnd;

	public DoctorAdvice() {super();}
	public DoctorAdvice(String uuid, String mainUuid) {
		super();
		this.uuid = uuid;
		this.mainUuid = mainUuid;
	}
	@Override
	public String getId() {
		return uuid;
	}
	@Override
	public void setId(String id) {
		this.uuid = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
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
	public String getMedicineUuid() {
		return medicineUuid;
	}
	public void setMedicineUuid(String medicineUuid) {
		this.medicineUuid = medicineUuid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getMainUuid() {
		return mainUuid;
	}
	public void setMainUuid(String mainUuid) {
		this.mainUuid = mainUuid;
	}
	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}
	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
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

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
		return "DoctorAdvice [uuid=" + uuid + ", createTime=" + createTime + ", directions=" + directions + ", dosage="
				+ dosage + ", frequency=" + frequency + ", medicineUuid=" + medicineUuid + ", type=" + type + ", unit="
				+ unit + ", food=" + food + ", opeTime=" + opeTime + ", mainUuid=" + mainUuid + ", visitRecordUuid="
				+ visitRecordUuid + ", customerUuid=" + customerUuid + ", serviceStaffUuid=" + serviceStaffUuid
				+ ", medicalDateBegin=" + medicalDateBegin + ", medicalDateEnd=" + medicalDateEnd + "]";
	}
	
	
	
	
}

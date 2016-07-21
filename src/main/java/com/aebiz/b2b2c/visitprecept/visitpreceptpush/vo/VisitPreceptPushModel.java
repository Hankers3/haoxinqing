package com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * @author szr 2016/02/01 随访周期消息推送表
 */
@Entity
@Table(name = "visit_precept_push")
public class VisitPreceptPushModel extends BaseModel {
	/* 医生id */
	private String doctorUuid;
	/* 患者id */
	private String customerUuid;
	/* 方案id */
	private String visitPreceptUuid;
	/* 方案周期 */
	private String period;
	/* 推送时间 */
	private String pushTime;
	/* 创建时间 */
	private String createTime;
	/* 推送次数 */
	private int pushTimes;

	public String getDoctorUuid() {
		return doctorUuid;
	}

	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public void setVisitPreceptUuid(String obj) {
		this.visitPreceptUuid = obj;
	}

	public String getVisitPreceptUuid() {
		return this.visitPreceptUuid;
	}

	public void setPeriod(String obj) {
		this.period = obj;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPushTime(String obj) {
		this.pushTime = obj;
	}

	public String getPushTime() {
		return this.pushTime;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public int getPushTimes() {
		return pushTimes;
	}

	public void setPushTimes(int pushTimes) {
		this.pushTimes = pushTimes;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName() + "[visitPreceptUuid="
				+ this.getVisitPreceptUuid() + ",period=" + this.getPeriod() + ",pushTime=" + this.getPushTime()
				+ ",createTime=" + this.getCreateTime() + ",pushTimes=" + this.getPushTimes() + ",]";
	}
}

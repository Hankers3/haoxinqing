package com.aebiz.b2b2c.servicestaff.homevisitset.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 出诊时间设置表(以及加号设置表)
 * 
 * @author szr
 * 
 */
@Entity
@Table(name = "home_visit_set")
public class HomeVisitSetModel extends BaseModel {
	/* 关联的医生Uuid */
	private String doctorUuid;
	/* 日期 1:周一 2：周二 3：周三4：周四.. */
	private String weekDate;
	/* 时间类型 1：全天 ,2：上午,3：下午 */
	private String timeType;
	/* 状态 1：开通 2：停诊 */
	private String state;
	/* 是否提供预约加号状态 1：提供 2：不提供 */
	private String plusState;
	/* 加号人数 */
	private String plusNum;

	public String getPlusNum() {
		return plusNum;
	}

	public void setPlusNum(String plusNum) {
		this.plusNum = plusNum;
	}

	public String getPlusState() {
		return plusState;
	}

	public void setPlusState(String plusState) {
		this.plusState = plusState;
	}

	public void setDoctorUuid(String obj) {
		this.doctorUuid = obj;
	}

	public String getDoctorUuid() {
		return this.doctorUuid;
	}

	public void setWeekDate(String obj) {
		this.weekDate = obj;
	}

	public String getWeekDate() {
		return this.weekDate;
	}

	public void setTimeType(String obj) {
		this.timeType = obj;
	}

	public String getTimeType() {
		return this.timeType;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[doctorUuid=" + this.getDoctorUuid() + ",weekDate="
				+ this.getWeekDate() + ",timeType=" + this.getTimeType()
				+ ",state=" + this.getState() + ",]";
	}
}

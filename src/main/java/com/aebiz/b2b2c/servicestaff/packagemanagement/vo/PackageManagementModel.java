package com.aebiz.b2b2c.servicestaff.packagemanagement.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 后台用来设置医生的私人套餐信息
 * @author xueli
 *
 */
@Entity
@Table(name = "package_management")
public class PackageManagementModel extends BaseModel {
	
	/* 套餐名称 */
	private String packageName;
	/* 预约加号次数 */
	private int plusTimes;
	/* 电话咨询次数 */
	private int phoneTimes;
	/* 预约时长 */
	private String duration;
	/* 开始时间 */
	private String startTime;
	/* 结束时间 */
	private String endTime;
	/* 价格 */
	private double money;
	/* 创建时间 */
	private String createTime;
	/* 备注 */
	private String note;
	
	
	/*周期 0:月 1：季 2：年*/
	private String period;
	
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setPackageName(String obj) {
		this.packageName = obj;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPlusTimes(int obj) {
		this.plusTimes = obj;
	}

	public int getPlusTimes() {
		return this.plusTimes;
	}

	public void setPhoneTimes(int obj) {
		this.phoneTimes = obj;
	}

	public int getPhoneTimes() {
		return this.phoneTimes;
	}



	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setStartTime(String obj) {
		this.startTime = obj;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setEndTime(String obj) {
		this.endTime = obj;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setMoney(double obj) {
		this.money = obj;
	}

	public double getMoney() {
		return this.money;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[packageName=" + this.getPackageName() + ",plusTimes="
				+ this.getPlusTimes() + ",phoneTimes=" + this.getPhoneTimes()
				+ ",duration=" + this.getDuration() + ",startTime="
				+ this.getStartTime() + ",endTime=" + this.getEndTime()
				+ ",money=" + this.getMoney() + ",createTime="
				+ this.getCreateTime() + ",note=" + this.getNote() + ",]";
	}
}

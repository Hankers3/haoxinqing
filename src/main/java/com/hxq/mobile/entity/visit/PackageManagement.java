package com.hxq.mobile.entity.visit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 后台用来设置医生的私人套餐信息
 */
@SuppressWarnings("serial")
@Entity(name = "package_management")
public class PackageManagement extends AbstractEntity<String> {
    /* 编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    public String uuid;//申请医生随访id
    @Column(columnDefinition="default 1")
	public String delFlag;
	public String opeTime;
	public String oper;
	/* 创建时间 */
    public String createTime;
	/* 预约时长 */
    public String duration;
	/* 结束时间 */
    public String endTime;
	/* 价格 */
    @Column(columnDefinition="default 0")
    public Double money;
	/* 备注 */
    public String note;
    /* 套餐名称 */
    public String packageName;
	/* 电话咨询次数 */
    @Column(columnDefinition="default 0")
    public Integer phoneTimes;
    /* 预约加号次数 */
    @Column(columnDefinition="default 0")
    public Integer plusTimes;
	/* 开始时间 */
    public String startTime;
    public String length;
	/*周期 0:月 1：季 2：年*/
    public String period;

    public PackageManagement() {super();}
    public PackageManagement(String id) {
    	super();
    	this.uuid = id;
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
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Integer getPlusTimes() {
		return plusTimes;
	}
	public void setPlusTimes(Integer plusTimes) {
		this.plusTimes = plusTimes;
	}
	public Integer getPhoneTimes() {
		return phoneTimes;
	}
	public void setPhoneTimes(Integer phoneTimes) {
		this.phoneTimes = phoneTimes;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
}

package com.hxq.mobile.entity.visit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 健康指导
 */
@SuppressWarnings("serial")
@Entity(name="health_guide")
public class HealthGuide extends AbstractEntity<String> {
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	/* 用户编号 */
	public String customerUuid;
	/* 医生编号 */
	public String serviceStaffUuid;
	/* 饮食指导  */ 
	public String diet;
	/* 运动指导 */
	public String sports;
	/* 其他指导 (现在将这个字段改成建议指导,运动,饮食,睡眠 字段不设值)*/
	public String rest;
	/**
	 *推送周期
	 */
	@Column(columnDefinition="default 0")
	public Integer period;
	/**
	 * 睡眠指导
	 */
	public String sleep;
	/* 创建时间 */
	public String createTime;
	/* 备注 */
	public String note;
	/* 随访编号 */
	public String notevisitRecordUuid;
	
	public HealthGuide() {}
	public HealthGuide(String id) {
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
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public String getSports() {
		return sports;
	}
	public void setSports(String sports) {
		this.sports = sports;
	}
	public String getRest() {
		return rest;
	}
	public void setRest(String rest) {
		this.rest = rest;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getSleep() {
		return sleep;
	}
	public void setSleep(String sleep) {
		this.sleep = sleep;
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
	public String getNotevisitRecordUuid() {
		return notevisitRecordUuid;
	}
	public void setNotevisitRecordUuid(String notevisitRecordUuid) {
		this.notevisitRecordUuid = notevisitRecordUuid;
	}
}

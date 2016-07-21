package com.hxq.mobile.entity.visit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 病情记录
 *
 */
@SuppressWarnings("serial")
@Entity(name="illness_record")
public class IllnessRecord extends AbstractEntity<String> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	/* 创建时间 */
	@GeneratedValue(strategy=GenerationType.AUTO, generator="now")
	public Date createTime;
	/* 用户编号 */
	public String customerUuid;
	/* 新增病症 */
	public String newCondition;
	/* 病情变化描述 1 代表痊愈 2代表好转  3代表无效 4 代表其他 */
	public String previons;
	/* 医生编号 */
	public String serviceStaffUuid;
	/* 随访编号 */
	public String visitRecordUuid;

	public IllnessRecord() {super();}
	public IllnessRecord(String uuid, String visitRecordUuid) {
		super();
		this.uuid = uuid;
		this.visitRecordUuid = visitRecordUuid;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public String getNewCondition() {
		return newCondition;
	}
	public void setNewCondition(String newCondition) {
		this.newCondition = newCondition;
	}
	public String getPrevions() {
		return previons;
	}
	public void setPrevions(String previons) {
		this.previons = previons;
	}
	public String getServiceStaffUuid() {
		return serviceStaffUuid;
	}
	public void setServiceStaffUuid(String serviceStaffUuid) {
		this.serviceStaffUuid = serviceStaffUuid;
	}
	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}
	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
	}
}

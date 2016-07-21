package com.hxq.mobile.entity.visit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 随访记录扩展
 *
 */
@SuppressWarnings("serial")
@Entity(name="visit_record_extend")
public class VisitRecordExtend extends AbstractEntity<String> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	/* 创建时间 */
	@GeneratedValue(strategy=GenerationType.AUTO, generator="now")
	public Date opeTime;
	/* 患者ID */
	public String customerUuid;
	/* 名字 */
	public String name;
	/* 周期 */
	public String period;
	/* 描述 */
	public String result;
	/* 类型 */
	public String type;
	/* 随访编号 */
	public String visitRecordUuid;
	/* 状态描述 */
	public String state;
	/* 其它检查项 */
	public String preceptExtendUuid;
	/* 图像 */
	public String images;

	public VisitRecordExtend() {super();}
	public VisitRecordExtend(String uuid, String visitRecordUuid) {
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
	public Date getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(Date opeTime) {
		this.opeTime = opeTime;
	}
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}
	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPreceptExtendUuid() {
		return preceptExtendUuid;
	}
	public void setPreceptExtendUuid(String preceptExtendUuid) {
		this.preceptExtendUuid = preceptExtendUuid;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
}

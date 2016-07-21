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
@Entity(name="drug_reaction")
public class DrugReaction extends AbstractEntity<String> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	/* 创建时间 */
	@GeneratedValue(strategy=GenerationType.AUTO, generator="now")
	public Date createTime;
	/* 持续时间 */
	public String dosageTime;
	/* 症状描述 */
	public String frequency;
	/* 影响 */
	public String impact;
	/* 发生时间 */
	public String occurrenceTime;
	/* 随访编号 */
	public String visitRecordUuid;

	public DrugReaction() {super();}
	public DrugReaction(String uuid, String visitRecordUuid) {
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
	public String getDosageTime() {
		return dosageTime;
	}
	public void setDosageTime(String dosageTime) {
		this.dosageTime = dosageTime;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getOccurrenceTime() {
		return occurrenceTime;
	}
	public void setOccurrenceTime(String occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}
	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}
	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
	}
}

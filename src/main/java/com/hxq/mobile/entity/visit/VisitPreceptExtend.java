package com.hxq.mobile.entity.visit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 随访记录扩展表
 */
@SuppressWarnings("serial")
@Entity(name = "visit_record_extend")
public class VisitPreceptExtend extends AbstractEntity<String> {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    public String uuid;
	@Column(columnDefinition="default 1")
    public String delFlag;
    public String opeTime;
    public String oper;
    public String name;
    @Column(columnDefinition = "default 0")
    public Integer period;
    //随访方案id
    public String visitRecordUuid;
    public String result;
    public String type;
	/* 关联随访方案字段*/
    public String preceptExtendUuid;
	
    public VisitPreceptExtend() {super();}
    public VisitPreceptExtend(String uuid) {
		super();
		this.uuid = uuid;
	}
	@Override
	public String getId() {
		return uuid;
	}
	@Override
	public void setId(String id) {
		uuid = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}
	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
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
	public String getPreceptExtendUuid() {
		return preceptExtendUuid;
	}
	public void setPreceptExtendUuid(String preceptExtendUuid) {
		this.preceptExtendUuid = preceptExtendUuid;
	}
	
}
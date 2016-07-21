package com.hxq.mobile.entity.visit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 随访周期消息推送表
 */
@SuppressWarnings("serial")
@Entity(name = "visit_precept_push")
public class VisitPreceptPush extends AbstractEntity<String> {
    /* 编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    public String uuid;
    @Column(columnDefinition="default 1")
    public String delFlag;
    public String opeTime;
    public String oper;
    /* 医生id */
    public String doctorUuid;
    /* 患者id */
    public String customerUuid;
    /* 方案id */
    public String visitPreceptUuid;
    /* 方案周期 */
    public String period;
    /* 推送时间 */
    public String pushTime;
    /* 创建时间 */
    public String createTime;
    /* 推送次数 */
    @Column(columnDefinition = "default 0")
    public Integer pushTimes;

    public VisitPreceptPush() {super();}
    public VisitPreceptPush(String id) {
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
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpetime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
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
	public String getVisitPreceptUuid() {
		return visitPreceptUuid;
	}
	public void setVisitPreceptUuid(String visitPreceptUuid) {
		this.visitPreceptUuid = visitPreceptUuid;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getPushTime() {
		return pushTime;
	}
	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getPushTimes() {
		return pushTimes;
	}
	public void setPushTimes(Integer pushTimes) {
		this.pushTimes = pushTimes;
	}
}

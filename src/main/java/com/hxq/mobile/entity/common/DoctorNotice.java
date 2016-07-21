package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 医生公告
 *
 */
@SuppressWarnings("serial")
@Entity(name="doctor_notice")
public class DoctorNotice extends AbstractEntity<String> {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
    @Column(columnDefinition="default 1")
    public String delFlag;
    public String opeTime;
    public String oper;
    public String serviceStatffUuid;
    public String doctorName;
    public String line;
	/* 公告内容 */
    public String content;
    public String createTime;

	public DoctorNotice() {super();}
	public DoctorNotice(String uuid) {
		super();
		this.uuid = uuid;
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
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getServiceStatffUuid() {
		return serviceStatffUuid;
	}
	public void setServiceStatffUuid(String serviceStatffUuid) {
		this.serviceStatffUuid = serviceStatffUuid;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}

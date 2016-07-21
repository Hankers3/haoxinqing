package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 医生权限设置
 */
@SuppressWarnings("serial")
@Entity(name="doctor_right")
public class DoctorRight extends AbstractEntity<String> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	@Column(columnDefinition="default 1")
	public String delFlag;
	public String opeTime;
	public String oper;
	public String doctorUuid;
	@Column(columnDefinition="default 0")
	public String plus;
	@Column(columnDefinition="default 0")
	public String teletext;
	@Column(columnDefinition="default 0")
	public String phone;
	@Column(columnDefinition="default 0")
	public String personal;
	@Column(columnDefinition="default 0")
	public String disturb;
	public String note;
	public String type;
	@Column(columnDefinition="default 0")
	public String exam;
	public String refuseReason;
	@Column(columnDefinition="default 0")
    public String personalExam;
	@Column(columnDefinition="default 0")
    public String telDisturb;

	public DoctorRight() {super();}
	public DoctorRight(String uuid) {
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
	public String getDoctorUuid() {
		return doctorUuid;
	}
	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}
	public String getPlus() {
		return plus;
	}
	public void setPlus(String plus) {
		this.plus = plus;
	}
	public String getTeletext() {
		return teletext;
	}
	public void setTeletext(String teletext) {
		this.teletext = teletext;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public String getDisturb() {
		return disturb;
	}
	public void setDisturb(String disturb) {
		this.disturb = disturb;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getPersonalExam() {
		return personalExam;
	}
	public void setPersonalExam(String personalExam) {
		this.personalExam = personalExam;
	}
	public String getTelDisturb() {
		return telDisturb;
	}
	public void setTelDisturb(String telDisturb) {
		this.telDisturb = telDisturb;
	}
}
package com.aebiz.b2b2c.servicestaff.doctorright.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 医生权限表  用来给医生设置开通功能权限的记录信息
 * @author xueli
 *
 */
@Entity
@Table(name = "doctor_right")
public class DoctorRightModel extends BaseModel {
	/* 医生编号 */
	private String doctorUuid;

	/* 加号预约 0：未开通 1：开通 */
	private String plus;

	/* 图文咨询 0：未开通 1：开通 */
	private String teletext;

	/* 电话咨询 0：未开通 1：开通 */
	private String phone;

	/* 私人医生 0：未开通 1：开通 */
	private String personal;

	/* 免打扰 0：未开通 1：开通 */
	private String disturb;

	/* 图文免打扰 0：未开通 1：开通 */
	private String  telDisturb;
	
	/* 电话咨询托管审核 0：未开通 1：开通 */
	private String exam;
	/* 私人医生托管审核 0：未开通 1：开通 */
	private String personalExam;

	private String type;
	/* 备注 */
	private String note;
	
	
	public String getTelDisturb() {
		return telDisturb;
	}

	public void setTelDisturb(String telDisturb) {
		this.telDisturb = telDisturb;
	}

	public String getPersonalExam() {
		return personalExam;
	}

	public void setPersonalExam(String personalExam) {
		this.personalExam = personalExam;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public void setDoctorUuid(String obj) {
		this.doctorUuid = obj;
	}

	public String getDoctorUuid() {
		return this.doctorUuid;
	}

	public void setPlus(String obj) {
		this.plus = obj;
	}

	public String getPlus() {
		return this.plus;
	}

	public void setTeletext(String obj) {
		this.teletext = obj;
	}

	public String getTeletext() {
		return this.teletext;
	}

	public void setPhone(String obj) {
		this.phone = obj;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPersonal(String obj) {
		this.personal = obj;
	}

	public String getPersonal() {
		return this.personal;
	}

	public void setDisturb(String obj) {
		this.disturb = obj;
	}

	public String getDisturb() {
		return this.disturb;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName() + "[doctorUuid=" + this.getDoctorUuid()
				+ ",plus=" + this.getPlus() + ",teletext=" + this.getTeletext() + ",phone=" + this.getPhone()
				+ ",personal=" + this.getPersonal() + ",disturb=" + this.getDisturb() + ",note=" + this.getNote()
				+ ",]";
	}
}

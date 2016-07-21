package com.aebiz.b2b2c.visitprecept.casegroup.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 患者分组
 * @author szr
 *
 */
@Entity
@Table(name = "case_group")
public class CaseGroupModel extends BaseModel {
	
	/*医生编号*/
	private String doctorUuid;
	/* 分组名 */
	private String groupName;
	/* 备注 */
	private String note;
	/* 状态 */
	private String state;
	/* 创建时间 */
	private String createTime;

	public String getDoctorUuid() {
		return doctorUuid;
	}

	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}

	public void setGroupName(String obj) {
		this.groupName = obj;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[groupName=" + this.getGroupName() + ",note="
				+ this.getNote() + ",state=" + this.getState() + ",createTime="
				+ this.getCreateTime() + ",]";
	}
}

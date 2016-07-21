package com.aebiz.b2b2c.servicestaff.doctorcategory.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "doctor_category")
public class DoctorCategoryModel extends BaseModel {
	/* 分类名称 */
	private String categoryName;
	/* 分类备注 */
	private String note;
	/* 创建时间 */
	private String createTime;
	/* 状态 */
	private String state;

	public void setCategoryName(String obj) {
		this.categoryName = obj;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[categoryName=" + this.getCategoryName() + ",note="
				+ this.getNote() + ",createTime=" + this.getCreateTime()
				+ ",state=" + this.getState() + ",]";
	}
}

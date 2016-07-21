package com.aebiz.b2b2c.customermgr.customercategory.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 患者分类表
 * @author szr
 *
 */
@Entity
@Table(name = "customer_category")
public class CustomerCategoryModel extends BaseModel {
	/* 分类名 */
	private String categoryName;
	/* 备注 */
	private String note;
	/* 状态 */
	private String state;
	/* 创建时间 */
	private String createTime;

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
				+ "[categoryName=" + this.getCategoryName() + ",note="
				+ this.getNote() + ",state=" + this.getState() + ",createTime="
				+ this.getCreateTime() + ",]";
	}
}

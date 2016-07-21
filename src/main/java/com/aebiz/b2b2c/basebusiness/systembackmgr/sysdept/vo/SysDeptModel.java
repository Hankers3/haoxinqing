package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "sys_dept")
public class SysDeptModel extends BaseModel {

	/* 部门名称 */
	private String departmentName;

	/* 创建时间 */
	private String createTime;

	public void setDepartmentName(String obj) {
		this.departmentName = obj;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[departmentName=" + this.getDepartmentName()
				+ ",createTime=" + this.getCreateTime() + ",]";
	}
}

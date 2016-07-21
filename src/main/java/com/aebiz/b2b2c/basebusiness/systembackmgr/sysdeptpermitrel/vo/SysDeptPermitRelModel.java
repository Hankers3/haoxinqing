package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "sys_dept_permit_rel")
public class SysDeptPermitRelModel extends BaseModel {
	private String deptUuid;
	private String permitUuid;

	public void setDeptUuid(String obj) {
		this.deptUuid = obj;
	}

	public String getDeptUuid() {
		return this.deptUuid;
	}

	public void setPermitUuid(String obj) {
		this.permitUuid = obj;
	}

	public String getPermitUuid() {
		return this.permitUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[deptUuid=" + this.getDeptUuid() + ",permitUuid="
				+ this.getPermitUuid() + ",]";
	}
}

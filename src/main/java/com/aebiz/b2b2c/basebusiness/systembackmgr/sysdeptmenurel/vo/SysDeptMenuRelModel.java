package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "sys_dept_menu_rel")
public class SysDeptMenuRelModel extends BaseModel {

	/* 部门编号 */
	private String deptUuid;

	/* 菜单编号 */
	private String menuUuid;

	@Transient
	private String[] choosemenus;

	@Transient
	private String[] choosepermits;

	public void setDeptUuid(String obj) {
		this.deptUuid = obj;
	}

	public String getDeptUuid() {
		return this.deptUuid;
	}

	public void setMenuUuid(String obj) {
		this.menuUuid = obj;
	}

	public String getMenuUuid() {
		return this.menuUuid;
	}

	public String[] getChoosemenus() {
		return choosemenus;
	}

	public void setChoosemenus(String[] choosemenus) {
		this.choosemenus = choosemenus;
	}

	public String[] getChoosepermits() {
		return choosepermits;
	}

	public void setChoosepermits(String[] choosepermits) {
		this.choosepermits = choosepermits;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[deptUuid=" + this.getDeptUuid() + ",menuUuid="
				+ this.getMenuUuid() + ",]";
	}
}

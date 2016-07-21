package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "sys_role_menu_rel")
public class SysRoleMenuRelModel extends BaseModel {

	/* 菜单编号 */
	private String menuUuid;

	/* 角色编号 */
	private String roleUuid;

	@Transient
	private String[] choosemenus;

	@Transient
	private String[] choosepermits;

	public void setMenuUuid(String obj) {
		this.menuUuid = obj;
	}

	public String getMenuUuid() {
		return this.menuUuid;
	}

	public void setRoleUuid(String obj) {
		this.roleUuid = obj;
	}

	public String getRoleUuid() {
		return this.roleUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[menuUuid=" + this.getMenuUuid() + ",roleUuid="
				+ this.getRoleUuid() + ",]";
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
}

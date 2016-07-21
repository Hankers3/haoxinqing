package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "store_plat_role")
public class StorePlatRoleModel extends BaseModel {
	/* 角色编号 */
	private String id;

	/* 角色名称 */
	private String name;

	/* 创建时间 */
	private String createTime;

	/* 描述 */
	private String description;

	@Transient
	private String checked = "";

	public void setId(String obj) {
		this.id = obj;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String obj) {
		this.name = obj;
	}

	public String getName() {
		return this.name;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[id=" + this.getId() + ",name=" + this.getName()
				+ ",createTime=" + this.getCreateTime() + ",description="
				+ this.getDescription() + ",]";
	}
}

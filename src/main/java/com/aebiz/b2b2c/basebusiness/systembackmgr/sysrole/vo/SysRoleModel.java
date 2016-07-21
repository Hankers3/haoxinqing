package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.service.SysDeptService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Entity
@Table(name = "sys_role")
@Component
public class SysRoleModel extends BaseModel {
	
	/* 系统管理员角色 */
	public static final String SYSROEL = "SYSROLE";

	@Transient
	private static SysDeptService sysDeptService;

	@Autowired
	public void setMyService(SysDeptService bs) {
		this.sysDeptService = bs;
	}

	/* 角色编号 */
	private String id;

	/* 角色名称 */
	private String name;

	/* 创建时间 */
	private String createTime;

	/* 描述 */
	private String description;

	/* 所属部门 */
	private String deptUuid = "";

	@Transient
	private String deptName = "";

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

	public String getDeptUuid() {
		return deptUuid;
	}

	public void setDeptUuid(String deptUuid) {
		this.deptUuid = deptUuid;
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

	public String getDeptName() {
		if (!StringUtil.isEmpty(this.getDeptUuid())) {
			return sysDeptService.getDeptNameByDeptUuid(this.getDeptUuid());
		} else {
			return "";
		}
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}

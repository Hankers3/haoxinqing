package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="sys_manager_role_rel")
public class SysManagerRoleRelModel extends BaseModel{
	private String roleUuid;
	private String managerUuid;
	
	public void setRoleUuid(String obj){
		this.roleUuid = obj;
	}
	public String getRoleUuid(){
		return this.roleUuid;
	}
	
	public void setManagerUuid(String obj){
		this.managerUuid = obj;
	}
	public String getManagerUuid(){
		return this.managerUuid;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[roleUuid=" + this.getRoleUuid() + ",managerUuid=" + this.getManagerUuid() + ",]";
	}	
}

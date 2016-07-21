package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="sys_role_permit_rel")
public class SysRolePermitRelModel extends BaseModel{
	private String permitUuid;
	private String roleUuid;
	
	public void setPermitUuid(String obj){
		this.permitUuid = obj;
	}
	public String getPermitUuid(){
		return this.permitUuid;
	}
	
	public void setRoleUuid(String obj){
		this.roleUuid = obj;
	}
	public String getRoleUuid(){
		return this.roleUuid;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[permitUuid=" + this.getPermitUuid() + ",roleUuid=" + this.getRoleUuid() + ",]";
	}	
}

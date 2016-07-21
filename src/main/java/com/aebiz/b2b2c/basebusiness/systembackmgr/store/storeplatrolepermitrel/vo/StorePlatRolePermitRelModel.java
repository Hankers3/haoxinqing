package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="store_plat_role_permit_rel")
public class StorePlatRolePermitRelModel extends BaseModel{
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

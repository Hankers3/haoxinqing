package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="store_plat_role_menu_rel")
public class StorePlatRoleMenuRelModel extends BaseModel{
	private String menuUuid;
	private String roleUuid;
	
	public void setMenuUuid(String obj){
		this.menuUuid = obj;
	}
	public String getMenuUuid(){
		return this.menuUuid;
	}
	
	public void setRoleUuid(String obj){
		this.roleUuid = obj;
	}
	public String getRoleUuid(){
		return this.roleUuid;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[menuUuid=" + this.getMenuUuid() + ",roleUuid=" + this.getRoleUuid() + ",]";
	}	
}

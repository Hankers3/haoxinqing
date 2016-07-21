package com.aebiz.b2b2c.servicestaff.staffloginstatus.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="staff_login_status")
public class StaffLoginStatusModel extends BaseModel{
	
	/*家政员编号*/
	private String serviceStaffUuid;
	
	/*家政员登录状态    0 未登录       1 已登录*/
	private String status;
	
	/*创建时间*/
	private String createTime;
	
	/*上次登录时间*/
	private String lastLoginTime;
	
	/*上次退出时间*/
	private String lastOutOfTime;
	
	/*本次登录时间*/
	private String theLoginTime;
	
	public String getLastOutOfTime() {
		return lastOutOfTime;
	}
	public void setLastOutOfTime(String lastOutOfTime) {
		this.lastOutOfTime = lastOutOfTime;
	}
	public String getTheLoginTime() {
		return theLoginTime;
	}
	public void setTheLoginTime(String theLoginTime) {
		this.theLoginTime = theLoginTime;
	}
	public void setServiceStaffUuid(String obj){
		this.serviceStaffUuid = obj;
	}
	public String getServiceStaffUuid(){
		return this.serviceStaffUuid;
	}
	
	public void setStatus(String obj){
		this.status = obj;
	}
	public String getStatus(){
		return this.status;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setLastLoginTime(String obj){
		this.lastLoginTime = obj;
	}
	public String getLastLoginTime(){
		return this.lastLoginTime;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[serviceStaffUuid=" + this.getServiceStaffUuid() + ",status=" + this.getStatus() + ",createTime=" + this.getCreateTime() + ",lastLoginTime=" + this.getLastLoginTime() + ",]";
	}	
}

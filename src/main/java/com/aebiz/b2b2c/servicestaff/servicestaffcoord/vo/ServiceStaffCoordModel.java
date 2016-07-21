package com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="service_staff_coord")
public class ServiceStaffCoordModel extends BaseModel{
	
	/*家政员编号*/
	private String serviceStaffUuid;
	
	/*状态*/
	private String status;
	
	/*创建时间*/
	private String createTime;
	
	/*上传时间*/
	private String updateTime;
	
	/*经度*/
	private double longitude;
	
	/*纬度*/
	private double latitude;
	
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
	
	public void setUpdateTime(String obj){
		this.updateTime = obj;
	}
	public String getUpdateTime(){
		return this.updateTime;
	}
	
	public void setLongitude(double obj){
		this.longitude = obj;
	}
	public double getLongitude(){
		return this.longitude;
	}
	
	public void setLatitude(double obj){
		this.latitude = obj;
	}
	public double getLatitude(){
		return this.latitude;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[serviceStaffUuid=" + this.getServiceStaffUuid() + ",status=" + this.getStatus() + ",createTime=" + this.getCreateTime() + ",updateTime=" + this.getUpdateTime() + ",longitude=" + this.getLongitude() + ",latitude=" + this.getLatitude() + ",]";
	}	
}

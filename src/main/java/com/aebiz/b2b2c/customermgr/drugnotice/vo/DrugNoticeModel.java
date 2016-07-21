package com.aebiz.b2b2c.customermgr.drugnotice.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * @Description: (服药提醒的表)    
 * @author XP  
 * @date 2016-1-13 下午2:10:13
 */
@Entity
@Table(name="drug_notice")
public class DrugNoticeModel extends BaseModel{
        /*药品名称*/
	private String medicineName;
	/*患者的id*/
	private String customerUuid;
	/*服药计量*/
	private String dosage;
	/*服药次数*/
	private String frequency;
	/*用药指导*/
	private String directions;
	/*提醒时间*/
	private String noticeTime;
	/*创建时间*/
	private String createTime;
	
	public void setMedicineName(String obj){
		this.medicineName = obj;
	}
	public String getMedicineName(){
		return this.medicineName;
	}
	
	public void setCustomerUuid(String obj){
		this.customerUuid = obj;
	}
	public String getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setDosage(String obj){
		this.dosage = obj;
	}
	public String getDosage(){
		return this.dosage;
	}
	
	public void setFrequency(String obj){
		this.frequency = obj;
	}
	public String getFrequency(){
		return this.frequency;
	}
	
	public void setDirections(String obj){
		this.directions = obj;
	}
	public String getDirections(){
		return this.directions;
	}
	
	public void setNoticeTime(String obj){
		this.noticeTime = obj;
	}
	public String getNoticeTime(){
		return this.noticeTime;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[medicineName=" + this.getMedicineName() + ",customerUuid=" + this.getCustomerUuid() + ",dosage=" + this.getDosage() + ",frequency=" + this.getFrequency() + ",directions=" + this.getDirections() + ",noticeTime=" + this.getNoticeTime() + ",createTime=" + this.getCreateTime() + ",]";
	}	
}

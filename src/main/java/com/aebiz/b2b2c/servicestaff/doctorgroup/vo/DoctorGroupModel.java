package com.aebiz.b2b2c.servicestaff.doctorgroup.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 医圈表
 * @author wangbingning
 */
@Entity
@Table(name="doctor_group")
public class DoctorGroupModel extends BaseModel{
	/*医生类别*/
	private String classifyUuid;
	/*类别名称*/
	private String classifyName;
	/*医生编号*/
	private String doctorUuid;
	/*医生名称*/
	private String doctorName;
	/*医圈名称*/
	private String groupName;
	/*创建时间*/
	private String createTime;
	/*备注*/
	private String note;
	
	public void setClassifyUuid(String obj){
		this.classifyUuid = obj;
	}
	public String getClassifyUuid(){
		return this.classifyUuid;
	}
	
	public void setClassifyName(String obj){
		this.classifyName = obj;
	}
	public String getClassifyName(){
		return this.classifyName;
	}
	
	public void setDoctorUuid(String obj){
		this.doctorUuid = obj;
	}
	public String getDoctorUuid(){
		return this.doctorUuid;
	}
	
	public void setDoctorName(String obj){
		this.doctorName = obj;
	}
	public String getDoctorName(){
		return this.doctorName;
	}
	
	public void setGroupName(String obj){
		this.groupName = obj;
	}
	public String getGroupName(){
		return this.groupName;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[classifyUuid=" + this.getClassifyUuid() + ",classifyName=" + this.getClassifyName() + ",doctorUuid=" + this.getDoctorUuid() + ",doctorName=" + this.getDoctorName() + ",groupName=" + this.getGroupName() + ",createTime=" + this.getCreateTime() + ",note=" + this.getNote() + ",]";
	}	
}

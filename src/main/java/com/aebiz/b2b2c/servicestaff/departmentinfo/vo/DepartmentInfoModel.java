package com.aebiz.b2b2c.servicestaff.departmentinfo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 科室表信息  用來添加管理醫生科室信息
 * @author xueli
 *
 */
@Entity
@Table(name="department_info")
public class DepartmentInfoModel extends BaseModel{
	/*科室名称*/
	private String departmentName;
	
	/*科室电话*/
	private String mobile;
	
	/*创建时间*/
	private String createTime;
	
	/*科室ID*/
	private String departmentId;

        /*备注*/
	private String note;
	
	public String getDepartmentId() {
	        return departmentId;
	    }
	public void setDepartmentId(String departmentId) {
	     this.departmentId = departmentId;
	    }
	
	public void setDepartmentName(String obj){
		this.departmentName = obj;
	}
	public String getDepartmentName(){
		return this.departmentName;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
		return super.toString()+" , Model"+this.getClass().getName()+"[departmentName=" + this.getDepartmentName() + ",createTime=" + this.getCreateTime() + ",note=" + this.getNote() + ",]";
	}	
}

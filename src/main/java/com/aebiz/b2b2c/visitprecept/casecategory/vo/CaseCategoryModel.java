package com.aebiz.b2b2c.visitprecept.casecategory.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 病例分类
 * @author szr
 *
 */
@Entity
@Table(name="case_category")
public class CaseCategoryModel extends BaseModel{
	/* 病例分类名 */
	private String categoryName;
	/* 备注 */
	private String note;
	/* 状态 */
	private String state;
	/* 创建时间 */
	private String createTime;
	/* 分组名 */
	private String visitRecordUuid;
	
	public void setCategoryName(String obj){
		this.categoryName = obj;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setVisitRecordUuid(String obj){
		this.visitRecordUuid = obj;
	}
	public String getVisitRecordUuid(){
		return this.visitRecordUuid;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[categoryName=" + this.getCategoryName() + ",note=" + this.getNote() + ",state=" + this.getState() + ",createTime=" + this.getCreateTime() + ",visitRecordUuid=" + this.getVisitRecordUuid() + ",]";
	}	
}

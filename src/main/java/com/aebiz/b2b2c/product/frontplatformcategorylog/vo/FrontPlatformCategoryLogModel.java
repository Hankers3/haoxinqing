package com.aebiz.b2b2c.product.frontplatformcategorylog.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="front_platform_category_log")
public class FrontPlatformCategoryLogModel extends BaseModel{
	public static final String IS_SAME = "1";
	public static final String NO_SAME = "0";
	
	public static final String ADD = "1";
	public static final String UPDATE = "2";
	public static final String DELETE = "3";
	
	private String frontCategoryuuid;
	private String platformcategoryUuid;
	private String type;
	private String same;
	
	public void setFrontCategoryuuid(String obj){
		this.frontCategoryuuid = obj;
	}
	public String getFrontCategoryuuid(){
		return this.frontCategoryuuid;
	}
	
	public void setPlatformcategoryUuid(String obj){
		this.platformcategoryUuid = obj;
	}
	public String getPlatformcategoryUuid(){
		return this.platformcategoryUuid;
	}
	
	public void setType(String obj){
		this.type = obj;
	}
	public String getType(){
		return this.type;
	}
	
	public void setSame(String obj){
		this.same = obj;
	}
	public String getSame(){
		return this.same;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[frontCategoryuuid=" + this.getFrontCategoryuuid() + ",platformcategoryUuid=" + this.getPlatformcategoryUuid() + ",type=" + this.getType() + ",same=" + this.getSame() + ",]";
	}	
}

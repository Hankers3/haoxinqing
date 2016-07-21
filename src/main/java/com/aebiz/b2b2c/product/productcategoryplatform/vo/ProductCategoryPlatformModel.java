package com.aebiz.b2b2c.product.productcategoryplatform.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 平台分类实体类
 * @author huangpinpin
 *
 */
@Entity
@Table(name="product_category_platform")
public class ProductCategoryPlatformModel extends BaseModel{
	
	/* 分类编号*/
	private String categoryNo;
	
	/* 分类名称*/
	private String categoryName;
	
	/* 分类位置*/
	private int position;
	
	/* 分类备注*/
	private String categoryNote;
	
	/* 分类状态*/
	private String state;
	
	/* 分类父类uuid*/
	private String parentUuid;
	
	/* 分类fullpath*/
	private String fullpath;
	
	/* 标签*/
	private String tagValue;
	
	/* 分类链接*/
	private String categoryUrl;
	
	/* 标签分类uuid*/
	private String tagCategoryUuid;
	
	
	public void setTagCategoryUuid(String tagCategoryUuid) {
		this.tagCategoryUuid = tagCategoryUuid;
	}
	public String getTagCategoryUuid() {
		return tagCategoryUuid;
	}
	
	public void setCategoryNo(String obj){
		this.categoryNo = obj;
	}
	public String getCategoryNo(){
		return this.categoryNo;
	}
	
	public void setCategoryName(String obj){
		this.categoryName = obj;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
	
	public void setPosition(int obj){
		this.position = obj;
	}
	public int getPosition(){
		return this.position;
	}
	
	public void setCategoryNote(String obj){
		this.categoryNote = obj;
	}
	public String getCategoryNote(){
		return this.categoryNote;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	public void setParentUuid(String obj){
		this.parentUuid = obj;
	}
	public String getParentUuid(){
		return this.parentUuid;
	}
	
	public void setFullpath(String obj){
		this.fullpath = obj;
	}
	public String getFullpath(){
		return this.fullpath;
	}
	
	public void setTagValue(String obj){
		this.tagValue = obj;
	}
	public String getTagValue(){
		return this.tagValue;
	}
	
	public void setCategoryUrl(String obj){
		this.categoryUrl = obj;
	}
	public String getCategoryUrl(){
		return this.categoryUrl;
	}
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[categoryNo=" + this.getCategoryNo() + ",categoryName=" + this.getCategoryName() + ",position=" + this.getPosition() + ",categoryNote=" + this.getCategoryNote() + ",state=" + this.getState() + ",parentUuid=" + this.getParentUuid() + ",fullpath=" + this.getFullpath() + ",tagValue=" + this.getTagValue() + ",categoryUrl=" + this.getCategoryUrl() + ",]";
	}	
}

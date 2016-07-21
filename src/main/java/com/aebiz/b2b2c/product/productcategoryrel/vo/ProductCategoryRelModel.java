package com.aebiz.b2b2c.product.productcategoryrel.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="product_category_rel")
public class ProductCategoryRelModel extends BaseModel{
	/* 前台分类uuid*/
	private String frontCategoryUuid;
	/* 后台分类uuid*/
	private String platCategoryUuid;
	
	public void setFrontCategoryUuid(String obj){
		this.frontCategoryUuid = obj;
	}
	public String getFrontCategoryUuid(){
		return this.frontCategoryUuid;
	}
	
	public void setPlatCategoryUuid(String obj){
		this.platCategoryUuid = obj;
	}
	public String getPlatCategoryUuid(){
		return this.platCategoryUuid;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[frontCategoryUuid=" + this.getFrontCategoryUuid() + ",platCategoryUuid=" + this.getPlatCategoryUuid() + ",]";
	}	
}

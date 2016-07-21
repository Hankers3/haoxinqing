package com.aebiz.b2b2c.product.productimagecategory.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * 店铺商品图片分类
 * 
 * @author huangpinpin
 *
 */
@Entity
@Table(name="product_image_category")
public class ProductImageCategoryModel extends BaseModel{
	
	/* 分类名称*/
	private String categoryName;
	/* 位置*/
	private int position;
	/* 上级编号*/
	private String parentUuid;
	/* 所属商户*/
	private String storeUuid;
	/* fullpath*/
	private String fullPath;
	
	
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getFullPath() {
		return fullPath;
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
	
	public void setParentUuid(String obj){
		this.parentUuid = obj;
	}
	public String getParentUuid(){
		return this.parentUuid;
	}
	
	public void setStoreUuid(String obj){
		this.storeUuid = obj;
	}
	public String getStoreUuid(){
		return this.storeUuid;
	}
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[categoryName=" + this.getCategoryName() + ",position=" + this.getPosition() + ",parentUuid=" + this.getParentUuid() + ",storeUuid=" + this.getStoreUuid() + ",]";
	}
}

package com.aebiz.b2b2c.customermgr.customercategory.vo;

public class CustomerCategoryQueryModel extends CustomerCategoryModel {
	private String categoryName;
	
	public void setCategoryName(String obj){
		this.categoryName = obj;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[categoryName=" + this.getCategoryName() + ",]";
	}
}

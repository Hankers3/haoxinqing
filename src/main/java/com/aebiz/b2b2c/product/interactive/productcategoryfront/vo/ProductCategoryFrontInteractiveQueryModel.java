package com.aebiz.b2b2c.product.interactive.productcategoryfront.vo;

public class ProductCategoryFrontInteractiveQueryModel extends ProductCategoryFrontInteractiveModel {

	private String minTime;
	private String maxTime;
	
	public void setMinTime(String obj){
		this.minTime = obj;
	}
	public String getMinTime(){
		return this.minTime;
	}
	
	public void setMaxTime(String obj){
		this.maxTime = obj;
	}
	public String getMaxTime(){
		return this.maxTime;
	}
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[minTime=" + this.getMinTime() + ",maxTime=" + this.getMaxTime() + ",]";
	}
}

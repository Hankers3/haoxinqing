package com.aebiz.b2b2c.websiteoperation.quizcategory.vo;

public class QuizCategoryQueryModel extends QuizCategoryModel {
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

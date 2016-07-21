package com.aebiz.b2b2c.cms.sysback.web.contentcategory.vo;

public class SelectCategoryModel {
	/* 分类uuid*/
	private String categoryUuid="";
	/* 父类uuid*/
	private String parentUuid="";
	/* 分类名称*/
	private String categoryName="";
	/* 是否选中*/
	private String selected="";

	public String getCategoryUuid() {
		return categoryUuid;
	}
	public void setCategoryUuid(String categoryUuid) {
		this.categoryUuid = categoryUuid;
	}
	public String getParentUuid() {
		return parentUuid;
	}
	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getSelected() {
		return selected;
	}
	
}

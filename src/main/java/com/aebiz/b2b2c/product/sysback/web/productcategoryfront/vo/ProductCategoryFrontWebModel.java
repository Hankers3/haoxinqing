package com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateModel;

public class ProductCategoryFrontWebModel extends BaseWebModel{
	
	/* 前台分类uuid*/
	private String frontCategoryUuid; 
	
	/* 属性和属性值列表展示*/
	private List<AttributeAndValueWebModel> attributes;
	
	
	public void setFrontCategoryUuid(String frontCategoryUuid) {
		this.frontCategoryUuid = frontCategoryUuid;
	}
	public String getFrontCategoryUuid() {
		return frontCategoryUuid;
	}
	
	public void setAttributes(List<AttributeAndValueWebModel> attributes) {
		this.attributes = attributes;
	}
	public List<AttributeAndValueWebModel> getAttributes() {
		return attributes;
	}
	
	 
}
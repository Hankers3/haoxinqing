package com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo;

public class AttributeValueWebModel {
	
	/* 属性值名称*/
	private String valueName;
	
	/* 是否选中*/
	private String select;
	
	
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	
	public void setSelect(String select) {
		this.select = select;
	}
	public String getSelect() {
		return select;
	}
}

package com.aebiz.b2b2c.product.producttemplateattr.vo;

import java.util.List;

public class ProductTemplateAttrQueryModel extends ProductTemplateAttrModel {
	private List<String> attributeUuids;
	
	public List<String> getAttributeUuids() {
		return attributeUuids;
	}
	public void setAttributeUuids(List<String> attributeUuids) {
		this.attributeUuids = attributeUuids;
	}
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}

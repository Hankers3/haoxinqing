package com.aebiz.b2b2c.product.producttemplate.vo;

import java.util.List;

public class ProductTemplateQueryModel extends ProductTemplateModel {
	/* 创建时间结束*/
	private String createTime2;
	
	private List<String> templateUuids;
	
	public void setCreateTime2(String obj){
		this.createTime2 = obj;
	}
	public String getCreateTime2(){
		return this.createTime2;
	}
	
	public List<String> getTemplateUuids() {
		return templateUuids;
	}
	public void setTemplateUuids(List<String> templateUuids) {
		this.templateUuids = templateUuids;
	}
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[createTime2=" + this.getCreateTime2() + ",]";
	}
}

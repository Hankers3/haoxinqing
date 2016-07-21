package com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo;

public class FrontProductCategoryBrandRelQueryModel extends
		FrontProductCategoryBrandRelModel {
	private String minTime;

	public void setMinTime(String obj) {
		this.minTime = obj;
	}

	public String getMinTime() {
		return this.minTime;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[minTime=" + this.getMinTime() + ",]";
	}
}

package com.aebiz.b2b2c.cms.productappraise.vo;

import java.util.ArrayList;
import java.util.List;

public class ProductAppraiseQueryModel extends ProductAppraiseModel {
	/* 商品名称 */
	private String productName = "";
	
	/* 商品id集合 */
	private List<String> productIds = null;
	
	/* 评价时间结束 */
	private String appTime2;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public List<String> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}

	public void setAppTime2(String obj) {
		this.appTime2 = obj;
	}

	public String getAppTime2() {
		return this.appTime2;
	}
}

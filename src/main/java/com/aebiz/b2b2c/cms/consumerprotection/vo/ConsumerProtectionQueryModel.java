package com.aebiz.b2b2c.cms.consumerprotection.vo;

public class ConsumerProtectionQueryModel extends ConsumerProtectionModel {

	/*商品的编号*/
	private String productNo;

	/*商品名称*/
	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
}

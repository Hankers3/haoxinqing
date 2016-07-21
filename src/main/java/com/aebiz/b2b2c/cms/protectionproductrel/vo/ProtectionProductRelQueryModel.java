package com.aebiz.b2b2c.cms.protectionproductrel.vo;

public class ProtectionProductRelQueryModel extends ProtectionProductRelModel {

	/*商品id*/
	private String productUuid;

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	public String getProductUuid() {
		return this.productUuid;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[productUuid=" + this.getProductUuid() + ",]";
	}
}

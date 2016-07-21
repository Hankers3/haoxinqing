package com.aebiz.b2b2c.cms.storeback.web.consumerprotection.vo;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

public class ConsumerProductWebModel extends BaseWebModel{
	/*商品id*/
	private String productUuid;
	
	/*商品编号*/
	private String ProductNo;
	
	/*商品名称*/
	private String productName; 
	
	/*商品价格*/
	private double shopPrice;
	
	/*权益的id*/
	private String protectionUuid;
	
	/*商户id*/
	private String StoreUuid;
	
	/*商品权益的id*/
	private String uuid;

	public String getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getProtectionUuid() {
		return protectionUuid;
	}

	public void setProtectionUuid(String protectionUuid) {
		this.protectionUuid = protectionUuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProductNo() {
		return ProductNo;
	}

	public void setProductNo(String productNo) {
		ProductNo = productNo;
	}

	public String getStoreUuid() {
		return StoreUuid;
	}

	public void setStoreUuid(String storeUuid) {
		StoreUuid = storeUuid;
	}
}

package com.aebiz.b2b2c.product.interactive.product.vo;

public class ProductInteractiveQueryModel extends ProductInteractiveModel{
	/* 大于商城价*/
	private String shopPrice;
	/* 小于商城价*/
	private String shopPrice1;
	/* 上架结束时间*/
	private String shelvesTime1;
	/* 商户uuid*/
	private String storeUuid;
	/* 商品uuid集合*/
	private String[] productUuids;
	/* 商品编号-not in*/
	private String productNo;
	/* 商品名称*/
	private String productName;
	/* 商品编号-in*/
	private String[] inProductUuids;
	
	public void setInProductUuids(String[] inProductUuids) {
		this.inProductUuids = inProductUuids;
	}
	
	public String[] getInProductUuids() {
		return inProductUuids;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	public String getProductNo() {
		return productNo;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductUuids(String[] productUuids) {
		this.productUuids = productUuids;
	}
	
	public String[] getProductUuids() {
		return productUuids;
	}
	
	public void setStoreUuid(String storeUuid) {
		this.storeUuid = storeUuid;
	}
	
	public String getStoreUuid() {
		return storeUuid;
	}
	
	public void setShopPrice1(String shopPrice1) {
		this.shopPrice1 = shopPrice1;
	}
	
	public String getShopPrice1() {
		return shopPrice1;
	}
	 
	public void setShopPrice(String shopPrice) {
		this.shopPrice = shopPrice;
	}
	
	public String getShopPrice() {
		return shopPrice;
	}
	
	public void setShelvesTime1(String shelvesTime1) {
		this.shelvesTime1 = shelvesTime1;
	}
	
	public String getShelvesTime1() {
		return shelvesTime1;
	}
}

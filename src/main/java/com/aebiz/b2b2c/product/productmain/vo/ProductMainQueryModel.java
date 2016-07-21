package com.aebiz.b2b2c.product.productmain.vo;

public class ProductMainQueryModel extends ProductMainModel {
	/* 大于商城价*/
	private String shopPrice;
	/* 小于商城价*/
	private String shopPrice1;
	/* 上架结束时间*/
	private String shelvesTime1;
	
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

	@Override
	public String toString() {
		return super.toString()+  "ProductMainQueryModel [shopPrice=" + shopPrice
				+ ", shopPrice1=" + shopPrice1 + "]";
	}
	
	
}

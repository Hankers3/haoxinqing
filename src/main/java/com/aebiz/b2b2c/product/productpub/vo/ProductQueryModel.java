package com.aebiz.b2b2c.product.productpub.vo;

/**
 * 商品查询条件
 * 
 * 
 * @author huangpinpin
 *
 */
public class ProductQueryModel extends ProductModel {
	
	/* 商品名称*/
	private String productName;
	/* 商品英文名称*/
	private String productEnglishName;

        /* 商品编号*/
	private String productNo;
	
	/* 大于商城价*/
	private String shopPrice;
	
	/* 小于商城价*/
	private String shopPrice1;
	
	/* 上架开始时间*/
	private String shelvesTime;
	
	/* 上架结束时间*/
	private String shelvesTime1;
	
	/* 商品类型*/
	private String productTypeq;
	
        /* 商品审核状态*/
	private String auditState;
	
	/* 商品上下架状态*/
	private String state;
	
	

	
	public String getProductTypeq() {
        return productTypeq;
    }

    public void setProductTypeq(String productTypeq) {
        this.productTypeq = productTypeq;
    }

    public String getProductEnglishName() {
	        return productEnglishName;
	    }

	    public void setProductEnglishName(String productEnglishName) {
	        this.productEnglishName = productEnglishName;
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

	public String getShelvesTime() {
		return shelvesTime;
	}

	public void setShelvesTime(String shelvesTime) {
		this.shelvesTime = shelvesTime;
	}
	
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return super.toString()+  "ProductMainQueryModel [shopPrice=" + shopPrice
				+ ", shopPrice1=" + shopPrice1 + "]";
	}
	
	
}

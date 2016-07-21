package com.aebiz.b2b2c.product.interactive.product.vo;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;

public class ProductInteractiveModel extends BaseModel{
	/* 商品主信息*/
	private ProductMainModel productMain;
	
	/* 商品价格*/
	private ProductMainBasePriceModel productPrice;
	
	
	public ProductMainModel getProductMain() {
		return productMain;
	}
	public void setProductMain(ProductMainModel productMain) {
		this.productMain = productMain;
	}
	public ProductMainBasePriceModel getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(ProductMainBasePriceModel productPrice) {
		this.productPrice = productPrice;
	}
}

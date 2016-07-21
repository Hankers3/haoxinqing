package com.aebiz.b2b2c.product.common;

public class ProductCacheConstant {
	
	/* 商品系统，名称 */
	public static final String PRODUCT_SYS_NAME = "product";

	/* 商品系统，缓存客户端名称 */
	public static final String PRODUCT_CLIENT_NAME = "memCachedClient";
	
	/* 商品的缓存key */
	public static final String PRODUCT_PRODUCTMAIN_KEY = PRODUCT_SYS_NAME + "/productmain";
	
	/* 药品的缓存key */
	public static final String PRODUCT_MAIN_DESCRIPTION = PRODUCT_SYS_NAME + "/description";
}

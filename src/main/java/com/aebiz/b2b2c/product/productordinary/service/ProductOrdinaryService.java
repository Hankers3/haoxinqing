package com.aebiz.b2b2c.product.productordinary.service;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productpub.vo.ProductModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductQueryModel;

public interface ProductOrdinaryService extends BaseService<ProductModel,ProductQueryModel>{
	/**
	 * 发布商品
	 * @param m
	 */
	public String save(ProductModel m);
	
	
}

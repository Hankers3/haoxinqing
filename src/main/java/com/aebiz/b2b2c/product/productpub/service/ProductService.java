package com.aebiz.b2b2c.product.productpub.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productpub.vo.ProductModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductQueryModel;

public interface ProductService extends BaseService<ProductModel,ProductQueryModel>{
	/**
	 * 发布商品
	 * @param m
	 */
	public String save(ProductModel m,String type);
	
	/**
	 * 根据类型,更新商品
	 * @param m
	 * @param type
	 */
	public void update(ProductModel m,String type);
	
	
	public List<ProductModel> getProductListByCondition(ProductQueryModel paramQM, int paramInt1, int paramInt2);
	
	/**
	 * 设置商品上架,根据所选productUuid集合
	 * @param productUuids 商品productUuid集合
	 * 
	 */
	public List<String> updateStateShelves(List<String> productUuids);
	
	/**
	 * 设置商品下架,根据所选productUuid集合
	 * @param productUuids 商品productUuid集合
	 * 
	 */
	public List<String> updateStateUndercarriadge(List<String> productUuids);
	
	/**
	 * 根据商品uuid,获取赠送商品
	 * @param productUuid
	 * @return
	 */
	//public List<ProductGift> getProductGiftByUuid(List<String> productUuid);
	
	
}
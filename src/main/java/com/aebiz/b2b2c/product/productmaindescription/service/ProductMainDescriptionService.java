package com.aebiz.b2b2c.product.productmaindescription.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionQueryModel;

public interface ProductMainDescriptionService extends BaseService<ProductMainDescriptionModel,ProductMainDescriptionQueryModel>{
	/**
	 * 根据商品uuid集合 删除描述信息
	 * @param productUuids
	 */
	public void deletesByProductUuids(List<String> productUuids);
	
	/**
	 * 根据商品uuid 查询描述信息
	 * @param productUuid
	 * @return
	 */
	public ProductMainDescriptionModel getByProductUuid(String productUuid);
	
	/**
	 * 恢复商品描述表,根据所选productUuid集合
	 * @param productUuids 商品productUuid集合
	 * 
	 */
	public void recycleProductDesc(List<String> productUuids);
	
	/**
	 * 根据商品UUID获取查询描述信息的UUID
	 * @param productUuid
	 * @return
	 */
	public String getProductMainDescriptionUuidByProductUuid(String productUuid);
}

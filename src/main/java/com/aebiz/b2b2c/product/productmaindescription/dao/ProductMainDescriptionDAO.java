package com.aebiz.b2b2c.product.productmaindescription.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionQueryModel;

public interface ProductMainDescriptionDAO extends BaseDAO<ProductMainDescriptionModel,ProductMainDescriptionQueryModel>{
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
	
	public String getUuidByProductUuid(String productUuid);
	
	
	/**
	 * 恢复商品描述表,根据所选productUuid集合
	 * @param productUuids 商品productUuid集合
	 * 
	 */
	public void recycleProductDesc(List<String> productUuids);
}
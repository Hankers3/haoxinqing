package com.aebiz.b2b2c.product.productmainbaseprice.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceQueryModel;

public interface ProductMainBasePriceDAO extends BaseDAO<ProductMainBasePriceModel,ProductMainBasePriceQueryModel>{
	/**
	 * 根据商品uuid集合 删除价格信息
	 * @param productUuids
	 */
	public void deletesByProductUuids(List<String> productUuids);
	
	/**
	 * 根据productUuid获取商品价格信息model
	 * @param productUuid
	 * @return
	 */
	public ProductMainBasePriceModel getProductMainBasePriceModelByProductUuid(String productUuid);
	
	/**
	 * 根据productUuid获取商品价格
	 * 
	 * @param productUuid
	 * @return
	 */
	public double getShopPrice(String productUuid);
	
	/**
	 * 根据productUuid获取计费方式
	 * 
	 * @param productUuid
	 * @return
	 */
	public String getChargetype(String productUuid);

}
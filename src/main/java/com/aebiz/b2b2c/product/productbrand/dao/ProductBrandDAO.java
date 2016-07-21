package com.aebiz.b2b2c.product.productbrand.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandQueryModel;
/**
 * 商品品牌数据操作
 * 
 * @author huangpinpin
 *
 */
public interface ProductBrandDAO extends BaseDAO<ProductBrandModel,ProductBrandQueryModel>{
	
	/**
	 * 通过父分类uuid查询所属子分类集合
	 * @param productBrandUuid
	 * @return
	 */
	public List<ProductBrandQueryModel> getSubProductBrandsByUuid(String productBrandUuid);
	
	/**
	 * 根据品牌uuid,获取品牌名称
	 * @param uuid
	 * @return
	 */
	public String getBrandNameByUuid(String uuid);
	
	/**
	 * 根据品牌uuid集合,获取品牌名称集合
	 * @param uuid
	 * @return
	 */
	public List<String> getBrandNamesByUuids(List<String> uuids);
	
	/**
	 * 根据品牌uuid集合,获取品牌集合
	 * @param uuid
	 * @return
	 */
	public List<ProductBrandModel> getByUuids(List<String> uuids);
}
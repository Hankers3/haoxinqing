package com.aebiz.b2b2c.product.interactive.productbrand.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandQueryModel;
/**
 * 商品品牌数据操作
 * 
 * @author huyingying
 *
 */
public interface ProductBrandInteractiveDAO extends BaseDAO<ProductBrandModel,ProductBrandQueryModel>{
	/**
	 * 通过商品平铺uuids集合查询商品品牌集合
	 * @param uuids
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List<ProductBrandModel> getProductBrandsByUuids(List<String> uuids,int start,int pageShow);
}
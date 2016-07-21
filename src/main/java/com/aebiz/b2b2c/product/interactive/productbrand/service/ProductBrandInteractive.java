package com.aebiz.b2b2c.product.interactive.productbrand.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandQueryModel;

/**
 * 商品品牌对外接口
 * 
 * @author huyingying
 * 
 */
public interface ProductBrandInteractive extends
		BaseService<ProductBrandModel, ProductBrandQueryModel> {
	
	/**
	 * 通过商品平铺uuids集合查询商品品牌集合
	 * @param uuids
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List<ProductBrandModel> getProductBrandsByUuids(List<String> uuids,int start,int pageShow);
 
}

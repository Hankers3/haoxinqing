package com.aebiz.b2b2c.product.interactive.productcategoryplatform.service;

import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;

/**
 * 
 * 商品平台分类对外接口
 * 
 * @author huangpinpin
 *
 */
public interface ProductCategoryPlatfromInteractiveService {
	
	/**
	 * 根据Uuid获取平台分类
	 * @param uuid 分类uuid
	 * @return
	 */
	public ProductCategoryPlatformModel getByUuid(String uuid);
}

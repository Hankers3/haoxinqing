package com.aebiz.b2b2c.product.interactive.productcategoryplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.product.interactive.productcategoryplatform.service.ProductCategoryPlatfromInteractiveService;
import com.aebiz.b2b2c.product.productcategoryplatform.service.ProductCategoryPlatformService;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;

@Service
@Transactional
public class ProductCategoryPlatformInteractiveServiceImpl implements ProductCategoryPlatfromInteractiveService {
	@Autowired
	private ProductCategoryPlatformService myService;
	
	/**
	 * 根据Uuid获取平台分类
	 * @param uuid 分类uuid
	 * @return
	 */
	public ProductCategoryPlatformModel getByUuid(String uuid){
		return myService.getByUuid(uuid);
	}
	
	
}

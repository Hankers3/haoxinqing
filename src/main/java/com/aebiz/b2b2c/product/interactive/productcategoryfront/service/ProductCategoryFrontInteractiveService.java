package com.aebiz.b2b2c.product.interactive.productcategoryfront.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.vo.ProductCategoryFrontInteractiveModel;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.vo.ProductCategoryFrontInteractiveQueryModel;

public interface ProductCategoryFrontInteractiveService extends BaseService<ProductCategoryFrontInteractiveModel,ProductCategoryFrontInteractiveQueryModel>{
	
	/**
	 * 对外接口：获取所有前台分类
	 * 
	 * @param parentUuid
	 * 			父类uuid
	 * @return
	 */
	public List<ProductCategoryFrontInteractiveModel> getAllProductCategoryFront(String parentUuid);
	
	
	/**
	 * 根据分类父uuid，分类Uuid集合查询分类集合（带分页）
	 * @param parentUuid
	 * @param uuids
	 * @param start
	 * @param pageNow
	 * @return
	 */
	public List<ProductCategoryFrontInteractiveModel> getProductCategorys(String parentUuid,String[] uuids,int start,int pageNow);
	
	/**
	 * 根据父分类uuid查询分类数
	 * @param parentUuid
	 * @return
	 */
	public int getProductCategoryCount(String parentUuid);
	
	
	/**
	 * 根据分类uuid查询分类,三级分类
	 * @param parentUuid
	 * @param uuids
	 * @param start
	 * @param pageNow
	 * @return
	 */
	public ProductCategoryFrontInteractiveModel getByCategoryUuid(String categoryUuid);
}

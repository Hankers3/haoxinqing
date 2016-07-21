package com.aebiz.b2b2c.product.interactive.productcategoryfront.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.vo.ProductCategoryFrontInteractiveModel;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.vo.ProductCategoryFrontInteractiveQueryModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontModel;

public interface ProductCategoryFrontInteractiveDAO extends BaseDAO<ProductCategoryFrontInteractiveModel,ProductCategoryFrontInteractiveQueryModel>{
	/**
	 * 
	 * 对外接口：通过商品分类获取子分类
	 * @param parentUuid
	 * 				父分类uuid
	 * @param storeUuid
	 * 				商户uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getSubProductCategoryByUuid(String parentUuid);
	
	/**
	 * 根据分类父uuid，分类Uuid集合查询分类集合（带分页面）
	 * @param parentUuid
	 * @param uuids
	 * @param start
	 * @param pageNow
	 * @return
	 */
	public List<ProductCategoryFrontModel> getProductCategorys(String parentUuid,String[] uuids,int start,int pageNow);
	
	/**
	 * 根据父分类uuid查询分类数
	 * @param parentUuid
	 * @return
	 */
	public int getProductCategoryCount(String parentUuid);
	
	/**
	 * 通过分类uuid查询分类
	 * @param categoruUuid
	 * @return
	 */
	public ProductCategoryFrontModel getByCategoryUuid(String categoryUuid);
	

}
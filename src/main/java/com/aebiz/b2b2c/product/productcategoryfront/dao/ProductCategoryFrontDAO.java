package com.aebiz.b2b2c.product.productcategoryfront.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontQueryModel;

public interface ProductCategoryFrontDAO extends BaseDAO<ProductCategoryFrontModel,ProductCategoryFrontQueryModel>{
	/**
	 * 根据前台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getSubProductCategoryByParentUuid(String parentUuid);
	
	/**
	 * 根据前台父分类uuid查询子分类uuid
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<String> getSubProductCategoryUuidByParentUuid(String parentUuid);
	
	/**
	 * 根据前台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getFrontCategoryByUuids(List<String> uuids);
	
	/**
	 * 该方法用于生成fullpath时使用，查询该fullpath是否已经存在
	 * @param fullPath 
	 * @return
	 */
	public List<ProductCategoryFrontModel> getProductCategoryFrontModelByFullPath(String fullPath);
	
	/**
	 * 根据fullpath 获取其所有子分类uuid集合（包括本身）
	 * @param fullPath
	 * @return
	 */
	public List<String> getUuidsByFullPath(String fullPath);
}
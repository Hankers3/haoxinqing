package com.aebiz.b2b2c.product.productimagecategory.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productimagecategory.vo.ProductImageCategoryModel;
import com.aebiz.b2b2c.product.productimagecategory.vo.ProductImageCategoryQueryModel;
import com.aebiz.b2b2c.product.sysback.web.productcategoryplatform.vo.TreeElement;

public interface ProductImageCategoryService extends BaseService<ProductImageCategoryModel,ProductImageCategoryQueryModel>{
	/**
	 * 根据商品图片父分类uuid查询商品图片子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductImageCategoryModel> getSubProductImageCategoryByParentUuid(String parentUuid);
	
	/**
	 * 得到下级分类列表根据父分类uuid
	 * @param parenUuid
	 * @return
	 */
	public List<TreeElement> getRootList(String parenUuid);
	/**
	 * 获取父分类下的所有分类的uuid集合
	 * @param parentUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getSubCategory(String parentUuid);
}

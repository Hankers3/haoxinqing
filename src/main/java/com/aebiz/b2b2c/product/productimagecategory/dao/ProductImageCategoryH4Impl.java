package com.aebiz.b2b2c.product.productimagecategory.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;
import com.aebiz.b2b2c.product.productimagecategory.vo.ProductImageCategoryModel;
import com.aebiz.b2b2c.product.productimagecategory.vo.ProductImageCategoryQueryModel;

@Repository
public class ProductImageCategoryH4Impl extends BaseH4Impl<ProductImageCategoryModel,ProductImageCategoryQueryModel> implements ProductImageCategoryDAO {
	/**
	 * 根据图片父分类uuid查询子图片分类
	 * 该功能用于图片分类树展开时查询子图片分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductImageCategoryModel> getSubProductImageCategoryByParentUuid(String parentUuid){
		String hql="select o from ProductImageCategoryModel o where parentUuid=:parentUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		List<ProductImageCategoryModel> list=query.list();
		return list;
	}
	
	/**
	 * 获取父分类下的所有分类的uuid集合
	 * @param parentUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getSubCategory(String parentUuid) {
		String hql = "select o.uuid from ProductImageCategoryModel o where parentUuid=:parentUuid";

		Query query = this.getH4Session().createQuery(hql);

		query.setString("parentUuid", parentUuid);
		List<String> list = query.list();
		return list;
	}
}

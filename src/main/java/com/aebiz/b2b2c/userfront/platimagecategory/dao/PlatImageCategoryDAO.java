package com.aebiz.b2b2c.userfront.platimagecategory.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.userfront.platimagecategory.vo.PlatImageCategoryModel;
import com.aebiz.b2b2c.userfront.platimagecategory.vo.PlatImageCategoryQueryModel;

public interface PlatImageCategoryDAO
		extends
			BaseDAO<PlatImageCategoryModel, PlatImageCategoryQueryModel> {

	/**
	 * 根据图片父分类uuid查询图片子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<PlatImageCategoryModel> getSubPlatImageCategoryByParentUuid(String parentUuid);
	
	/**
	 * 获取父分类下的所有分类的uuid集合
	 * @param parentUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getSubCategory(String parentUuid);
}
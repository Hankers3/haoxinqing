package com.aebiz.b2b2c.userfront.platimagecategory.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.userfront.platimagecategory.vo.PlatImageCategoryModel;
import com.aebiz.b2b2c.userfront.platimagecategory.vo.PlatImageCategoryQueryModel;

@Repository
public class PlatImageCategoryH4Impl
		extends
			BaseH4Impl<PlatImageCategoryModel, PlatImageCategoryQueryModel>
		implements
			PlatImageCategoryDAO {

	/**
	 * 根据图片父分类uuid查询图片子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<PlatImageCategoryModel> getSubPlatImageCategoryByParentUuid(
			String parentUuid) {
		String hql = "select o from PlatImageCategoryModel o where parentUuid=:parentUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		List<PlatImageCategoryModel> list = query.list();
		return list;
	}
	
	/**
	 * 获取父分类下的所有分类的uuid集合
	 * @param parentUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getSubCategory(String parentUuid){
		String hql = "select o.uuid from PlatImageCategoryModel o where parentUuid=:parentUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		List<String> list = query.list();
		return list;
	}
}

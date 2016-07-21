package com.aebiz.b2b2c.product.productcategoryfront.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontQueryModel;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;

@Repository
public class ProductCategoryFrontH4Impl extends BaseH4Impl<ProductCategoryFrontModel,ProductCategoryFrontQueryModel> implements ProductCategoryFrontDAO {
	/**
	 * 根据前台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getSubProductCategoryByParentUuid(String parentUuid){
		String hql="select o from ProductCategoryFrontModel o where parentUuid=:parentUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		List<ProductCategoryFrontModel> list=query.list();
		return list;
	}
	
	/**
	 * 根据前台父分类uuid查询子分类uuid
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<String> getSubProductCategoryUuidByParentUuid(String parentUuid) {
		String hql="select o.uuid from ProductCategoryFrontModel o where parentUuid=:parentUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		List<String> list=query.list();
		return list;
	}
	
	
	
	
	/**
	 * 根据前台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getFrontCategoryByUuids(List<String> uuids){
		if(uuids==null || uuids.size()==0 ){
			return null;
		}
		String hql="select o from ProductCategoryFrontModel o where o.uuid in(:uuids)";
		Query query = this.getH4Session().createQuery(hql);
		query.setParameterList("uuids", uuids);
		
		List<ProductCategoryFrontModel> list=query.list();
		return list;
	}
	
	/**
	 * 该方法用于生成fullpath时使用，查询该fullpath是否已经存在
	 * @param fullPath 
	 * @return
	 */
	public List<ProductCategoryFrontModel> getProductCategoryFrontModelByFullPath(String fullPath){
		if(StringUtil.isEmpty(fullPath)){
			return null;
		}
		String hql=" select o from ProductCategoryFrontModel  o where o.fullpath=:fullpath ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("fullpath", fullPath);
		List<ProductCategoryFrontModel> list=query.list();
		return list;
	}		
	
	/**
	 * 根据fullpath 获取其所有子分类uuid集合（包括本身）
	 * @param fullPath
	 * @return
	 */
	public List<String> getUuidsByFullPath(String fullPath){
		if(StringUtil.isEmpty(fullPath)){
			return null;
		}
		String hql=" select o.uuid from ProductCategoryFrontModel  o where o.fullpath >=:fullpath ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("fullpath", fullPath);
		List<String> list=query.list();
		return list;
	}
	
}

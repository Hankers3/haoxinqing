package com.aebiz.b2b2c.cms.contentcategory.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryQueryModel;

@Repository
public class ContentCategoryH4Impl extends
		BaseH4Impl<ContentCategoryModel, ContentCategoryQueryModel> implements
		ContentCategoryDAO {
	/**
	 * 根据平台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(
			String parentUuid ) {
		String hql = "select o from ContentCategoryModel o where parentUuid=:parentUuid ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		List<ContentCategoryModel> list = query.list();
		return list;
	}
	
	/**
	 * 根据平台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(
			String parentUuid ,String categoryType) {
		String hql = "select o from ContentCategoryModel o where parentUuid=:parentUuid and categoryType =:categoryType";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		query.setString("categoryType", categoryType);
		List<ContentCategoryModel> list = query.list();
		return list;
	}
	/**
	 * 根据平台父分类uuid查询子分类uuid 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<String> getSubContentCategoryUuidByParentUuid(String parentUuid,String categoryType) {
		String hql = "select o.uuid from ContentCategoryModel o where parentUuid=:parentUuid   and categoryType =:categoryType order by o.position asc";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		query.setString("categoryType", categoryType);
		List<String> list = query.list();
		return list;
	}
	
	/**
	 * 根据平台父分类uuid查询子分类uuid 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<String> getSubContentCategoryUuidByParentUuid(String parentUuid) {
		String hql = "select o.uuid from ContentCategoryModel o where parentUuid=:parentUuid order by o.position asc";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		List<String> list = query.list();
		return list;
	}


	/**
	 * 该方法用于生成fullpath时使用，查询该fullpath是否已经存在
	 * 
	 * @param fullPath
	 * @return
	 */
	public List<ContentCategoryModel> getContentCategoryByFullPath(
			String fullPath) {
		String hql = " select o from ContentCategoryModel  o where o.fullpath=:fullpath ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("fullpath", fullPath);
		List<ContentCategoryModel> list = query.list();
		return list;
	}
	
	public List<String> getContentCategoryUuidsByFullPath(
			String fullPath) {
		String hql = " select o.uuid from ContentCategoryModel  o where o.fullpath=:fullpath ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("fullpath", fullPath);
		return query.list();
	}

	/**
	 * 根据分类uuid获取分类名称
	 * 
	 * @param uuid
	 *            分类uuid
	 * @return
	 */
	public String getNameByCategoryUuid(String uuid) {
		if (StringUtil.isEmpty(uuid)) {
			return "";
		}
		String hql = "select o.categoryName from ContentCategoryModel o where uuid=:uuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("uuid", uuid);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (String) obj;
		}
		return "";
	}

	/**
	 * 根据集合uuids 查出 分类集合
	 * 
	 * @param uuids
	 * @return
	 */
	public List<ContentCategoryModel> getCategorysByUuids(List<String> uuids) {
		String hql = "select o from ContentCategoryModel o  where o.uuid in (:uuids)";
		Query query = this.getH4Session().createQuery(hql);
		query.setParameterList("uuids", uuids);
		List<ContentCategoryModel> list = query.list();
		return list;
	}
	
	public List<String> getCategoryUuidsByUuids(List<String> uuids) {
		String hql = "select o.uuid from ContentCategoryModel o  where o.uuid in (:uuids)";
		Query query = this.getH4Session().createQuery(hql);
		query.setParameterList("uuids", uuids);
		return query.list();
	}
	
	/**
	 * 获取所有根分类集合
	 * @return
	 */
	public List<ContentCategoryModel> getAllRootCategorys(){
		String hql = "select o from ContentCategoryModel o  where o.parentUuid = :parentUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", "");
		List<ContentCategoryModel> list = query.list();
		return list;
	}
	
	public List<String> getAllRootCategoryUuids(){
		String hql = "select o.uuid from ContentCategoryModel o  where o.parentUuid = :parentUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", "");
		return query.list();
	}
	
	/**
	 * 根据uuid获取父类uuid
	 * 
	 * @param uuid
	 * 			分类uuid
	 * @return
	 */
	public String getParentUuidByUuid(String uuid){
		String hql = "select o.parentUuid from ContentCategoryModel o  where o.uuid = :uuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("uuid",uuid);
		Object obj = query.uniqueResult();
		if(obj!=null){
			return (String) obj;
		}
		return "";
	}
	
	/**
	 * 根据分类编号获取对象
	 * @param categoryNo
	 * @return
	 */
	@Override
	public ContentCategoryModel getContentCategoryByCategoryNo(String categoryNo) {
		
		String hql = "select o from ContentCategoryModel o where categoryNo=:categoryNo";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("categoryNo", categoryNo);
		List<ContentCategoryModel> list = query.list();
		if (list!=null && list.size()>0) {
			ContentCategoryModel categoryModel= list.get(0);
			if (categoryModel!=null) {
				return categoryModel;
			}else{
				return new ContentCategoryModel();
			}
		}
		return null;
	}
}

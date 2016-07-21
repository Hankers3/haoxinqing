package com.aebiz.b2b2c.cms.contentcategory.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryQueryModel;

public interface ContentCategoryDAO extends BaseDAO<ContentCategoryModel,ContentCategoryQueryModel>{
	/**
	 * 根据平台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @param categoryType 
	 * @return
	 */
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(String parentUuid);
	
	/**
	 * 根据平台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @param categoryType 
	 * @return
	 */
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(String parentUuid, String categoryType);
	
	
	/**
	 * 根据平台父分类uuid查询子分类uuid
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<String> getSubContentCategoryUuidByParentUuid(String parentUuid);
	
	/**
	 * 根据平台父分类uuid查询子分类uuid
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<String> getSubContentCategoryUuidByParentUuid(String parentUuid,String categoryType);
	
	
	/**
	 * 该方法用于生成fullpath时使用，查询该fullpath是否已经存在
	 * @param fullPath 
	 * @return
	 */
	public List<ContentCategoryModel> getContentCategoryByFullPath(String fullPath);
	
	public List<String> getContentCategoryUuidsByFullPath(String fullPath);
	
	/**
	 * 根据分类uuid获取分类名称
	 * @param uuid 分类uuid
	 * @return
	 */
	public String getNameByCategoryUuid(String uuid);
	
	/**
	 * 根据集合uuids 查出 分类集合
	 * @param uuids
	 * @return
	 */
	public List<ContentCategoryModel> getCategorysByUuids(List<String> uuids);
	
	public List<String> getCategoryUuidsByUuids(List<String> uuids);
	
	/**
	 * 获取所有根分类集合
	 * @return
	 */
	public List<ContentCategoryModel> getAllRootCategorys();
	
	public List<String> getAllRootCategoryUuids();
	
	/**
	 * 根据uuid获取父类uuid
	 * 
	 * @param uuid
	 * 			分类uuid
	 * @return
	 */
	public String getParentUuidByUuid(String uuid);
	
	/**
	 * 根据分类编号获取对象
	 * @param categoryNo
	 * @return
	 */
	public ContentCategoryModel getContentCategoryByCategoryNo(String categoryNo);
}
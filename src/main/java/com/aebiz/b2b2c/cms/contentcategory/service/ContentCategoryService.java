package com.aebiz.b2b2c.cms.contentcategory.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryQueryModel;

public interface ContentCategoryService extends
		BaseService<ContentCategoryModel, ContentCategoryQueryModel> {
	/**
	 * 根据平台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(
			String parentUuid);

	/**
	 * 根据平台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(
			String parentUuid, String categoryType);

	/**
	 * 该方法用于生成fullpath时使用，查询该fullpath是否已经存在
	 * 
	 * @param fullPath
	 * @return
	 */
	public List<ContentCategoryModel> getContentCategoryPlatformModelByFullPath(
			String fullPath);

	/**
	 * 生成fullpath规则
	 * 
	 * @param parentId
	 * @param sheetIdNo
	 * @return
	 */
	public String getSheetNo(String parentId, int sheetIdNo);

	/**
	 * 生成fullpath规则
	 * 
	 * @param parentId
	 * @param sheetIdNo
	 * @return
	 */
	public String convertSheetIdNo(String parentId, int sheetIdNo);

	/**
	 * 批量添加分类
	 * 
	 * @param list
	 */
	public void addBatch(List<ContentCategoryModel> list);

	/**
	 * 根据分类uuid获取分类名称
	 * 
	 * @param uuid
	 *            分类uuid
	 * @return
	 */
	public String getNameByCategoryUuid(String uuid);

	/**
	 * 获取分类路径全名称 例：通用分类>电子>手机
	 * 
	 * @param uuid
	 *            分类uuid
	 * @param parentName
	 * @return
	 */
	public String getAllNameByUuid(String uuid, String parentName);

	/**
	 * 根据最后级分类uuid,获取各级所要选中的分类uuid
	 * 
	 * @param leafUuid
	 *            最后选择的分类uuid
	 * @return
	 */
	public void getSelectCategoryUuid(String leafUuid, List<String> list);

	/**
	 * 根据uuid获取父类uuid
	 * 
	 * @param uuid
	 *            分类uuid
	 * @return
	 */
	public String getParentUuidByUuid(String uuid);

	/**
	 * 取某个分类同级的分类集合
	 * 
	 * @param uuid
	 *            分类uuid
	 * @return
	 */
	public List<ContentCategoryModel> getSameLevelCategoryByUuid(String uuid);

	/**
	 * 根据分类编号获取对象
	 * 
	 * @param categoryNo
	 * @return
	 */
	public ContentCategoryModel getContentCategoryByCategoryNo(String categoryNo);

}

package com.aebiz.b2b2c.cms.contentcategory.service;

import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryQueryModel;

public interface ContentCategoryInteractive extends BaseService<ContentCategoryModel,ContentCategoryQueryModel>{
	/**
	 * 获取所有的根分类
	 * @return
	 */
	public List<ContentCategoryModel> getAllRootCategory();
	
	/**
	 * 根据分类UUID获取子分类名称以及内容集合
	 * 
	 * @param uuid
	 * @return
	 */
	public List<Map.Entry<ContentCategoryModel, List<ContentModel>>> getContentCategoryByUuid(String uuid);
	
	public Map<ContentCategoryModel, List<ContentModel>> getHelpContents(String uuid);
}

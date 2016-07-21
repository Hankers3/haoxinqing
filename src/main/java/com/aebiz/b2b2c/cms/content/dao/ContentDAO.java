package com.aebiz.b2b2c.cms.content.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.content.vo.ContentQueryModel;

public interface ContentDAO extends BaseDAO<ContentModel, ContentQueryModel> {

	/**
	 * 批量内容发布
	 * 
	 * @param needUpdateUuids
	 * @param state
	 */
	public void updates(List<String> needUpdateUuids, String state);

	/**
	 * 获取会员分享内容的数据
	 * 
	 * @return
	 */
	public ContentModel getShareContent(String contentCategoryUuid);

	/**
	 * 根据contentCategoryUuid获取ContentModel集合
	 * 
	 * @param contentCategoryUuid
	 * @return
	 */
	public List<ContentModel> getContentModelByCategoryUuid(
			String contentCategoryUuid);

	/**
	 * 根据contentCategoryUuid获取文章（没有患教）集合
	 * 
	 * @param contentCategoryUuid
	 * @return
	 */
	List<ContentModel> getContentListByCategoryUuid(String contentCategoryUuid);

	/**
	 * 根据患教分类获得患教信息
	 * 
	 * @param categoryId
	 */
	public List<ContentModel> getCustomerTeachList(String categoryId);

	/**
	 * app端搜索资讯信息
	 * @param contentCategoryUuid
	 * @param qm
	 * @return
	 */
	 
	public List<ContentModel> getByContentCategoryUuid(
			String contentCategoryUuid,ContentQueryModel qm);
	
	
	/**
	 * 根据分类UUID获取内容uuid集合
	 * 
	 * @param categoryUuid
	 * @return
	 */
	public List<String> getContentUuidsByCategoryUuid(String categoryUuid);
	/**
         * 
         * @Description: TODO(返回咨询的list)    
         * @author XP  
         * @return
         * @date 2015-12-27 上午11:24:51
         */
        public List<ContentModel> getAllContentModelList();

	
}
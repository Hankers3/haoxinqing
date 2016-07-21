package com.aebiz.b2b2c.cms.content.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.content.vo.ContentQueryModel;

public interface ContentService extends
		BaseService<ContentModel, ContentQueryModel> {

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
	public ContentModel getShareContent(String contentcategoryNo);

	/**
	 * 根据内容编号获得ContentModel
	 * 
	 * @param contentcategoryNo
	 *            内容编号
	 * @return
	 */
	public List<ContentModel> getAppContentModels(String contentcategoryNo);

	/**
	 * 根据内容编号获得APP更多或者服务准则的三个参数
	 * 
	 * @param contentcategoryNo
	 *            内容编号
	 * @return
	 */
	public List<Map<String, String>> getAppContent(String contentcategoryNo);

	/**
	 * 文件上传
	 * 
	 * @param ContentModel
	 * @param files
	 * @return
	 */
	public ContentModel uploadImage(ContentModel contentModel,
			MultipartFile[] files);

	/**
	 * 创建contentNo
	 * 
	 * @param m
	 */
	public String createContentNo();

	/**
	 * 根据分类id获得文章列表
	 * 
	 * @param contentCategoryUuid
	 */
	public List<ContentModel> getByContentCategoryUuid(
			String contentCategoryUuid);

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
	 * 
	 * @Description: TODO(返回咨询的list)    
    	 * @author XP  
    	 * @return
    	 * @date 2015-12-27 上午11:24:51
	 */
        public List<ContentModel> getAllContentModelList();
	
}

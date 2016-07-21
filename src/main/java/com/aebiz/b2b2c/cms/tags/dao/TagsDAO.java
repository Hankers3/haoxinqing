package com.aebiz.b2b2c.cms.tags.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.cms.tags.vo.TagsQueryModel;

public interface TagsDAO extends BaseDAO<TagsModel, TagsQueryModel> {
	/**
	 * 
	 * 通过标签分类ID获取该分类下关联的标签
	 * 
	 * @param categoryUuid
	 * @return
	 */
	public List<TagsModel> getTagsByCategoryUuid(String categoryUuid);
	
	/**
	 * 通过编号获取标签名
	 * @param uuid
	 * @return
	 */
	public String  getTagNameByUuid(String uuid);
	
	/**
	 * 
	 * 通过医生已有标签 获取未关联的标签
	 * 
	 * @param categoryUuid
	 * @return
	 */
	public List<TagsModel> getTagsByDoctorTag(String doctorTag);
	
	public List<String> getTagsUuidsByCategoryUuid(List<String> categoryUuid);
	/**
	 * 
	 * @Description: (通过标签id获取标签对象)    
	 * @author XP  
	 * @param consultTag
	 * @return
	 * @date 2016-1-21 下午2:54:07
	 */
	public List<TagsModel> getTagListByConsultTag(String consultTag);
}
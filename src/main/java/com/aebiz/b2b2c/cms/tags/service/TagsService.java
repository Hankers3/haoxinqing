package com.aebiz.b2b2c.cms.tags.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.cms.tags.vo.TagsQueryModel;

public interface TagsService extends BaseService<TagsModel, TagsQueryModel> {
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
	/**
	 * 
	 * @Description: (通过标签id获取标签对象)    
	 * @author XP  
	 * @param consultTag
	 * @return
	 * @date 2016-1-21 下午2:45:11
	 */
	public List<TagsModel> getTagListByConsultTag(String consultTag);
	
}

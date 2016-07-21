package com.aebiz.b2b2c.cms.tagscategoyrel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelQueryModel;

public interface TagsCategoyRelDAO extends
		BaseDAO<TagsCategoyRelModel, TagsCategoyRelQueryModel> {
	/**
	 * 根据标签分类uuid查找已关联标签
	 * 
	 * @param categoryUuid
	 *            标签分类uuid
	 * @return
	 */
	public List<String> getRelByCategoryUuid(String categoryUuid);

	/**
	 * 根据标签分类uuid删除,标签和标签分类关系表数据
	 * 
	 * @param categoryUuids
	 *            标签分类uuid
	 * @return
	 */
	public void deletesByCategoryUuid(List<String> categoryUuids);

	/**
	 * 根据标签uuid删除,标签和标签分类关系表数据
	 * 
	 * @param tagUuids
	 *            标签uuid
	 * @return
	 */
	public void deletesBytagUuid(List<String> stagUuids);
}
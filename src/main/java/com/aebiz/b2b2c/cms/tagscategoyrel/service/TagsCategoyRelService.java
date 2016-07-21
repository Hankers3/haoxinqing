package com.aebiz.b2b2c.cms.tagscategoyrel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelQueryModel;

/**
 * 标签和标签分类的关系业务接口 主要是实现标签和标签分类的关系，一个标签分类可以关联多个标签，一个标签可以被多个标签分类关联
 * 标签可以用于前台用户评论、商品收藏标签等 使用标签分类，方便后台维护标签
 * 
 * @author huangpinpin
 * 
 */
public interface TagsCategoyRelService extends
		BaseService<TagsCategoyRelModel, TagsCategoyRelQueryModel> {
	/**
	 * 批量关联标签到某个分类
	 * 
	 * @param needRelUuids
	 *            关联的标签uuid
	 * @param categoryId
	 *            关联分类的uuid
	 */
	public void relation(List<String> needRelUuids, String categoryId);

	/**
	 * 根据标签分类uuid查找已关联的标签
	 * 
	 * @param categoryUuid
	 *            标签分类uuid
	 * @return
	 */
	public List<String> existTagesUuids(String categoryUuid);

	/**
	 * 批量更新标签和分类关系表 主要更新位置
	 * 
	 * @param needRelUuids
	 */
	public void batchUpdate(List<String> selectRelUuids);

	/**
	 * 根据标签分类uuid,删除标签和标签分类关系表数据
	 * 
	 * @param categoryUuids
	 *            标签uuid
	 * @return
	 */
	public void deletesByCategoryUuid(List<String> categoryUuids);

	/**
	 * 根据标签uuid,删除标签和标签分类关系表数据
	 * 
	 * @param stagUuids
	 *            标签uuid
	 * @return
	 */
	public void deletesByStagUuid(List<String> stagUuids);
}

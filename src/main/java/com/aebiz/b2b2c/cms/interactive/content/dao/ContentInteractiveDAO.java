package com.aebiz.b2b2c.cms.interactive.content.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.interactive.content.vo.ContentInteractiveModel;
import com.aebiz.b2b2c.cms.interactive.content.vo.ContentInteractiveQueryModel;

public interface ContentInteractiveDAO extends BaseDAO<ContentInteractiveModel, ContentInteractiveQueryModel> {
	
	/**
	 * 根据qm获取总数
	 */
	public int getCountByContent(ContentInteractiveQueryModel qm) ;
	
	/**
	 * 根据qm获取集合
	 */
	public List<ContentInteractiveModel> getByContent(ContentInteractiveQueryModel qm,int start,int pageSize);
	
	/**
	 * 根据uuid集合查询内容集合
	 * @param contentUuids
	 * @return
	 */
	public List<ContentInteractiveModel> getByUuids(String[] contentUuids);
}
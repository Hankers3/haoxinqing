package com.aebiz.b2b2c.cms.interactive.content.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.interactive.content.vo.ContentInteractiveModel;
import com.aebiz.b2b2c.cms.interactive.content.vo.ContentInteractiveQueryModel;


/**
 * 内容对外接口
 * 
 * @author huyingying
 * 
 */
public interface ContentInteractive extends BaseService<ContentInteractiveModel, ContentInteractiveQueryModel>{
	
	/**
	 * 根据qm获取内容总数
	 * @param qm
	 * @return
	 */
	public int getCountByContent(ContentInteractiveQueryModel qm);
	
	/**
	 * 根据qm获取内容集合
	 * @param qm
	 * @return
	 */
	public List<ContentInteractiveModel> getByContent(ContentInteractiveQueryModel qm,int paramInt1,int paramInt2);
	
	/**
	 * 根据uuid集合查询内容集合，带分页
	 * @param uuids
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List<ContentInteractiveModel> getContentsByUuids(String uuids[],int paramInt1,int paramInt2);
}

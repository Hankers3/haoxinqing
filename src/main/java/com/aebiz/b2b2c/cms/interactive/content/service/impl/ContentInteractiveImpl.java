package com.aebiz.b2b2c.cms.interactive.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.cms.interactive.content.dao.ContentInteractiveDAO;
import com.aebiz.b2b2c.cms.interactive.content.service.ContentInteractive;
import com.aebiz.b2b2c.cms.interactive.content.vo.ContentInteractiveModel;
import com.aebiz.b2b2c.cms.interactive.content.vo.ContentInteractiveQueryModel;

@Service
@Transactional
public class ContentInteractiveImpl extends BaseServiceImpl<ContentInteractiveModel, ContentInteractiveQueryModel> implements ContentInteractive {
	private ContentInteractiveDAO myDao = null;

	@Autowired
	public void setMyDao(ContentInteractiveDAO dao) {
		this.myDao = dao;
	}

	/**
	 * 根据qm获取内容总数
	 * @param qm
	 * @return
	 */
	public int getCountByContent(ContentInteractiveQueryModel qm) {
		
		return myDao.getCountByContent(qm);
	}

	/**
	 * 根据qm获取内容集合
	 * @param qm
	 * @return
	 */
	public List<ContentInteractiveModel> getByContent(ContentInteractiveQueryModel qm,int paramInt1,int paramInt2) {
		return myDao.getByContent(qm, paramInt1, paramInt2);
	}
	
	/**
	 * 根据uuid集合查询内容集合，带分页
	 * @param uuids
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List<ContentInteractiveModel> getContentsByUuids(String uuids[],int paramInt1,int paramInt2){
		ContentInteractiveQueryModel qm=new ContentInteractiveQueryModel();
		qm.setInContentUuids(uuids);
		return myDao.getByContent(qm, paramInt1, paramInt2);
	}

}

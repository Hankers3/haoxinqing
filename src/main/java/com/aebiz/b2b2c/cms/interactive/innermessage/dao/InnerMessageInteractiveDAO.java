package com.aebiz.b2b2c.cms.interactive.innermessage.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;
import com.aebiz.b2b2c.cms.interactive.innermessage.vo.InnerMessageInteractiveModel;

public interface InnerMessageInteractiveDAO extends BaseDAO<InnerMessageModel,InnerMessageQueryModel>{
	/**
	 * 对外接口：获取会员所有未读消息
	 * 
	 * @param userUuid
	 * @return
	 */
	public List<InnerMessageInteractiveModel> getUnreadMessageList(String userUuid);
	
	/**
	 * 对外接口：获取会员所有未读消息数
	 * 
	 * @param userUuid
	 * @return
	 */
	public int getUnreadMessageCount(String userUuid);
}
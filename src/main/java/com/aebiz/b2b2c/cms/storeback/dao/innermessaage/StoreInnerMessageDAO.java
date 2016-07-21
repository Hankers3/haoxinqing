package com.aebiz.b2b2c.cms.storeback.dao.innermessaage;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;

public interface StoreInnerMessageDAO extends BaseDAO<InnerMessageModel,InnerMessageQueryModel>{
	/**
	 * 根据消息类别获取我的消息列表
	 * 
	 * @param type
	 * @return
	 */
	public List<InnerMessageModel> getInnerMessageListByType(String type);
}
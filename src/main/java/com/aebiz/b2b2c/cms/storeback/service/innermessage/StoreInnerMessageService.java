package com.aebiz.b2b2c.cms.storeback.service.innermessage;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;

public interface StoreInnerMessageService extends BaseService<InnerMessageModel,InnerMessageQueryModel>{
	
	/**
	 * 发送站内信
	 * 
	 * @param receiveUser
	 * @param title
	 * @param content
	 */
	public void saveInnerMessage(String receiveUser, String title, String content);
	
	/**
	 * 删除站内信
	 * 
	 * @param needDeleteUuids
	 */
	public void deleteInnerMessage(List<String> needDeleteUuids, String type);
	
	/**
	 * 根据消息类别获取我的消息列表
	 * 
	 * @param type
	 * @return
	 */
	public List<InnerMessageModel> getInnerMessageListByType(String type);
}

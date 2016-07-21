package com.aebiz.b2b2c.cms.innermessage.dao;

import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;

public interface InnerMessageDAO extends BaseDAO<InnerMessageModel,InnerMessageQueryModel>{
	/**
	 * 根据消息类别获取消息列表
	 * 
	 * @param messageType
	 * @param doctorUuid 
	 * @return
	 */

	public List<InnerMessageModel> getInnerMessageListByMessageType(String messageType,String doctorUuid);

	
	/**
	 * 根据用户Id获取消息列表 
	 * @param userId
	 * @return
	 */
	public List<InnerMessageModel> getInnerMessageListByUserId(String userId,String readStatus,int pageCount,int pageNo);



	/**
	 * 通过参数返回list
	 * @param userId
	 * @param string
	 * @return
	 */
	public List<InnerMessageModel> getMessageList(String userId, String messageType);
	/**
	 * 返回消息中心未读数量通过收件人id
	 */
	public int getMessageCenterCount(String messageType, String userId);
	

	
}
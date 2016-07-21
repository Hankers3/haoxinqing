package com.aebiz.b2b2c.cms.interactive.innermessage.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;
import com.aebiz.b2b2c.cms.interactive.innermessage.vo.InnerMessageInteractiveModel;

public interface InnerMessageInteractiveService extends BaseService<InnerMessageModel,InnerMessageQueryModel>{
	/**
	 * 对外接口：获取当前会员所有未读消息
	 * 
	 * @return
	 */
	public List<InnerMessageInteractiveModel> getUnreadMessageList();
	
	/**
	 * 对外接口：获取当前会员所有未读消息数
	 * 
	 * @return
	 */
	public int getUnreadMessageCount();
}

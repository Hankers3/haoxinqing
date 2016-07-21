package com.aebiz.b2b2c.cms.innermessagenotice.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeModel;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeQueryModel;


public interface InnerMessageNoticeService extends BaseService<InnerMessageNoticeModel,InnerMessageNoticeQueryModel>{
	
	/**
	 * 通过接收人编号获取通知列表
	 * @param userId
	 * @return
	 */
	public List<InnerMessageNoticeModel> getByUserId(String userId,int pageCount,int pageNo);
	
	/**
	 * 
	 * 
	 * @param messageUuid
	 * @param userId
	 * @return
	 */
	public InnerMessageNoticeModel getInnerMessageNoticeModel(String messageUuid,String userId );
	
	/**
	 * 根据用户Id获取未读消息列表 
	 * @param userId
	 * @return
	 * hedongfei
	 */
	public List<InnerMessageNoticeModel> getInnerMessageListByUserId(String userId,String readStatus,int pageCount,int pageNo);
}

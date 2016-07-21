package com.hxq.mobile.message.InnerMessage.service;

import java.util.List;

import com.hxq.mobile.entity.message.InnerMessageNotice;
import com.hxq.mobile.util.repository.SimpleEntityService;

public interface InnerMessageNoticeService extends SimpleEntityService {
	/**
	 * 通过消息id和用户id获取消息通知
	 * @param messageUuid
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public InnerMessageNotice selectInnerMessageNotice(String messageUuid, String userId) throws Exception;

	/**
	 * 通过接收人编号获取通知列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<InnerMessageNotice> selectByUserId(String userId, int pageCount, int pageNo) throws Exception;

	/**
	 * 根据用户Id获取未读消息列表
	 * @param userId
	 * @return
	 * hedongfei
	 * @throws Exception
	 */
	public List<InnerMessageNotice> selectInnerMessageListByUserId(String userId,String readStatus,int pageCount,int pageNo) throws Exception;
}

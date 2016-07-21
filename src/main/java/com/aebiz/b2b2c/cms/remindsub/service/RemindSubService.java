package com.aebiz.b2b2c.cms.remindsub.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubModel;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubQueryModel;

public interface RemindSubService extends BaseService<RemindSubModel,RemindSubQueryModel>{
	
	/**
	 * 保存商户或者会员消息的设置
	 * @param accountUuid
	 * @param messageUuid
	 * @param mobileSend
	 * @param emailSend
	 * @param innerSend
	 */
	public void saveRemindSubModel(String accountUuid,String messageUuid,String remindSubUuid,String mobileSend,String emailSend,String innerSend);
}

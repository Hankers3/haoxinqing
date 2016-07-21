package com.aebiz.b2b2c.cms.storeback.service.messageremind;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;
import com.aebiz.b2b2c.cms.storeback.web.messageremind.vo.StoreMessageRemind;

public interface StoreMessageRemindService extends BaseService<MessageRemindModel,MessageRemindQueryModel>{
	
	/**
	 * 根据会员uuid的到商户消息内容和商户消息设置
	 * @param customerUuid
	 * @return
	 */
	public List<StoreMessageRemind> getStoreMessageWebModelList(String storeUuid,String messageType);
}

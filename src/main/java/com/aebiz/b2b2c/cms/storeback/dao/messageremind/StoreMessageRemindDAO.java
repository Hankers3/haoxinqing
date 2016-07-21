package com.aebiz.b2b2c.cms.storeback.dao.messageremind;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;

public interface StoreMessageRemindDAO extends BaseDAO<MessageRemindModel,MessageRemindQueryModel>{
	/**
	 * 通过账户类型(会员，商户)，消息类型或许消息list
	 * @param accountType
	 * @param messageType
	 * @return
	 */
	public List<MessageRemindModel> getMessageRemindListByAccountType(String accountType,String messageType);
}
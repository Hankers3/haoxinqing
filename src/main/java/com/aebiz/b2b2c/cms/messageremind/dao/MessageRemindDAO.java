package com.aebiz.b2b2c.cms.messageremind.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;

public interface MessageRemindDAO extends BaseDAO<MessageRemindModel,MessageRemindQueryModel>{
	/**
	 * 通过账户类型查询属于会员还是属于商户的站内消息
	 * @param accountType
	 * @return
	 */
	public List<MessageRemindModel> getMessageRemindListByAccountType(String accountType);
}
package com.aebiz.b2b2c.cms.remindsub.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubModel;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubQueryModel;

public interface RemindSubDAO extends BaseDAO<RemindSubModel,RemindSubQueryModel>{
	/**
	 * 通过账户（会员，商户）Uuid获取消息设置记录
	 * @param accountUuid
	 * @param messageUuid
	 * @return
	 */
	public RemindSubModel getRemindSubModelByMessageUuidAndAccountUuid(String accountUuid,String messageUuid);
	
	/**
	 *商户或者会员保存消息设置时，需要删除旧的设置记录
	 * @param accountUuid
	 */
	public void deleteAllRemindSubByAccountUuid(String accountUuid);
}
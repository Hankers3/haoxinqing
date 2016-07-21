package com.aebiz.b2b2c.cms.remindsub.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubModel;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubQueryModel;

@Repository
public class RemindSubH4Impl extends BaseH4Impl<RemindSubModel,RemindSubQueryModel> implements RemindSubDAO {
	
	/**
	 * 通过账户（会员，商户）Uuid获取消息设置记录
	 * @param accountUuid
	 * @param messageUuid
	 * @return
	 */
	public RemindSubModel getRemindSubModelByMessageUuidAndAccountUuid(String accountUuid,String messageUuid){
		StringBuffer hql=new StringBuffer("select o from RemindSubModel o where o.accountUuid=:accountUuid ");
		hql.append(" and o.messageRemindUuid=:messageRemindUuid");
		Query q = getH4Session().createQuery(hql.toString());
		if(!StringUtil.isEmpty(accountUuid)){
			q.setParameter("accountUuid", accountUuid);
		}
		if(!StringUtil.isEmpty(messageUuid)){
			q.setParameter("messageRemindUuid", messageUuid);
		}
		Object object=q.uniqueResult();
		if(object!=null){
			return (RemindSubModel)object;
		}
		return null;
	}
	
	/**
	 *商户或者会员保存消息设置时，需要删除旧的设置记录
	 * @param accountUuid
	 */
	public void deleteAllRemindSubByAccountUuid(String accountUuid){
		StringBuffer hql=new StringBuffer("delete from RemindSubModel o where o.accountUuid=:accountUuid ");
		Query q = getH4Session().createQuery(hql.toString());
		if(!StringUtil.isEmpty(accountUuid)){
			q.setParameter("accountUuid", accountUuid);
		}
		q.executeUpdate();
	}
}

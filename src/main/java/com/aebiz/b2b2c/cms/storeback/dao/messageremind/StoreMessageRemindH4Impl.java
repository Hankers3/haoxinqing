package com.aebiz.b2b2c.cms.storeback.dao.messageremind;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;

@Repository
public class StoreMessageRemindH4Impl extends BaseH4Impl<MessageRemindModel,MessageRemindQueryModel> implements StoreMessageRemindDAO {
	
	/**
	 * 通过账户类型(会员，商户)，消息类型获取消息list
	 * @param accountType
	 * @param messageType
	 * @return
	 */
	public List<MessageRemindModel> getMessageRemindListByAccountType(String accountType,String messageType){
		StringBuffer hql=new StringBuffer("select o from MessageRemindModel o where o.accountType=:accountType ");
		hql.append(" and o.type = :messageType ");
		Query q = getH4Session().createQuery(hql.toString());
		if(!StringUtil.isEmpty(accountType)){
			q.setParameter("accountType", accountType);
		}
		if(!StringUtil.isEmpty(messageType)){
			q.setParameter("messageType", messageType);
		}
		
		List<MessageRemindModel> obj = q.list();
		if(obj != null && obj.size()>0){
			return obj;
		}
		return null;
	}
}

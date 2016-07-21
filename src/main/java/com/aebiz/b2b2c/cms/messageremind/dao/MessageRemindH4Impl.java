package com.aebiz.b2b2c.cms.messageremind.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;

@Repository
public class MessageRemindH4Impl extends BaseH4Impl<MessageRemindModel,MessageRemindQueryModel> implements MessageRemindDAO {
	
	/**
	 * 通过账户类型查询属于会员还是属于商户的站内消息
	 * @param accountType
	 * @return
	 */
	public List<MessageRemindModel> getMessageRemindListByAccountType(String accountType){
		StringBuffer hql=new StringBuffer("select o from MessageRemindModel o where o.accountType=:accountType");
		Query q = getH4Session().createQuery(hql.toString());
		if(!StringUtil.isEmpty(accountType)){
			q.setParameter("accountType", accountType);
		}
		
		List<MessageRemindModel> obj = q.list();
		if(obj != null && obj.size()>0){
			return obj;
		}
		return null;
	}
}

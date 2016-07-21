package com.aebiz.b2b2c.cms.storeback.dao.innermessaage;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;

@Repository
public class StoreInnerMessageH4Impl extends BaseH4Impl<InnerMessageModel,InnerMessageQueryModel> implements StoreInnerMessageDAO {

	@Override
	public List<InnerMessageModel> getInnerMessageListByType(String type) {
		StringBuffer hql = new StringBuffer("from InnerMessageModel imm where 1=1");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		if (type.equals("sx")) {
			hql.append( " and imm.sjDelStatus='0'");
		} else if (type.equals("fx")) {
			hql.append( " and imm.sjDelStatus='0'");
		}
		
		hql.append(" order by imm.sendTime desc");
		
		List<InnerMessageModel> list = query.list();
		
		return list;
	}

}

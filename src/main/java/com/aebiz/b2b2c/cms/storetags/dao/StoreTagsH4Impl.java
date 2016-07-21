package com.aebiz.b2b2c.cms.storetags.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsModel;
import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsQueryModel;

@Repository
public class StoreTagsH4Impl extends BaseH4Impl<StoreTagsModel,StoreTagsQueryModel> implements StoreTagsDAO {
	/**
	 * 获取某一商户下发布的标签
	 * 
	 * @param storeUuid
	 * @return
	 */
	public List<StoreTagsModel> getStoreTagsModelsByStoreUuid(String storeUuid){
		StringBuffer hql = new StringBuffer("select o from StoreTagsModel o ");
		hql.append(" where o.storeUuid=:storeUuid");
		
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("storeUuid", storeUuid);
		
		return q.list();
	}
}

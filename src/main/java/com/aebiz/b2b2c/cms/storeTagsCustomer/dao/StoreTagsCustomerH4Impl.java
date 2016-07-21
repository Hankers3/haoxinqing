package com.aebiz.b2b2c.cms.storeTagsCustomer.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.storeTagsCustomer.vo.StoreTagsCustomerModel;
import com.aebiz.b2b2c.cms.storeTagsCustomer.vo.StoreTagsCustomerQueryModel;

@Repository
public class StoreTagsCustomerH4Impl extends
		BaseH4Impl<StoreTagsCustomerModel, StoreTagsCustomerQueryModel>
		implements StoreTagsCustomerDAO {
	/**
	 * 获取某一商户下,某个会员关联的标签id
	 * 
	 * @param storeUuid
	 * @return
	 */
	public List<String> getByStoreUuidAndCustomerUuid(String storeUuid,
			String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.tagUuid from StoreTagsCustomerModel o where o.storeUuid=:storeUuid and o.customerUuid=:customerUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("storeUuid", storeUuid);
		q.setParameter("customerUuid", customerUuid);

		return q.list();
	}

	/**
	 * 商户会员管理商户标签时、保存商户、标签、会员的关联关系
	 * 
	 * @param m
	 */
	public void deleteStoreTagsCustomerRel(String storeUuid, String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"delete from StoreTagsCustomerModel o where o.storeUuid=:storeUuid and o.customerUuid=:customerUuid");
		
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("storeUuid", storeUuid);
		q.setParameter("customerUuid", customerUuid);
		
		q.executeUpdate();
	}
}

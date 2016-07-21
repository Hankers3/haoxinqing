package com.aebiz.b2b2c.customermgr.customersubscription.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionModel;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionQueryModel;

@Repository
public class CustomerSubscriptionH4Impl extends
		BaseH4Impl<CustomerSubscriptionModel, CustomerSubscriptionQueryModel>
		implements CustomerSubscriptionDAO {

	/* 多表拼接，联合查询 */
	@Override
	protected String getMultiSelect() {
		String hql = ",c.customerName ";
		return hql;
	}

	@Override
	protected String getMultiModel() {
		String hql = ", CustomerModel c ";
		return hql;
	}

	@Override
	protected String getAppendHql(CustomerSubscriptionQueryModel qm) {
		String hql = " and o.customerUuid=c.uuid ";

		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			hql += " and c.customerName=:customerName ";
		}

		if ("customerName".equals(qm.getSortName())) {
			hql += " order by c.customerName " + qm.getSortType();
		} else {
			hql += super.getAppendHql(qm);
		}
		return hql;
	}

	@Override
	protected void setAppendHqlValue(CustomerSubscriptionQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			q.setParameter("customerName", qm.getCustomerName());
		}
	}

	@Override
	protected List<CustomerSubscriptionModel> exeResultList(
			List<Object[]> tempList) {
		List<CustomerSubscriptionModel> csmList = new ArrayList<CustomerSubscriptionModel>();
		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {
				CustomerSubscriptionModel csm = new CustomerSubscriptionModel();
				csm = (CustomerSubscriptionModel) obj[0];
				csm.setCustomerName(String.valueOf(obj[1]));
				csmList.add(csm);
			}
		}
		return csmList;
	}

	/**
	 * 根据会员订阅编号取消会员订阅
	 * 
	 * @param needUpdateUuids
	 */
	public void updateSubscriptionState(List<String> needUpdateUuids) {
		StringBuffer hql = new StringBuffer(
				" update CustomerSubscriptionModel set subscriptionState=:subscriptionState where uuid in(:uuid) ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString(
				"subscriptionState",
				CustomerSubscriptionModel.CUSTOMERSUBSCRIPTION_SUBSCRIPTIONSTATE_UNSUBSCRIBED);
		query.setParameterList("uuid", needUpdateUuids);

		query.executeUpdate();
	}

	/**
         * 根据会员订阅编号取消或者开启会员订阅
         * 
         * @param needUpdateUuids
         */
        public void updateSubscriptionState(List<String> needUpdateUuids,
                        String subState) {
                StringBuffer hql = new StringBuffer(
                                " update CustomerSubscriptionModel set subscriptionState=:subscriptionState where uuid in(:uuid) ");

                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("subscriptionState", subState);
                query.setParameterList("uuid", needUpdateUuids);

                query.executeUpdate();
        }
}

package com.aebiz.b2b2c.customermgr.customersubscription.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionModel;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionQueryModel;

public interface CustomerSubscriptionDAO extends
		BaseDAO<CustomerSubscriptionModel, CustomerSubscriptionQueryModel> {
	/**
	 * 根据会员订阅编号取消会员订阅
	 * 
	 * @param needUpdateUuids
	 */
	public void updateSubscriptionState(List<String> needUpdateUuids);
	/**
         * 根据会员订阅编号取消或者开启会员订阅
         * 
         * @param needUpdateUuids
         */
        public void updateSubscriptionState(List<String> needUpdateUuids, String subState);

}
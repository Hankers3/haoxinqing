package com.aebiz.b2b2c.customermgr.customersubscription.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionModel;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionQueryModel;

public interface CustomerSubscriptionService extends
		BaseService<CustomerSubscriptionModel, CustomerSubscriptionQueryModel> {
	/**
	 * 根据会员订阅编号取消会员订阅
	 * 
	 * @param needUpdateUuids
	 */
	public void updateSubscriptionState(List<String> needUpdateUuids);
}

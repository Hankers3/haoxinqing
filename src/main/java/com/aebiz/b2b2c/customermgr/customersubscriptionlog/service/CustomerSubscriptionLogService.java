package com.aebiz.b2b2c.customermgr.customersubscriptionlog.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogModel;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogQueryModel;

public interface CustomerSubscriptionLogService
		extends
		BaseService<CustomerSubscriptionLogModel, CustomerSubscriptionLogQueryModel> {
	/**
	 * 通过会员编号查询出该会员所有订阅的信息
	 * 
	 * @param customerUuid
	 * @return
	 */
	public List<CustomerSubscriptionLogModel> getSubscriptionListByCustomerUuid(
			String customerUuid);
}

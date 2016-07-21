package com.aebiz.b2b2c.customermgr.customersubscriptionlog.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogModel;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogQueryModel;

public interface CustomerSubscriptionLogDAO
		extends
		BaseDAO<CustomerSubscriptionLogModel, CustomerSubscriptionLogQueryModel> {
	/**
	 * 通过会员编号查询出该会员所有订阅的详细信息
	 * 
	 * @param customerUuid
	 * @return
	 */
	public List<CustomerSubscriptionLogModel> getSubscriptionListByCustomerUuid(
			String customerUuid);
	/**
	 * 获取提交申请的uuids
	 * @param customerUuid
	 * @return
	 */
       public List<String> getSubscriptionUuidsByCustomerUuid(String customerUuid);
}
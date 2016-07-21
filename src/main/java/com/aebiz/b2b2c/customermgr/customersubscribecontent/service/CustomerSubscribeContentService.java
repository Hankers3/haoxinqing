package com.aebiz.b2b2c.customermgr.customersubscribecontent.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentModel;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentQueryModel;

public interface CustomerSubscribeContentService
		extends
		BaseService<CustomerSubscribeContentModel, CustomerSubscribeContentQueryModel> {
	/**
	 * 检查订阅编号是否存在
	 * 
	 * @return
	 */
	public boolean checkSubscribeNoExist(String subscribeNo, String uuid);

	
}

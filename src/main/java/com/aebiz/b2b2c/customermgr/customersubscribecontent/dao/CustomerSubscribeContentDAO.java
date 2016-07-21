package com.aebiz.b2b2c.customermgr.customersubscribecontent.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentModel;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentQueryModel;

public interface CustomerSubscribeContentDAO
		extends
		BaseDAO<CustomerSubscribeContentModel, CustomerSubscribeContentQueryModel> {
	/**
	 * 检查订阅编号是否存在
	 * 
	 * @return
	 */
	public boolean checkSubscribeNoExist(String subscribeNo, String uuid);
	
	/**
         * 通过uuid得到编号
         * 
         * @param uuid
         * @return
         */
        public String getSubscribeNoByUuid(String uuid);

}
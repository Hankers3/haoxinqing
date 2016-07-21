package com.aebiz.b2b2c.finance.customeraccount.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountQueryModel;

public interface CustomerAccountDAO extends
		BaseDAO<CustomerAccountModel, CustomerAccountQueryModel> {

	/**
	 * 根据会员的uuid获取会员账户对象
	 * @param customerUuid
	 * @return 
	 * CustomerAccountModel
	 */
	public CustomerAccountModel getCustomerAccountModelByCustomerUuid(String customerUuid);
        /**
         * 根据会员的id回去会员账户的id
         * @param customerUuid
         * @return
         */
        public  String getCustomerAccountUuidByCustomerUuid(String customerUuid);
}
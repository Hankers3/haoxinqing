package com.aebiz.b2b2c.finance.customeraccount.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountQueryModel;

public interface CustomerAccountService
		extends
			BaseService<CustomerAccountModel, CustomerAccountQueryModel> {

	/**
	 * 根据会员的uuid获取会员账户对象
	 * @param customerUuid
	 * @return 
	 * CustomerAccountModel
	 */
	public CustomerAccountModel getCustomerAccountModelByCustomerUuid(String customerUuid);
	
	/**
	 * 根据密码和会员的uuid校验输入的虚拟账户密码是否正确
	 * @param password
	 * @param customerUuid
	 * @return 
	 * boolean
	 */
	public boolean checkPassword(String password,String customerUuid);
}

package com.aebiz.b2b2c.finance.customerbankrel.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelModel;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelQueryModel;

public interface CustomerBankRelDAO extends BaseDAO<CustomerBankRelModel,CustomerBankRelQueryModel>{

	/**
	 * 根据会员的uuid查询绑定申请对象,来判断该会员是否已经申请过绑定了
	 * @param uuid
	 * @return 
	 * CustomerBankRelModel
	 */
	public CustomerBankRelModel getCustomerBankRelModelByCustomerUuid(String uuid);

	/**
         * 根据会员的编号获得会员和银行绑定的编号
         * 
         * @param customerUuid
         * @return
         */
        public String getCustomerBankRelUuidByCustomerUuid(String customerUuid);
}
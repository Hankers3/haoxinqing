package com.aebiz.b2b2c.finance.storefinanceaccount.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountModel;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountQueryModel;

public interface StoreFinanceAccountDAO
		extends
			BaseDAO<StoreFinanceAccountModel, StoreFinanceAccountQueryModel> {

	/**
	 * 根据商户的uuid获取商户的财务账户信息
	 * @param accountUUid
	 * @return 
	 * StoreFinanceAccountModel
	 */
	public StoreFinanceAccountModel getStoreFinanceAccountModelByAccount(String accountUUid);
	
	public String getStoreFinanceAccountUuidByAccount(String accountUUid);
}
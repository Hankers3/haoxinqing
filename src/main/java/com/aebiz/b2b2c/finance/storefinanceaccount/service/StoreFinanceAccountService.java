package com.aebiz.b2b2c.finance.storefinanceaccount.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountModel;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountQueryModel;

public interface StoreFinanceAccountService
		extends
			BaseService<StoreFinanceAccountModel, StoreFinanceAccountQueryModel> {
	/**
	 * 根据商户的uuid获取商户的财务账户信息
	 * @param accountUUid
	 * @return 
	 * StoreFinanceAccountModel
	 */
	public StoreFinanceAccountModel getStoreFinanceAccountModelByAccount(String accountUUid);
	
	/**
	 * 更新商户保证金余额和冻结的保证金
	 * @param accountModel 商户账户的model
	 * @param bondMoney 保证余额
	 * @param freezeBondMoney 冻结保证金
	 * void
	 */
	public void updateBondChargeMoneyAndFreezeBondMoney(StoreFinanceAccountModel accountModel,float bondMoney,float freezeBondMoney);
	
	/**
	 * 根据密码和商户的uuid校验输入的虚拟账户密码是否正确
	 * @param password
	 * @param accountUuid
	 * @return 
	 * boolean
	 */
	public boolean checkPassword(String password,String accountUuid);
}

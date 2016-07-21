package com.aebiz.b2b2c.finance.storefinanceaccount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.finance.storefinanceaccount.dao.StoreFinanceAccountDAO;
import com.aebiz.b2b2c.finance.storefinanceaccount.service.StoreFinanceAccountInteractive;
import com.aebiz.b2b2c.finance.storefinanceaccount.service.StoreFinanceAccountService;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountModel;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountQueryModel;

@Service
@Transactional
public class StoreFinanceAccountInteractiveImpl
		extends
			BaseServiceImpl<StoreFinanceAccountModel, StoreFinanceAccountQueryModel>
		implements
			StoreFinanceAccountInteractive {
	//注入商户财务账户信息service
	@Autowired
	private StoreFinanceAccountService storeFinanceAccountService;
	
	private StoreFinanceAccountDAO myDao = null;
	@Autowired
	public void setMyDao(StoreFinanceAccountDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 新增商户财务账户信息
	 * 
	 */
	public void createStoreFinanceAccountModel(StoreFinanceAccountModel m) {
		storeFinanceAccountService.create(m);
	}
	
	/**
	 * 根据商户的uuid获取商户的财务账户信息
	 * 
	 * @param accountUUid
	 * @return StoreFinanceAccountModel
	 */
	public StoreFinanceAccountModel getStoreFinanceAccountModelByAccount(
			String accountUUid) {
		return myDao.getStoreFinanceAccountModelByAccount(accountUUid);
	}

	/**
	 * 更新商户保证金余额和冻结的保证金
	 * 
	 * @param accountModel
	 *            商户账户的model
	 * @param bondMoney
	 *            保证余额
	 * @param freezeBondMoney
	 *            冻结保证金 void
	 */
	public void updateBondChargeMoneyAndFreezeBondMoney(
			StoreFinanceAccountModel accountModel, float bondMoney,
			float freezeBondMoney) {
		if (accountModel != null) {
			accountModel.setBondMoney(bondMoney);
			accountModel.setFreezeBondMoney(freezeBondMoney);

			myDao.update(accountModel);
		}
	}
	
	/**
	 * 根据密码和商户的uuid校验输入的虚拟账户密码是否正确
	 * @param password
	 * @param accountUuid
	 * @return 
	 * boolean
	 */
	public boolean checkPassword(String password,String accountUuid){
		StoreFinanceAccountModel accountModel = this.getStoreFinanceAccountModelByAccount(accountUuid);
		
		//TODO 以后密码会加密处理
		if(accountModel != null){
			if(accountModel.getVirtualPasswd().equals(password)){
				return true;
			}else{
				return false;
			}
		}
		
		return false;
	}
	
}
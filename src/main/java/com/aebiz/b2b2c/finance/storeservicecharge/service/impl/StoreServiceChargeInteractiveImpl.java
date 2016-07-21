package com.aebiz.b2b2c.finance.storeservicecharge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.finance.storeservicecharge.dao.StoreServiceChargeDAO;
import com.aebiz.b2b2c.finance.storeservicecharge.service.StoreServiceChargeInteractive;
import com.aebiz.b2b2c.finance.storeservicecharge.service.StoreServiceChargeService;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeModel;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeQueryModel;


@Service
@Transactional
public class StoreServiceChargeInteractiveImpl extends BaseServiceImpl<StoreServiceChargeModel, StoreServiceChargeQueryModel> implements StoreServiceChargeInteractive {
	
	private StoreServiceChargeDAO myDao = null;
	@Autowired
	public void setMyDao(StoreServiceChargeDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	
	@Autowired
	private StoreServiceChargeService storeServiceChargeService;

	/**
	 * 添加完合同后生成的商户服务单据,供商户去支付
	 * @param accountUuid 商户uuid
	 * @param contract 合同uuid
	 * @param amount 服务费金额
	 * void
	 */
	public void createServiceChargeByContract(String accountUuid,String contract,float amount){
		storeServiceChargeService.createServiceChargeByContract(accountUuid, contract, amount);
	}
}
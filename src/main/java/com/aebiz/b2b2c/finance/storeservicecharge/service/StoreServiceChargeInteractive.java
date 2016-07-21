package com.aebiz.b2b2c.finance.storeservicecharge.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeModel;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeQueryModel;

public interface StoreServiceChargeInteractive extends
		BaseService<StoreServiceChargeModel, StoreServiceChargeQueryModel> {

	/**
	 * 添加完合同后生成的商户服务单据,供商户去支付
	 * @param accountUuid 商户uuid
	 * @param contract 合同uuid
	 * @param amount 服务费金额
	 * void
	 */
	public void createServiceChargeByContract(String accountUuid,String contract,float amount);
}

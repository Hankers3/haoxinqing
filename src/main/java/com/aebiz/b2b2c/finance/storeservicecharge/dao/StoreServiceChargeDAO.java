package com.aebiz.b2b2c.finance.storeservicecharge.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeModel;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeQueryModel;

public interface StoreServiceChargeDAO
		extends
			BaseDAO<StoreServiceChargeModel, StoreServiceChargeQueryModel> {

	/**
	 * 查询当前商户的未支付的服务单据
	 * @param accountUuid
	 * @return 
	 * StoreServiceChargeModel
	 */
	public StoreServiceChargeModel getUnderPayServiceCharge(String accountUuid);
	
	
	public String getUnderPayServiceChargeUuid(String accountUuid);
}
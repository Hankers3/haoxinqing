package com.aebiz.b2b2c.finance.storeinvoiceset.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetModel;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetQueryModel;

public interface StoreInvoiceSetDAO extends BaseDAO<StoreInvoiceSetModel,StoreInvoiceSetQueryModel>{

	/**
	 * 根据商户uuid获取商户设置的发票
	 * @param accountUuid
	 * @return
	 */
	public StoreInvoiceSetModel getStoreInvoiceSetWebModelByAccoutUuid(String accountUuid);
	
	public String getStoreInvoiceSetWebUuidByAccoutUuid(String accountUuid);
}
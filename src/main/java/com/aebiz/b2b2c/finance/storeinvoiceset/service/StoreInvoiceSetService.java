package com.aebiz.b2b2c.finance.storeinvoiceset.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetModel;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetQueryModel;

public interface StoreInvoiceSetService extends BaseService<StoreInvoiceSetModel,StoreInvoiceSetQueryModel>{
	 
	public StoreInvoiceSetModel getStoreInvoiceSetWebModelByAccoutUuid(String accountUuid);
}

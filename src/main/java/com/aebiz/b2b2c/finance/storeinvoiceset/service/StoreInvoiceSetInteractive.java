package com.aebiz.b2b2c.finance.storeinvoiceset.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetModel;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetQueryModel;
/**
 * 商户设置对外接口
 *
 * @author tangyunkai
 *
 * @date 2014年12月8日 下午8:33:52 
 *
 */
public interface StoreInvoiceSetInteractive extends BaseService<StoreInvoiceSetModel, StoreInvoiceSetQueryModel> {
	
	/**
	 * 通过商户Uuid获取商户发票类型，发票种类，发票内容
	 * @param accountUuid
	 * @return
	 */
	public StoreInvoiceSetModel getStoreInvoiceSetModelByAccountUuid(String accountUuid);
}

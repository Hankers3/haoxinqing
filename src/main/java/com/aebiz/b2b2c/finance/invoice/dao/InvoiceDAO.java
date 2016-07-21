package com.aebiz.b2b2c.finance.invoice.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceModel;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceQueryModel;

public interface InvoiceDAO extends BaseDAO<InvoiceModel, InvoiceQueryModel> {

	/**
	 * 根据订单编号获得订单的发票信息
	 * 
	 * 一个订单可能有多个发票信息，因此获得的是一个LIST集合
	 * 
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<InvoiceModel> getInvoiceListByOrderId(String orderUuid);

        public List<String> getInvoiceUuidsByOrderId(String orderMainUuid);
}
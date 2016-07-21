package com.aebiz.b2b2c.finance.invoice.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceModel;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceQueryModel;

public interface InvoiceService extends BaseService<InvoiceModel, InvoiceQueryModel> {

	/**
	 * 根据订单编号获得订单的发票信息
	 * 一个订单可能有多个发票信息，因此获得的是一个LIST集合
	 * @param orderUuid
	 * @return
	 */
	public List<InvoiceModel> getInvoiceListByOrderId(String orderUuid);
	
	/**
	 * 保存一条发票记录
	 * @param invoiceModel
	 */
	public void saveInvoiceModel(InvoiceModel invoiceModel);
	
	/**
	 * 更新一条发票记录
	 * @param invoiceModel
	 */
	public void updateInvoiceModel(InvoiceModel invoiceModel);
	
	/**
	 * 根据Uuid查询出一条发票记录
	 * @param invoiceUuid
	 * @return InvoiceModel
	 */
	public InvoiceModel getByInvoiceUuid(String invoiceUuid);
	
	/**
	 * 保存一条发票记录
	 * @param invoiceCate
	 * @param invoiceType
	 * @param invoiceNO
	 * @param invoiceContent
	 */
	public void save( String invoiceCate, String invoiceType,String invoiceNO, String[] invoiceContents);
	
	/**
	 * 将数组转化为String
	 * @param array
	 * @return
	 */
	public String arrayToString(String[] array);
}

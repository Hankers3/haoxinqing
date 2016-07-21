package com.aebiz.b2b2c.order.ordermainpay.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayModel;
import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayQueryModel;

public interface OrderMainPayService extends
		BaseService<OrderMainPayModel, OrderMainPayQueryModel> {

	/**
	 * 获得订单的第一步支付信息
	 * 
	 * 如果是预售订单，则第一笔信息为首付款
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderMainPayModel getAdvanceOrderMainPayModel(String orderId);
}

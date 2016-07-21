package com.aebiz.b2b2c.order.ordermainpay.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayModel;
import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayQueryModel;

public interface OrderMainPayDAO extends
		BaseDAO<OrderMainPayModel, OrderMainPayQueryModel> {

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
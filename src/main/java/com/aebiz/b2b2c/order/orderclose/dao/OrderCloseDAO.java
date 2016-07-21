package com.aebiz.b2b2c.order.orderclose.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.orderclose.vo.OrderCloseModel;
import com.aebiz.b2b2c.order.orderclose.vo.OrderCloseQueryModel;

public interface OrderCloseDAO extends
		BaseDAO<OrderCloseModel, OrderCloseQueryModel> {

	/**
	 * 根据订单编号查询订单的关闭消息
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderCloseModel getOrderCloseModelByOrderUuid(String orderUuid);
}
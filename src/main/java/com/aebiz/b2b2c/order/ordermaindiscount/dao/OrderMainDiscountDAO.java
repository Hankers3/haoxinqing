package com.aebiz.b2b2c.order.ordermaindiscount.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.ordermaindiscount.vo.OrderMainDiscountModel;
import com.aebiz.b2b2c.order.ordermaindiscount.vo.OrderMainDiscountQueryModel;

public interface OrderMainDiscountDAO extends
		BaseDAO<OrderMainDiscountModel, OrderMainDiscountQueryModel> {

	/**
	 * 根据订单编号获得订单的促销信息
	 * 
	 * 订单的促销信息单独存储在这张表中，例如订单优惠的金额，参与活动的编号等促销信息
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	public OrderMainDiscountModel getOrderMainDiscountModelByOrderId(
			String orderMainUuid);
}
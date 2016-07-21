package com.aebiz.b2b2c.order.orderdetaildiscount.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.orderdetaildiscount.vo.OrderDetailDiscountModel;
import com.aebiz.b2b2c.order.orderdetaildiscount.vo.OrderDetailDiscountQueryModel;

public interface OrderDetailDiscountDAO extends
		BaseDAO<OrderDetailDiscountModel, OrderDetailDiscountQueryModel> {

	/**
	 * 根据订单明细查询订单明细对应的促销信息
	 * 
	 * @param detailUuid
	 * @return
	 */
	public OrderDetailDiscountModel getOrderDetailDiscountModelByDetailUuid(
			String detailUuid);
}
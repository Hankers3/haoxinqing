package com.aebiz.b2b2c.order.orderdetaildiscount.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.order.orderdetaildiscount.vo.OrderDetailDiscountModel;
import com.aebiz.b2b2c.order.orderdetaildiscount.vo.OrderDetailDiscountQueryModel;

/**
 * 订单促销信息
 * 
 * 订单促销信息记录整个订单的整体促销信息，比如促销的优惠类型，优惠的金额，优惠活动的编号等针对整个订单的促销
 * 
 * @author duandeyi
 * 
 */
public interface OrderDetailDiscountService extends
		BaseService<OrderDetailDiscountModel, OrderDetailDiscountQueryModel> {

	/**
	 * 根据订单明细查询订单明细对应的促销信息
	 * 
	 * @param detailUuid
	 * @return
	 */
	public OrderDetailDiscountModel getOrderDetailDiscountModelByDetailUuid(
			String detailUuid);
}

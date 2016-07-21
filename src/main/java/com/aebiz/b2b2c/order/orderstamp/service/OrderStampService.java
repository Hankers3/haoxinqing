package com.aebiz.b2b2c.order.orderstamp.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampModel;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampQueryModel;

public interface OrderStampService extends
		BaseService<OrderStampModel, OrderStampQueryModel> {

	/**
	 * 
	 * 通过订单ID获得订单标记
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderStampModel getOrderStampModelByOrderUuid(String orderUuid);
	
	/**
	 * 
	 * 通过confId获取录音信息
	 * 
	 * @param confId
	 * @return
	 */
	public OrderStampModel getOrderStampByConfId(String confId);
	
}

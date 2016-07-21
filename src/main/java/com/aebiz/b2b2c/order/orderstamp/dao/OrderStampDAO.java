package com.aebiz.b2b2c.order.orderstamp.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampModel;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampQueryModel;

public interface OrderStampDAO extends
		BaseDAO<OrderStampModel, OrderStampQueryModel> {

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
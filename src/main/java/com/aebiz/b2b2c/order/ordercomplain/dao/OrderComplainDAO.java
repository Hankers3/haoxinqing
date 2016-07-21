package com.aebiz.b2b2c.order.ordercomplain.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainModel;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainQueryModel;

public interface OrderComplainDAO extends
		BaseDAO<OrderComplainModel, OrderComplainQueryModel> {
	/**
	 * 根据订单获取订单是否已投诉
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	public boolean getComplainState(String orderMainUuid );
	
	
	/**
	 * 获得订单投诉对象
	 * @param orderMainUuid
	 * @return
	 */
	public OrderComplainModel getOrderComplain(String orderMainUuid);
	
	/**
	 * 根据订单获取订单投诉状态
	 * @param orderMainUuid
	 * @return
	 */
	public String getState(String orderMainUuid);
}
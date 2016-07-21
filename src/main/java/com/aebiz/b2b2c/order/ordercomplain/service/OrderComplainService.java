package com.aebiz.b2b2c.order.ordercomplain.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainModel;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainQueryModel;

public interface OrderComplainService extends
		BaseService<OrderComplainModel, OrderComplainQueryModel> {
	
	/**
	 * 创建订单投诉信息
	 * 
	 * @param orderMainUuid
	 * @param note
	 * @param customerUuid
	 * @param operMan
	 */
	public void createOrderComplain(String orderMainUuid, String content,
			String customerUuid, String operMan);
	
	/**
	 * 根据订单获取订单是否已投诉
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	public String getComplainState(String orderMainUuid );
	
	
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

package com.aebiz.b2b2c.order.orderclose.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.order.orderclose.vo.OrderCloseModel;
import com.aebiz.b2b2c.order.orderclose.vo.OrderCloseQueryModel;

/**
 * 订单生成后，可由以下几种情况关闭：
 * 
 * 1.会员主动关闭<br />
 * 2.商户主动关闭<br />
 * 3.订单超时自动关闭<br />
 * 
 * 订单关闭需要记录关闭人和关闭原因
 * 
 * @author duandeyi
 * 
 */
public interface OrderCloseService extends
		BaseService<OrderCloseModel, OrderCloseQueryModel> {

	/**
	 * 根据订单编号查询订单的关闭消息
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderCloseModel getOrderCloseModelByOrderUuid(String orderUuid);

	/**
	 * 创建订单关闭信息
	 * 
	 * @param orderId
	 * @param operMan
	 * @param reason
	 * @return
	 */
	public String createOrderClose(String orderId, String operMan, String reason);
}

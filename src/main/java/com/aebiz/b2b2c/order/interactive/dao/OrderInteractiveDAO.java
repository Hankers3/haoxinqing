package com.aebiz.b2b2c.order.interactive.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.interactive.vo.OrderInteractiveModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;

public interface OrderInteractiveDAO extends
		BaseDAO<OrderMainModel, OrderMainQueryModel> {

	/**
	 * 查询订单列表的总数量
	 * 
	 * 1.目前可由发货系统调用待发货订单 <br />
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm);

	/**
	 * 查询订单列表
	 * 
	 * 1.目前可由发货系统调用待发货订单 <br />
	 * 
	 * @param paramQM
	 * @param paramInt1
	 * @param paramInt2
	 * 
	 * @return 返回的是一个复合对象，包含了订单明细的对象和订单收货地址的信息
	 */
	public List<OrderInteractiveModel> getOrderListByCondition(
			OrderMainQueryModel paramQM, int paramInt1, int paramInt2);

	/**
	 * 
	 * 通过订单编号获得订单明细列表
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<OrderDetailModel> getOrderDetailListByOrderUuid(String orderUuid);
}

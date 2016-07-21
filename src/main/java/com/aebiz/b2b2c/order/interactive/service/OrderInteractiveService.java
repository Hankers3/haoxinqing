package com.aebiz.b2b2c.order.interactive.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.interactive.vo.OrderInteractiveModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressModel;

/**
 * 订单系统对外提供的公共接口
 * 
 * 
 * @author duandeyi
 * 
 */
public interface OrderInteractiveService extends
		BaseService<OrderMainModel, OrderMainQueryModel> {

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
			OrderMainQueryModel paramQM, int start, int rowsPerPage);

	/**
	 * 
	 * 通过订单编号获得订单明细列表
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<OrderDetailModel> getOrderDetailListByOrderUuid(String orderUuid);

	/**
	 * 更新订单状态
	 * 
	 * 例如：物流系统发货，需要更新订单的状态为已发货，需要记录发货人以及发货备注<br />
	 * 
	 * @param orderUuid
	 * @param updateStatus
	 * @param oper
	 * @param note
	 */
	public void updateOrderState(String orderUuid, String updateStatus,
			String oper, String note);

	/**
	 * 通过订单编号获得订单的收货地址
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressModelByUuid(String orderUuid);

	/**
	 * 通过订单明细的编号查询订单明细对象
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public OrderDetailModel getOrderDetailModelByDetailUuid(
			String orderDetailUuid);

}

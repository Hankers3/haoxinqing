package com.aebiz.b2b2c.order.orderdetail.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailQueryModel;

public interface OrderDetailService extends
		BaseService<OrderDetailModel, OrderDetailQueryModel> {

	/**
	 * 通过订单编号查询订单明细列表
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<OrderDetailModel> getOrderDetailModelByOrderId(String orderUuid);

	/**
	 * 通过订单编号查询订单的明细，并且需要根据订单明细的列表，查询订单明细的晒单情况
	 * 
	 */
	public List<OrderDetailModel> getOrderDetailAndOrderShowByOrderId(
			String orderUuid);

	/**
	 * 通过订单编号查询订单的明细，并且需要根据订单明细的列表，查询订单明细的消费者保护情况
	 * 
	 */
	public List<OrderDetailModel> getOrderDetailAndConsumerProtectionOrderId(
			String orderUuid);

	/**
	 * 退换货的状态修改后，需要同步更新订单明细的退换货状态进行展示
	 * 
	 * @param detailUuid
	 * @param afterServiceState
	 */
	public void updateOrderDetailState(String detailUuid,
			String afterServiceState);
	
	/**统计商品数量
	 * hedongfei
	 * @param 
	 * @param 
	 * @return
	 */
	public int getSumProductNumber(String productName,String searchType,String receiveTime);
	
	/**
	 * 获取该会员订购该医生的套餐信息
	 * @param customerUuid
	 * @param doctorUuid
	 * @param duration
	 * @return
	 */
	public OrderDetailModel getOrderDetailModel(String customerUuid ,String doctorUuid,String duration);
}

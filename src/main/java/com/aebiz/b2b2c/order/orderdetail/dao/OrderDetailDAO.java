package com.aebiz.b2b2c.order.orderdetail.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailQueryModel;

public interface OrderDetailDAO extends
		BaseDAO<OrderDetailModel, OrderDetailQueryModel> {

	/**
	 * 通过订单编号查询订单明细列表
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<OrderDetailModel> getOrderDetailModelByOrderId(String orderUuid);

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
package com.aebiz.b2b2c.order.servicestaffcenter.dao.ordermain;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;

public interface OrderMainUcenterDAO extends
		BaseDAO<OrderMainModel, OrderMainQueryModel> {

	/**
	 * 会员中心将订单添加到回收站
	 * 
	 * 正常状态的订单delflag=1，回收站delFalg=0,逻辑删除delFlag=2
	 * 
	 * @param orderId
	 */
	public void putOrderToRecycler(String orderId);

	/**
	 * 会员中心将订单添加到回收站
	 * 
	 * 正常状态的订单delflag=1，回收站delFalg=0,逻辑删除delFlag=2
	 * 
	 * @param orderId
	 */
	public void removeOrderForever(String orderId);

	/**
	 * 会员中心将订单添加到回收站
	 * 
	 * 正常状态的订单delflag=1，回收站delFalg=0,逻辑删除delFlag=2
	 * 
	 * @param orderId
	 */
	public void recovery(String orderId);

	/**
	 * 会员中心取消订单，记录取消原因 并，需要记录日志
	 * 
	 * @param orderId
	 * @param cancelReason
	 * @param oper
	 */
	public void cancelOrder(String orderId, String cancelReason);

	/**
	 * 会员中心订单查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm);

	/**
	 * 会员中心查询订单列表
	 * 
	 * @param paramQM
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List<String> getOrderListByCondition(OrderMainQueryModel paramQM,
			int paramInt1, int paramInt2);

	/**
	 * 根据会员id和订单类型查询总数量
	 * @param OrderMainQueryModel
	 * @param state 订单状态
	 * @return
	 */
	public int getOrderCountByCustomerId(OrderMainQueryModel qm,String serviceStaffUuid,String state);
}
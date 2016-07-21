package com.aebiz.b2b2c.order.ordermainlog.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogModel;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogQueryModel;

public interface OrderMainLogDAO extends
		BaseDAO<OrderMainLogModel, OrderMainLogQueryModel> {
	/**
	 * 通过订单号获得订单日志列表
	 * 
	 * 按照日志时间倒序排序
	 * 
	 * @param uuid
	 * @return
	 */
	public List<OrderMainLogModel> getOrderMainLogModelListByOrderId(
			String orderUuid);
	
	/**
	 * 通过订单号获得订单日志列表
	 * 
	 * 按照日志时间倒序排序
	 * 
	 * @param uuid
	 * @return
	 */
	public List<OrderMainLogModel> getOrderMainLogModelListByOrderId(
			String orderUuid,String operateType);
	
	
	
	/**
	 * 获取当前订单是否已被客服抢单处理权
	 * 
	 * @param orderMainUuid
	 * @param ugencyState
	 * @return
	 */
	public List<OrderMainLogModel> getOrderMainLogs(String orderMainUuid,String ugencyState);
	
	/**
	 * 获得订单日志信息
	 * 
	 * @param orderMainUuid
	 * @param operateType
	 * @return
	 */
	public OrderMainLogModel getLogModel(String orderMainUuid,String operateType);
}
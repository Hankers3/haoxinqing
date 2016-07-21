package com.aebiz.b2b2c.order.ordermainlog.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogModel;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogQueryModel;

public interface OrderMainLogService extends
		BaseService<OrderMainLogModel, OrderMainLogQueryModel> {

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
	 * 订单操作日志创建
	 * 
	 * 需要具体设置订单号、操作明细以及操作类型
	 * 
	 * @param orderMainUuid
	 *            订单号
	 * @param note
	 *            备注
	 * @param orderOperType
	 *            操作类型
	 * @param operMan
	 *            操作人
	 * @param operManType
	 *            操作人类型
	 */
	public void createOrderLog(String orderMainUuid, String note,
			String orderOperType, String operMan, String operManType);
	
	/**
	 * 订单操作日志创建
	 * 
	 * 需要具体设置订单号、操作明细以及操作类型  update by xl 20150511
	 * 
	 * @param orderMainUuid
	 *            订单号
	 * @param note
	 *            备注
	 * @param orderOperType
	 *            操作类型
	 * @param operMan
	 *            操作人
	 * @param operManType
	 *            操作人类型
	 * @param operateType
	 *            操作类型
	 * @param orderState
	 *            订单状态
	 */
	public void createOrderLog(String orderMainUuid, String note,
			String orderOperType, String operMan, String operManType,
			String operateType,String orderState,String payType);
	
	/**
	 * 订单操作日志创建 
	 * 
	 * 需要具体设置订单号、操作明细以及操作类型
	 * 
	 * @param orderMainUuid
	 *            订单号
	 * @param note
	 *            备注
	 * @param orderOperType
	 *            操作类型
	 * @param operMan
	 *            操作人
	 * @param operManType
	 *            操作人类型
	 * @param operManType
	 *            操作类型
	 * @param ugencyState
	 *            
	 */
	public void createOrderServiceLog(String orderMainUuid, String note,
			String orderOperType, String operMan, String operateType,String ugencyState,String orderState,String payType);
	/**
	 * 获取是否存在不是当前客服抢到的处理订单
	 * 
	 * @param orderMainUuid
	 * @param operMan
	 * @param ugencyState
	 * @return
	 */
	public OrderMainLogModel isExitUgencyState(String orderMainUuid,String ugencyState);
	
	/**
	 * 获得订单日志信息
	 * 
	 * @param orderMainUuid
	 * @param operateType
	 * @return
	 */
	public OrderMainLogModel getLogModel(String orderMainUuid,String operateType);
}

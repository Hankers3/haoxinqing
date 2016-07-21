package com.aebiz.b2b2c.order.ordermainlog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.ordermainlog.dao.OrderMainLogDAO;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogModel;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogQueryModel;

@Service
@Transactional
public class OrderMainLogServiceImpl extends
		BaseServiceImpl<OrderMainLogModel, OrderMainLogQueryModel> implements
		OrderMainLogService {
	private OrderMainLogDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OrderMainLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderMainLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	/**
	 * 订单操作日志创建
	 * 
	 * 需要具体设置订单号、操作明细以及操作类型
	 * 
	 * @param orderMainUuid
	 * @param note
	 * @param orderOperType
	 */
	public void createOrderLog(String orderMainUuid, String note,
			String orderOperType, String operMan, String operManType) {

		OrderMainLogModel m = new OrderMainLogModel();
		m.setOrderMainUuid(orderMainUuid);
		m.setNote(note);
		m.setOpeType(orderOperType);
		
		m.setOrderOperType(orderOperType);
		
		m.setOrderOper(operMan);
		m.setOrderOpeTime(DateFormatHelper.getNowTimeStr());

		this.create(m);
	}

	
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
			String operateType,String orderState,String payType) {

		OrderMainLogModel m = new OrderMainLogModel();
		//订单编号
		m.setOrderMainUuid(orderMainUuid);
		//订单备注
		m.setNote(note);
		//操作人类型
		m.setOpeType(orderOperType);
		//操作人类型
		m.setOrderOperType(orderOperType);
		//操作人
		m.setOrderOper(operMan);
		//操作时间
		m.setOrderOpeTime(DateFormatHelper.getNowTimeStr());
		//订单的操作类型
		m.setOperateType(operateType);
		//订单状态
		m.setOrderState(orderState);
		//支付類型
		m.setPayType(payType);
		this.create(m);
	}
	@Override
	public void update(OrderMainLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderMainLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过订单号获得订单日志列表  --客服添加的订单记录
	 * 
	 * 按照日志时间倒序排序
	 * 
	 * @param uuid
	 * @return
	 */
	public List<OrderMainLogModel> getOrderMainLogModelListByOrderId(
			String orderUuid) {
		return myDao.getOrderMainLogModelListByOrderId(orderUuid);
	}
	
	/**
	 * 通过订单号获得订单日志列表
	 * 
	 * 按照日志时间倒序排序
	 * 
	 * @param uuid
	 * @return
	 */
	public List<OrderMainLogModel> getOrderMainLogModelListByOrderId(
			String orderUuid,String operateType) {
		return myDao.getOrderMainLogModelListByOrderId(orderUuid,operateType);
	}
	
	
	/**
	 * 订单操作日志创建---- 客服处理紧急订单
	 * 
	 * 需要具体设置订单号、操作明细以及操作类型
	 * 
	 * @param orderMainUuid
	 * @param note
	 * @param orderOperType
	 */
	@Override
	public void createOrderServiceLog(String orderMainUuid, String note,
			String orderOperType, String operMan, String operateType,
			String ugencyState,String orderState,String payType) {
	
		OrderMainLogModel m = new OrderMainLogModel();
		m.setOrderMainUuid(orderMainUuid);
		m.setNote(note);
		m.setOpeType(orderOperType);
		
		m.setOrderOperType(orderOperType);
		
		m.setOperateType(operateType);
		m.setOrderOper(operMan);
		m.setOrderOpeTime(DateFormatHelper.getNowTimeStr());
		m.setUgencyState(ugencyState);
		
		m.setOrderState(orderState);
		m.setPayType(payType);
		
		this.create(m);
	}
	
	/**
	 * 获取是否存在不是当前客服抢到的处理订单
	 * 
	 * @param orderMainUuid
	 * @param operMan
	 * @param ugencyState
	 * @return
	 */ 
	@Override
	public OrderMainLogModel isExitUgencyState(String orderMainUuid,String ugencyState) {
		List<OrderMainLogModel> list = myDao.getOrderMainLogs(orderMainUuid, ugencyState);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 获得订单日志信息
	 * 
	 * @param orderMainUuid
	 * @param operateType
	 * @return
	 */
	@Override
	public OrderMainLogModel getLogModel(String orderMainUuid,
			String operateType) {
		
		return myDao.getLogModel(orderMainUuid, operateType);
	}
}
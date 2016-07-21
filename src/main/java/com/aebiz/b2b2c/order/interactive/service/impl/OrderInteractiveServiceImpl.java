package com.aebiz.b2b2c.order.interactive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.exception.AppException;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.order.interactive.dao.OrderInteractiveDAO;
import com.aebiz.b2b2c.order.interactive.service.OrderInteractiveService;
import com.aebiz.b2b2c.order.interactive.vo.OrderInteractiveModel;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.ordermainaddress.service.OrderMainAddressService;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressModel;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderLogOperateEnum;
//import com.aebiz.b2b2c.orderaftersaleservice.orderaftersaleservicemain.vo.PlatFormRoleTypeEnum;

@Service
@Transactional
public class OrderInteractiveServiceImpl extends
		BaseServiceImpl<OrderMainModel, OrderMainQueryModel> implements
		OrderInteractiveService {

	private OrderInteractiveDAO myDao = null;

	@Autowired
	private OrderMainLogService orderMainLogService;

	@Autowired
	private OrderMainService orderMainService;

	@Autowired
	private OrderMainAddressService orderMainAddressService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	public void setMyDao(OrderInteractiveDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 查询订单列表的总数量
	 * 
	 * 1.目前可由发货系统调用待发货订单 <br />
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm) {
		return myDao.getOrderCount(qm);
	}

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
			OrderMainQueryModel paramQM, int startRow, int rowsPerPage) {
		return myDao.getOrderListByCondition(paramQM, startRow, rowsPerPage);
	}

	/**
	 * 
	 * 通过订单编号获得订单明细列表
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<OrderDetailModel> getOrderDetailListByOrderUuid(String orderUuid) {
		return myDao.getOrderDetailListByOrderUuid(orderUuid);
	}

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
			String oper, String note) {

		// 1.更新订单状态
		OrderMainModel omm = orderMainService.getByUuid(orderUuid);
		if (omm == null) {
			throw new AppException("order.no.existed");
		}

		// 如果是发货，则记录发货的时间
		/*if (OrderStatusEnum.SHIPPING.getValue().equals(updateStatus)) {
			omm.setSendTime(DateFormatHelper.getNowTimeStr());
		}*/

		omm.setState(updateStatus);
		orderMainService.update(omm);

		// 2.记录订单的日志
		// 记录订单操作日志
/*		orderMainLogService.createOrderLog(orderUuid, note,
				OrderLogOperateEnum.UPDATESTATE.getValue(),
				LoginUserHelper.getStoreLoginUserUuid(),
				PlatFormRoleTypeEnum.STORE.getValue());*/
	}

	/**
	 * 通过订单编号获得订单的收货地址
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressModelByUuid(String orderUuid) {
		return orderMainAddressService
				.getOrderMainAddressModelByUuid(orderUuid);
	}

	/**
	 * 通过订单明细的编号查询订单明细对象
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public OrderDetailModel getOrderDetailModelByDetailUuid(
			String orderDetailUuid) {
		return orderDetailService.getByUuid(orderDetailUuid);
	}

}

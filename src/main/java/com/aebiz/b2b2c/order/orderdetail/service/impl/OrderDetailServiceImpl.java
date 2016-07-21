package com.aebiz.b2b2c.order.orderdetail.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.consumerprotection.service.ConsumerProtectionService;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.ordershow.service.OrderShowService;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowModel;
import com.aebiz.b2b2c.order.orderdetail.dao.OrderDetailDAO;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailQueryModel;

@Service
@Transactional
public class OrderDetailServiceImpl extends
		BaseServiceImpl<OrderDetailModel, OrderDetailQueryModel> implements
		OrderDetailService {
	private OrderDetailDAO myDao = null;

	@Autowired
	private OrderShowService orderShowService;

	@Autowired
	private UuidService us;

	@Autowired
	private ConsumerProtectionService consumerProtectionSercice;

	@Autowired
	public void setMyDao(OrderDetailDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderDetailModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderDetailModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过订单编号查询订单明细列表
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<OrderDetailModel> getOrderDetailModelByOrderId(String orderUuid) {
		return myDao.getOrderDetailModelByOrderId(orderUuid);
	}

	/**
	 * 通过订单编号查询订单的明细，并且需要根据订单明细的列表，查询订单明细的晒单情况
	 * 
	 */
	public List<OrderDetailModel> getOrderDetailAndOrderShowByOrderId(
			String orderUuid) {
		// 获得订单明细列表
		List<OrderDetailModel> detailList = this
				.getOrderDetailModelByOrderId(orderUuid);

		// 根据订单明细列表，获得订单明细对应的晒单情况
		List<OrderDetailModel> returnList = new ArrayList<OrderDetailModel>();
		for (OrderDetailModel od : detailList) {
			OrderShowModel osm = orderShowService
					.getOrderShowModelAndPicsByDetailUuid(od.getUuid());

			returnList.add(od);
		}
		return returnList;
	}

	/**
	 * 通过订单编号查询订单的明细，并且需要根据订单明细的列表，查询订单明细的消费者保护情况
	 * 
	 */
	public List<OrderDetailModel> getOrderDetailAndConsumerProtectionOrderId(
			String orderUuid) {

		// 获得订单明细列表
		List<OrderDetailModel> detailList = this
				.getOrderDetailModelByOrderId(orderUuid);
		return detailList;
	}

	/**
	 * 退换货的状态修改后，需要同步更新订单明细的退换货状态进行展示
	 * 
	 * @param detailUuid
	 * @param afterServiceState
	 */
	public void updateOrderDetailState(String detailUuid,
			String afterServiceState) {
		myDao.updateOrderDetailState(detailUuid, afterServiceState);
	}

	
	/**统计商品数量
	 * hedongfei
	 * @param 
	 * @param 
	 * @return
	 */
	@Override
	public int getSumProductNumber(String productName,String searchType,String receiveTime) {
		return myDao.getSumProductNumber(productName,searchType,receiveTime);
	}
	
	
	/**
	 * 获取该会员订购该医生的套餐信息
	 * @param customerUuid
	 * @param doctorUuid
	 * @param duration
	 * @return
	 */
	@Override
	public OrderDetailModel getOrderDetailModel(String customerUuid,
			String doctorUuid, String duration) {
		
		return null;
	}
}
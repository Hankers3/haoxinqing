package com.aebiz.b2b2c.order.orderstamp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.orderstamp.dao.OrderStampDAO;
import com.aebiz.b2b2c.order.orderstamp.service.OrderStampService;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampModel;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampQueryModel;

@Service
@Transactional
public class OrderStampServiceImpl extends
		BaseServiceImpl<OrderStampModel, OrderStampQueryModel> implements
		OrderStampService {
	private OrderStampDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OrderStampDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderStampModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderStampModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderStampModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 
	 * 通过订单ID获得订单标记
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderStampModel getOrderStampModelByOrderUuid(String orderUuid) {
		return myDao.getOrderStampModelByOrderUuid(orderUuid);
	}
	
	/**
	 * 
	 * 通过confId获取录音信息
	 * 
	 * @param confId
	 * @return
	 */
	@Override
	public OrderStampModel getOrderStampByConfId(String confId) {
		
		return myDao.getOrderStampByConfId(confId);
	}

}
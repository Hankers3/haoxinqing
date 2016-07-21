package com.aebiz.b2b2c.order.ordermaindiscount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.ordermaindiscount.dao.OrderMainDiscountDAO;
import com.aebiz.b2b2c.order.ordermaindiscount.service.OrderMainDiscountService;
import com.aebiz.b2b2c.order.ordermaindiscount.vo.OrderMainDiscountModel;
import com.aebiz.b2b2c.order.ordermaindiscount.vo.OrderMainDiscountQueryModel;

@Service
@Transactional
public class OrderMainDiscountServiceImpl extends
		BaseServiceImpl<OrderMainDiscountModel, OrderMainDiscountQueryModel>
		implements OrderMainDiscountService {
	private OrderMainDiscountDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OrderMainDiscountDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderMainDiscountModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderMainDiscountModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderMainDiscountModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据订单编号获得订单的促销信息
	 * 
	 * 订单的促销信息单独存储在这张表中，例如订单优惠的金额，参与活动的编号等促销信息
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	public OrderMainDiscountModel getOrderMainDiscountModelByOrderId(
			String orderMainUuid) {
		return myDao.getOrderMainDiscountModelByOrderId(orderMainUuid);
	}

}
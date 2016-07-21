package com.aebiz.b2b2c.order.ordermainpay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.ordermainpay.dao.OrderMainPayDAO;
import com.aebiz.b2b2c.order.ordermainpay.service.OrderMainPayService;
import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayModel;
import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayQueryModel;

@Service
@Transactional
public class OrderMainPayServiceImpl extends
		BaseServiceImpl<OrderMainPayModel, OrderMainPayQueryModel> implements
		OrderMainPayService {
	private OrderMainPayDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OrderMainPayDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderMainPayModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderMainPayModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderMainPayModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 获得订单的第一步支付信息
	 * 
	 * 如果是预售订单，则第一笔信息为首付款
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderMainPayModel getAdvanceOrderMainPayModel(String orderId) {
		return myDao.getAdvanceOrderMainPayModel(orderId);
	}
}
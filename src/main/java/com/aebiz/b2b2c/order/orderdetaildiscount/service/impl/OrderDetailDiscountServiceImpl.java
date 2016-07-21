package com.aebiz.b2b2c.order.orderdetaildiscount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.orderdetaildiscount.dao.OrderDetailDiscountDAO;
import com.aebiz.b2b2c.order.orderdetaildiscount.service.OrderDetailDiscountService;
import com.aebiz.b2b2c.order.orderdetaildiscount.vo.OrderDetailDiscountModel;
import com.aebiz.b2b2c.order.orderdetaildiscount.vo.OrderDetailDiscountQueryModel;

@Service
@Transactional
public class OrderDetailDiscountServiceImpl
		extends
		BaseServiceImpl<OrderDetailDiscountModel, OrderDetailDiscountQueryModel>
		implements OrderDetailDiscountService {
	private OrderDetailDiscountDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OrderDetailDiscountDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderDetailDiscountModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	/**
	 * 根据订单明细查询订单明细对应的促销信息
	 * 
	 * @param detailUuid
	 * @return
	 */
	public OrderDetailDiscountModel getOrderDetailDiscountModelByDetailUuid(
			String detailUuid) {
		return myDao.getOrderDetailDiscountModelByDetailUuid(detailUuid);
	}
}
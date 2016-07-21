package com.aebiz.b2b2c.order.orderrevisit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.orderrevisit.dao.OrderRevisitDAO;
import com.aebiz.b2b2c.order.orderrevisit.service.OrderRevisitService;
import com.aebiz.b2b2c.order.orderrevisit.vo.OrderRevisitModel;
import com.aebiz.b2b2c.order.orderrevisit.vo.OrderRevisitQueryModel;

@Service
@Transactional
public class OrderRevisitServiceImpl extends BaseServiceImpl<OrderRevisitModel,OrderRevisitQueryModel> implements OrderRevisitService {
	private OrderRevisitDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(OrderRevisitDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderRevisitModel m) {
		m.setUuid(us.getNextUuid("OrderRevisit",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(OrderRevisitModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(OrderRevisitModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
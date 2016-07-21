package com.aebiz.b2b2c.order.ordercomplain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.ordercomplain.dao.OrderComplainDAO;
import com.aebiz.b2b2c.order.ordercomplain.service.OrderComplainService;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainModel;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainQueryModel;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogModel;

@Service
@Transactional
public class OrderComplainServiceImpl extends BaseServiceImpl<OrderComplainModel,OrderComplainQueryModel> implements OrderComplainService {
	private OrderComplainDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(OrderComplainDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderComplainModel m) {
		m.setUuid(us.getNextUuid("OrderComplain",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(OrderComplainModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(OrderComplainModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 创建订单投诉信息
	 * 
	 * @param orderMainUuid
	 * @param note
	 * @param customerUuid
	 * @param operMan
	 */
	@Override
	public void createOrderComplain(String orderMainUuid, String content,
			String customerUuid, String operMan) {
		OrderComplainModel m = new OrderComplainModel();
		m.setOrderMainUuid(orderMainUuid);
		m.setCommentTime(DateFormatHelper.getNowTimeStr());
		m.setContent(content);
		m.setManagerUuid(operMan);
		m.setCustomerUuid(customerUuid);
		this.create(m);
		
	}

	@Override
	public String getComplainState(String orderMainUuid) {
		boolean state = myDao.getComplainState(orderMainUuid);
		String flag="0";
		if(state){
			flag="1";
		}
		return flag;
	}
	
	/**
	 * 获得订单投诉对象
	 * @param orderMainUuid
	 * @return
	 */
	@Override
	public OrderComplainModel getOrderComplain(String orderMainUuid) {
		
		return myDao.getOrderComplain(orderMainUuid);
	}

	@Override
	public String getState(String orderMainUuid) {
		
		return myDao.getState(orderMainUuid);
	}
}
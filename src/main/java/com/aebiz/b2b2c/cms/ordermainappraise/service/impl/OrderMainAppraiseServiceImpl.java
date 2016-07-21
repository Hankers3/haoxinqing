package com.aebiz.b2b2c.cms.ordermainappraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.cms.ordermainappraise.service.OrderMainAppraiseService;
import com.aebiz.b2b2c.cms.ordermainappraise.dao.OrderMainAppraiseDAO;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseModel;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseQueryModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;

@Service
@Transactional
public class OrderMainAppraiseServiceImpl extends BaseServiceImpl<OrderMainAppraiseModel,OrderMainAppraiseQueryModel> implements OrderMainAppraiseService {
	private OrderMainAppraiseDAO myDao = null;
	@Autowired
	private UuidService us;
	
	@Autowired
	private CustomerInteractive customerInteractive;
	
	@Autowired
	public void setMyDao(OrderMainAppraiseDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderMainAppraiseModel m) {
		m.setUuid(us.getNextUuid("OrderMainAppraise",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		m.setCustomerUuid(LoginUserHelper.getCustomerLoginUserUuid());
		CustomerModel cm = this.customerInteractive.getCustomerModelByCondition(m.getCustomerUuid());
		if(cm != null){
			m.setCustomerName(cm.getCustomerName());
		}
		m.setAppraiseTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(OrderMainAppraiseModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(OrderMainAppraiseModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 
	 * 获取商家的平均评分
	 * 
	 * @param storeUuid
	 * @return
	 */
	public double getAverageScore(String storeUuid,String type){
		return this.myDao.getAverageScore(storeUuid,type);
	}
	
	/**
	 * 
	 * 根据订单id获取订单评价
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAppraiseModel getOrderMainAppraiseByOrderUuid(String orderUuid){
		return this.myDao.getOrderMainAppraiseByOrderUuid(orderUuid);
	}
}
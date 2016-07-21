package com.aebiz.b2b2c.customermgr.customersubscription.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customersubscription.dao.CustomerSubscriptionDAO;
import com.aebiz.b2b2c.customermgr.customersubscription.service.CustomerSubscriptionService;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionModel;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionQueryModel;

@Service
@Transactional
public class CustomerSubscriptionServiceImpl
		extends
		BaseServiceImpl<CustomerSubscriptionModel, CustomerSubscriptionQueryModel>
		implements CustomerSubscriptionService {
	private CustomerSubscriptionDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerSubscriptionDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerSubscriptionModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerSubscriptionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerSubscriptionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据会员订阅编号取消会员订阅
	 * 
	 * @param needUpdateUuids
	 */
	public void updateSubscriptionState(List<String> needUpdateUuids) {
		this.myDao.updateSubscriptionState(needUpdateUuids);
	}
}
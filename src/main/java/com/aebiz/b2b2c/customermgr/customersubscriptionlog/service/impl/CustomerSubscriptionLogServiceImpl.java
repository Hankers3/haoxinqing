package com.aebiz.b2b2c.customermgr.customersubscriptionlog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.dao.CustomerSubscriptionLogDAO;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.service.CustomerSubscriptionLogService;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogModel;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogQueryModel;

@Service
@Transactional
public class CustomerSubscriptionLogServiceImpl
		extends
		BaseServiceImpl<CustomerSubscriptionLogModel, CustomerSubscriptionLogQueryModel>
		implements CustomerSubscriptionLogService {
	private CustomerSubscriptionLogDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerSubscriptionLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerSubscriptionLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerSubscriptionLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerSubscriptionLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过会员编号查询出该会员所有订阅的信息
	 */
	public List<CustomerSubscriptionLogModel> getSubscriptionListByCustomerUuid(
			String customerUuid) {
		return myDao.getSubscriptionListByCustomerUuid(customerUuid);
	}
}
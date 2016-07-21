package com.aebiz.b2b2c.customermgr.customersubscribecontent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.dao.CustomerSubscribeContentDAO;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.service.CustomerSubscribeContentService;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentModel;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentQueryModel;

@Service
@Transactional
public class CustomerSubscribeContentServiceImpl
		extends
		BaseServiceImpl<CustomerSubscribeContentModel, CustomerSubscribeContentQueryModel>
		implements CustomerSubscribeContentService {
	private CustomerSubscribeContentDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerSubscribeContentDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerSubscribeContentModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerSubscribeContentModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerSubscribeContentModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 检查订阅编号是否存在
	 */
	public boolean checkSubscribeNoExist(String subscribeNo, String uuid) {
		return this.myDao.checkSubscribeNoExist(subscribeNo, uuid);
	}

}
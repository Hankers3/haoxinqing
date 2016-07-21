package com.aebiz.b2b2c.customermgr.customeraudit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customeraudit.dao.CustomerAuditDAO;
import com.aebiz.b2b2c.customermgr.customeraudit.service.CustomerAuditService;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthQueryModel;

@Service
@Transactional
public class CustomerAuditServiceImpl extends
		BaseServiceImpl<CustomerAuthModel, CustomerAuthQueryModel> implements
		CustomerAuditService {
	private CustomerAuditDAO myDao = null;
	
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerAuditDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerAuthModel m) {
		m.setUuid(us.getNextUuid("CustomerAuth", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerAuthModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerAuthModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void doAuditPass(String customerUuid, String auditReason) {
		myDao.doAuditPass(customerUuid,auditReason);
	}

	@Override
	public void doAuditUnPass(String customerUuid, String auditReason) {
		myDao.doAuditUnPass(customerUuid,auditReason);
	}
}
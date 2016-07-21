package com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevelrel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customerstorelevelrel.vo.CustomerStoreLevelRelModel;
import com.aebiz.b2b2c.customermgr.customerstorelevelrel.vo.CustomerStoreLevelRelQueryModel;
import com.aebiz.b2b2c.customermgr.storeback.dao.customerstorelevelrel.CustomerStoreLevelRelDAO;
import com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevelrel.CustomerStoreLevelRelService;

@Service
@Transactional
public class CustomerStoreLevelRelServiceImpl
		extends
		BaseServiceImpl<CustomerStoreLevelRelModel, CustomerStoreLevelRelQueryModel>
		implements CustomerStoreLevelRelService {
	private CustomerStoreLevelRelDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerStoreLevelRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerStoreLevelRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerStoreLevelRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerStoreLevelRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
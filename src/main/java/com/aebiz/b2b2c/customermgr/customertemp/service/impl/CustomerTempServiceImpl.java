package com.aebiz.b2b2c.customermgr.customertemp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customertemp.dao.CustomerTempDAO;
import com.aebiz.b2b2c.customermgr.customertemp.service.CustomerTempService;
import com.aebiz.b2b2c.customermgr.customertemp.vo.CustomerTempModel;
import com.aebiz.b2b2c.customermgr.customertemp.vo.CustomerTempQueryModel;

@Service
@Transactional
public class CustomerTempServiceImpl extends
		BaseServiceImpl<CustomerTempModel, CustomerTempQueryModel> implements
		CustomerTempService {
	private CustomerTempDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerTempDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerTempModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerTempModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerTempModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
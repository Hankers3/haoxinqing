package com.aebiz.b2b2c.websiteoperation.customerservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.websiteoperation.customerservice.dao.CustomerServiceDAO;
import com.aebiz.b2b2c.websiteoperation.customerservice.service.CustomerServiceService;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceModel;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceQueryModel;



@Service
@Transactional
public class CustomerServiceServiceImpl extends BaseServiceImpl<CustomerServiceModel,CustomerServiceQueryModel> implements CustomerServiceService {
	private CustomerServiceDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(CustomerServiceDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerServiceModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(CustomerServiceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(CustomerServiceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	public List<CustomerServiceModel> getAll(){
		return myDao.getAll();
	}
}
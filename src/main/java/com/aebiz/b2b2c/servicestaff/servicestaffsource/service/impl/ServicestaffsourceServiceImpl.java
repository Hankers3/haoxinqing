package com.aebiz.b2b2c.servicestaff.servicestaffsource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.servicestaffsource.dao.ServicestaffsourceDAO;
import com.aebiz.b2b2c.servicestaff.servicestaffsource.service.ServicestaffsourceService;
import com.aebiz.b2b2c.servicestaff.servicestaffsource.vo.ServicestaffsourceModel;
import com.aebiz.b2b2c.servicestaff.servicestaffsource.vo.ServicestaffsourceQueryModel;

@Service
@Transactional
public class ServicestaffsourceServiceImpl extends BaseServiceImpl<ServicestaffsourceModel,ServicestaffsourceQueryModel> implements ServicestaffsourceService {
	private ServicestaffsourceDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ServicestaffsourceDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ServicestaffsourceModel m) {
		m.setUuid(us.getNextUuid("Servicestaffsource",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ServicestaffsourceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ServicestaffsourceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
package com.aebiz.b2b2c.servicestaff.servicestaffcoord.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.dao.ServiceStaffCoordDAO;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.service.ServiceStaffCoordService;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordQueryModel;


@Service
@Transactional
public class ServiceStaffCoordServiceImpl extends BaseServiceImpl<ServiceStaffCoordModel,ServiceStaffCoordQueryModel> implements ServiceStaffCoordService {
	private ServiceStaffCoordDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ServiceStaffCoordDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ServiceStaffCoordModel m) {
		m.setUuid(us.getNextUuid("ServiceStaffCoord",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ServiceStaffCoordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ServiceStaffCoordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public ServiceStaffCoordModel getByServiceStaffUuid(String serviceStaffUuid) {
		return myDao.getByServiceStaffUuid(serviceStaffUuid);
	}
}
package com.aebiz.b2b2c.servicestaff.doctorgroup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.servicestaff.doctorgroup.service.DoctorGroupService;
import com.aebiz.b2b2c.servicestaff.doctorgroup.dao.DoctorGroupDAO;
import com.aebiz.b2b2c.servicestaff.doctorgroup.vo.DoctorGroupModel;
import com.aebiz.b2b2c.servicestaff.doctorgroup.vo.DoctorGroupQueryModel;

@Service
@Transactional
public class DoctorGroupServiceImpl extends BaseServiceImpl<DoctorGroupModel,DoctorGroupQueryModel> implements DoctorGroupService {
	private DoctorGroupDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DoctorGroupDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DoctorGroupModel m) {
		m.setUuid(us.getNextUuid("DoctorGroup",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(DoctorGroupModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(DoctorGroupModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
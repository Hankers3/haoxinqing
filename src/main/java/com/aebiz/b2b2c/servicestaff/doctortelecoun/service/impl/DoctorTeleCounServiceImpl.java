package com.aebiz.b2b2c.servicestaff.doctortelecoun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.servicestaff.doctortelecoun.service.DoctorTeleCounService;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.dao.DoctorTeleCounDAO;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounModel;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounQueryModel;

@Service
@Transactional
public class DoctorTeleCounServiceImpl extends BaseServiceImpl<DoctorTeleCounModel,DoctorTeleCounQueryModel> implements DoctorTeleCounService {
	private DoctorTeleCounDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DoctorTeleCounDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DoctorTeleCounModel m) {
		m.setUuid(us.getNextUuid("DoctorTeleCoun",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(DoctorTeleCounModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(DoctorTeleCounModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
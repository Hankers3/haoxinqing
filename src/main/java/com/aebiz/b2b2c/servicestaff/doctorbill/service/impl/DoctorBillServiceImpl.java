package com.aebiz.b2b2c.servicestaff.doctorbill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.servicestaff.doctorbill.service.DoctorBillService;
import com.aebiz.b2b2c.servicestaff.doctorbill.dao.DoctorBillDAO;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillModel;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillQueryModel;

@Service
@Transactional
public class DoctorBillServiceImpl extends BaseServiceImpl<DoctorBillModel,DoctorBillQueryModel> implements DoctorBillService {
	private DoctorBillDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DoctorBillDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DoctorBillModel m) {
		m.setUuid(us.getNextUuid("DoctorBill",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(DoctorBillModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(DoctorBillModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
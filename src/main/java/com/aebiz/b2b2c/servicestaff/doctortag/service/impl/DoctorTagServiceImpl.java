package com.aebiz.b2b2c.servicestaff.doctortag.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.doctortag.dao.DoctorTagDAO;
import com.aebiz.b2b2c.servicestaff.doctortag.service.DoctorTagService;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagModel;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagQueryModel;

@Service
@Transactional
public class DoctorTagServiceImpl extends
		BaseServiceImpl<DoctorTagModel, DoctorTagQueryModel> implements
		DoctorTagService {
	private DoctorTagDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(DoctorTagDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DoctorTagModel m) {
		m.setUuid(us.getNextUuid("PlusConf", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(DoctorTagModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(DoctorTagModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过医生Uuid得到对象
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<String> getByDoctorUuid(String doctorUuid) {
		return myDao.getByDoctorUuid(doctorUuid);
	}

	/**
	 * 通过医生Uuid得到对象
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public void deleteByDoctorUuid(String doctorUuid) {
		myDao.deleteByDoctorUuid(doctorUuid);
	}
}
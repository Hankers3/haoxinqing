package com.aebiz.b2b2c.servicestaff.doctorright.service.impl;

import com.wxcommon.util.IdentityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.servicestaff.doctorright.service.DoctorRightService;
import com.aebiz.b2b2c.servicestaff.doctorright.dao.DoctorRightDAO;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightQueryModel;

@Service
@Transactional
public class DoctorRightServiceImpl extends BaseServiceImpl<DoctorRightModel,DoctorRightQueryModel> implements DoctorRightService {
	private DoctorRightDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DoctorRightDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DoctorRightModel m) {
		m.setUuid(IdentityHelper.uuid2());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(DoctorRightModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(DoctorRightModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 获取医生的权限设置信息
	 * @param DoctorUuid
	 * @return
	 */
	@Override
	public DoctorRightModel getByDoctorUuid(String doctorUuid) {
		return myDao.getByDoctorUuid(doctorUuid);
	}
	/**
	 * 获取医生是否开启审核
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public String getExamByDoctorUuid(String doctorUuid) {
		return myDao.getExamByDoctorUuid(doctorUuid);
	}
}
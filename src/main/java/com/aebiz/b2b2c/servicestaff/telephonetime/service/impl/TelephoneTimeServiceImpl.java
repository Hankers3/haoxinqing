package com.aebiz.b2b2c.servicestaff.telephonetime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.telephonetime.dao.TelephoneTimeDAO;
import com.aebiz.b2b2c.servicestaff.telephonetime.service.TelephoneTimeService;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeModel;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeQueryModel;


@Service
@Transactional
public class TelephoneTimeServiceImpl extends BaseServiceImpl<TelephoneTimeModel,TelephoneTimeQueryModel> implements TelephoneTimeService {
	private TelephoneTimeDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(TelephoneTimeDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(TelephoneTimeModel m) {
		m.setUuid(us.getNextUuid("TelephoneTime",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(TelephoneTimeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(TelephoneTimeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
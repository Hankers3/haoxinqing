package com.aebiz.b2b2c.servicestaff.telephonecost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.telephonecost.dao.TelephoneCostDAO;
import com.aebiz.b2b2c.servicestaff.telephonecost.service.TelephoneCostService;
import com.aebiz.b2b2c.servicestaff.telephonecost.vo.TelephoneCostModel;
import com.aebiz.b2b2c.servicestaff.telephonecost.vo.TelephoneCostQueryModel;


@Service
@Transactional
public class TelephoneCostServiceImpl extends BaseServiceImpl<TelephoneCostModel,TelephoneCostQueryModel> implements TelephoneCostService {
	private TelephoneCostDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(TelephoneCostDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(TelephoneCostModel m) {
		m.setUuid(us.getNextUuid("TelephoneCost",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(TelephoneCostModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(TelephoneCostModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
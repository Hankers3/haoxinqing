package com.aebiz.b2b2c.basicdata.logisticscompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.basicdata.logisticscompany.dao.LogisticsCompanyDAO;
import com.aebiz.b2b2c.basicdata.logisticscompany.service.LogisticsCompanyService;
import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyModel;
import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyQueryModel;

@Service
@Transactional
public class LogisticsCompanyServiceImpl extends
		BaseServiceImpl<LogisticsCompanyModel, LogisticsCompanyQueryModel>
		implements LogisticsCompanyService {
	private LogisticsCompanyDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(LogisticsCompanyDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(LogisticsCompanyModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(LogisticsCompanyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(LogisticsCompanyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
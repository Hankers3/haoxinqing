package com.aebiz.b2b2c.basicdata.sensitiveword.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.basicdata.sensitiveword.dao.SensitiveWordDAO;
import com.aebiz.b2b2c.basicdata.sensitiveword.service.SensitiveWordService;
import com.aebiz.b2b2c.basicdata.sensitiveword.vo.SensitiveWordModel;
import com.aebiz.b2b2c.basicdata.sensitiveword.vo.SensitiveWordQueryModel;

@Service
@Transactional
public class SensitiveWordServiceImpl extends
		BaseServiceImpl<SensitiveWordModel, SensitiveWordQueryModel> implements
		SensitiveWordService {
	private SensitiveWordDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(SensitiveWordDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SensitiveWordModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(SensitiveWordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(SensitiveWordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
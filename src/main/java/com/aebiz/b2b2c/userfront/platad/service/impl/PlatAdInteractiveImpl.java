package com.aebiz.b2b2c.userfront.platad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.userfront.platad.service.PlatAdInteractive;
import com.aebiz.b2b2c.userfront.platad.service.PlatAdService;
import com.aebiz.b2b2c.userfront.platad.dao.PlatAdDAO;
import com.aebiz.b2b2c.userfront.platad.vo.PlatAdModel;
import com.aebiz.b2b2c.userfront.platad.vo.PlatAdQueryModel;

@Service
@Transactional
public class PlatAdInteractiveImpl extends BaseServiceImpl<PlatAdModel,PlatAdQueryModel> implements PlatAdInteractive {
	private PlatAdDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(PlatAdDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(PlatAdModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(PlatAdModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(PlatAdModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
package com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.dao.VirtualAccountStoreLogDAO;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.service.VirtualAccountStoreLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogQueryModel;

@Service
@Transactional
public class VirtualAccountStoreLogServiceImpl
		extends
		BaseServiceImpl<VirtualAccountStoreLogModel, VirtualAccountStoreLogQueryModel>
		implements VirtualAccountStoreLogService {
	private VirtualAccountStoreLogDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(VirtualAccountStoreLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(VirtualAccountStoreLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(VirtualAccountStoreLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(VirtualAccountStoreLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
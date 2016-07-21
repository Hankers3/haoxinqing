package com.aebiz.b2b2c.finance.ledgerbystore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.ledgerbystore.dao.LedgerByStoreDAO;
import com.aebiz.b2b2c.finance.ledgerbystore.service.LedgerByStoreInteractive;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreModel;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreQueryModel;

@Service
@Transactional
public class LedgerByStoreInteractiveImpl
		extends
			BaseServiceImpl<LedgerByStoreModel, LedgerByStoreQueryModel>
		implements
		LedgerByStoreInteractive {
	private LedgerByStoreDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(LedgerByStoreDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(LedgerByStoreModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(LedgerByStoreModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(LedgerByStoreModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
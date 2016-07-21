package com.aebiz.b2b2c.finance.ledgerbystorelog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.ledgerbystorelog.dao.LedgerByStoreLogDAO;
import com.aebiz.b2b2c.finance.ledgerbystorelog.service.LedgerByStoreLogService;
import com.aebiz.b2b2c.finance.ledgerbystorelog.vo.LedgerByStoreLogModel;
import com.aebiz.b2b2c.finance.ledgerbystorelog.vo.LedgerByStoreLogQueryModel;

@Service
@Transactional
public class LedgerByStoreLogServiceImpl extends BaseServiceImpl<LedgerByStoreLogModel,LedgerByStoreLogQueryModel> implements LedgerByStoreLogService {
	private LedgerByStoreLogDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(LedgerByStoreLogDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(LedgerByStoreLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		//操作人
		m.setManagerUuid(LoginUserHelper.getLoginUserUuid());
		
		//修改时间
		m.setUpdateTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(LedgerByStoreLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(LedgerByStoreLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
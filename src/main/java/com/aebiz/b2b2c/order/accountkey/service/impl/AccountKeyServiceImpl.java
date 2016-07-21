package com.aebiz.b2b2c.order.accountkey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.accountkey.dao.AccountKeyDAO;
import com.aebiz.b2b2c.order.accountkey.service.AccountKeyService;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyQueryModel;

@Service
@Transactional
public class AccountKeyServiceImpl extends BaseServiceImpl<AccountKeyModel,AccountKeyQueryModel> implements AccountKeyService {
	private AccountKeyDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(AccountKeyDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(AccountKeyModel m) {
		m.setUuid(us.getNextUuid("AccountKey",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(AccountKeyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(AccountKeyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	@Override
	public AccountKeyModel getAccountKeyModelById(String id) {
		return myDao.getAccountKeyModelById(id);
	}
	/**
	 * 
	 * @Description: (定时任务将表里的字段设为0)    
	 * @author XP  
	 * @date 2016-1-26 下午1:07:33
	 */
        @Override
        public void updateAccountVlue() {
            myDao.updateAccountVlue();
        }
}
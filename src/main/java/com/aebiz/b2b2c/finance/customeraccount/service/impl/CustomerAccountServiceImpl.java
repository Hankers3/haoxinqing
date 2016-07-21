package com.aebiz.b2b2c.finance.customeraccount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.customeraccount.dao.CustomerAccountDAO;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountService;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountQueryModel;

@Service
@Transactional
public class CustomerAccountServiceImpl
		extends
			BaseServiceImpl<CustomerAccountModel, CustomerAccountQueryModel>
		implements
			CustomerAccountService {
	private CustomerAccountDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(CustomerAccountDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerAccountModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(CustomerAccountModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(CustomerAccountModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据会员的uuid获取会员账户对象
	 * @param customerUuid
	 * @return 
	 * CustomerAccountModel
	 */
	public CustomerAccountModel getCustomerAccountModelByCustomerUuid(String customerUuid){
		return myDao.getCustomerAccountModelByCustomerUuid(customerUuid);
	}
	
	/**
	 * 根据密码和会员的uuid校验输入的虚拟账户密码是否正确
	 * @param password
	 * @param customerUuid
	 * @return 
	 * boolean
	 */
	public boolean checkPassword(String password,String customerUuid){
		CustomerAccountModel accountModel = this.getCustomerAccountModelByCustomerUuid(customerUuid);
		
		if(accountModel != null){
			if(accountModel.getPayPasswd().equals(password)){
				return true;
			}else{
				return false;
			}
		}
		
		return false;
	}
}
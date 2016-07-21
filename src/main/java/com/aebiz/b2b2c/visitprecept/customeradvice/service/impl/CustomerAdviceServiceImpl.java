package com.aebiz.b2b2c.visitprecept.customeradvice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.customeradvice.dao.CustomerAdviceDAO;
import com.aebiz.b2b2c.visitprecept.customeradvice.service.CustomerAdviceService;
import com.aebiz.b2b2c.visitprecept.customeradvice.vo.CustomerAdviceModel;
import com.aebiz.b2b2c.visitprecept.customeradvice.vo.CustomerAdviceQueryModel;
import com.wxcommon.util.IdentityHelper;

@Service
@Transactional
public class CustomerAdviceServiceImpl extends BaseServiceImpl<CustomerAdviceModel,CustomerAdviceQueryModel> implements CustomerAdviceService {
	private CustomerAdviceDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(CustomerAdviceDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerAdviceModel m) {
		m.setUuid(IdentityHelper.uuid2());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(CustomerAdviceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(CustomerAdviceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 通过uuid将处理意见保存到数据库
	 * @param customerUuid  当前数据的uuid
	 * @param refundConten   处理意见
	 * @return
	 */
//	@Override
	public void toUpdate(String customerUuid, String refundConten) {
	
		this.myDao.toUpdate(customerUuid, refundConten);
	}

	
}
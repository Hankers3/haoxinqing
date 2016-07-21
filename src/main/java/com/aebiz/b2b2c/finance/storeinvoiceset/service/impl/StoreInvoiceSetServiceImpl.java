package com.aebiz.b2b2c.finance.storeinvoiceset.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.storeinvoiceset.dao.StoreInvoiceSetDAO;
import com.aebiz.b2b2c.finance.storeinvoiceset.service.StoreInvoiceSetService;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetModel;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetQueryModel;

@Service
@Transactional
public class StoreInvoiceSetServiceImpl extends BaseServiceImpl<StoreInvoiceSetModel,StoreInvoiceSetQueryModel> implements StoreInvoiceSetService {
	private StoreInvoiceSetDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(StoreInvoiceSetDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StoreInvoiceSetModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getStoreLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		//获取商户的accountUuid
		String accountUuid = LoginUserHelper.getStoreLoginUserUuid();
		
		StoreInvoiceSetModel invoiceSetModel = this.getStoreInvoiceSetWebModelByAccoutUuid(accountUuid);
		
		String ret = "";
		if(invoiceSetModel != null){
			invoiceSetModel.setInvoiceCate(m.getInvoiceCate());
			invoiceSetModel.setInvoiceContent(m.getInvoiceContent());
			invoiceSetModel.setInvoiceType(m.getInvoiceType());
			this.update(invoiceSetModel);
		}else{
			m.setAccountUuid(accountUuid);
			ret = this.myDao.create(m);
		}
		return ret;
	}
	@Override
	public void update(StoreInvoiceSetModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(StoreInvoiceSetModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	public StoreInvoiceSetModel getStoreInvoiceSetWebModelByAccoutUuid(
			String accountUuid) {
		// TODO Auto-generated method stub
		return myDao.getStoreInvoiceSetWebModelByAccoutUuid(accountUuid);
	}
	
	
	
	
}
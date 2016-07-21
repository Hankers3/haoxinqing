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
import com.aebiz.b2b2c.finance.storeinvoiceset.service.StoreInvoiceSetInteractive;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetModel;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetQueryModel;
/**
 * 商户发票设置对外接口
 *
 * @author tangyunkai
 *
 * @date 2014年12月8日 下午8:33:52 
 *
 */
@Service
@Transactional
public class StoreInvoiceSetInteractiveImpl extends BaseServiceImpl<StoreInvoiceSetModel, StoreInvoiceSetQueryModel>
		implements StoreInvoiceSetInteractive {
	
	private StoreInvoiceSetDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(StoreInvoiceSetDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StoreInvoiceSetModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getStoreLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}
	
	/**
	 * 通过商户Uuid获取商户发票类型，发票种类，发票内容
	 * @param accountUuid
	 * @return
	 */
	public StoreInvoiceSetModel getStoreInvoiceSetModelByAccountUuid(String accountUuid) {
		return myDao.getStoreInvoiceSetWebModelByAccoutUuid(accountUuid);
	}

}
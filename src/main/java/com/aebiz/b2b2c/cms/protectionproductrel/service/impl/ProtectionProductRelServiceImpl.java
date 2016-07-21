package com.aebiz.b2b2c.cms.protectionproductrel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.protectionproductrel.dao.ProtectionProductRelDAO;
import com.aebiz.b2b2c.cms.protectionproductrel.service.ProtectionProductRelService;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelModel;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelQueryModel;

@Service
@Transactional
public class ProtectionProductRelServiceImpl extends BaseServiceImpl<ProtectionProductRelModel,ProtectionProductRelQueryModel> implements ProtectionProductRelService {
	
	private ProtectionProductRelDAO myDao = null;
	
	@Autowired
	private UuidService us;
	
	@Autowired
	public void setMyDao(ProtectionProductRelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProtectionProductRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	
	@Override
	public void update(ProtectionProductRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	@Override
	public void delete(ProtectionProductRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

}
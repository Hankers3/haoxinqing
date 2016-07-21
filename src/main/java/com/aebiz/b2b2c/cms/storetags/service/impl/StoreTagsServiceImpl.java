package com.aebiz.b2b2c.cms.storetags.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.storetags.dao.StoreTagsDAO;
import com.aebiz.b2b2c.cms.storetags.service.StoreTagsService;
import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsModel;
import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsQueryModel;

@Service
@Transactional
public class StoreTagsServiceImpl extends BaseServiceImpl<StoreTagsModel,StoreTagsQueryModel> implements StoreTagsService {
	private StoreTagsDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(StoreTagsDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StoreTagsModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setStoreUuid("aebiz");
		m.setStoreName("AEBIZ");
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(StoreTagsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setStoreUuid("aebiz");
		m.setStoreName("AEBIZ");
		super.update(m);
	}
	@Override
	public void delete(StoreTagsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
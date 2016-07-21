package com.aebiz.b2b2c.cms.storeTagsCustomer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.storeTagsCustomer.dao.StoreTagsCustomerDAO;
import com.aebiz.b2b2c.cms.storeTagsCustomer.service.StoreTagsCustomerService;
import com.aebiz.b2b2c.cms.storeTagsCustomer.vo.StoreTagsCustomerModel;
import com.aebiz.b2b2c.cms.storeTagsCustomer.vo.StoreTagsCustomerQueryModel;

@Service
@Transactional
public class StoreTagsCustomerServiceImpl extends
		BaseServiceImpl<StoreTagsCustomerModel, StoreTagsCustomerQueryModel>
		implements StoreTagsCustomerService {
	private StoreTagsCustomerDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(StoreTagsCustomerDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StoreTagsCustomerModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(StoreTagsCustomerModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(StoreTagsCustomerModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 获取某一商户下,某个会员关联的标签id
	 * 
	 * @param storeUuid
	 * @return
	 */
	public List<String> getByStoreUuidAndCustomerUuid(String storeUuid,
			String customerUuid){
		return this.myDao.getByStoreUuidAndCustomerUuid(storeUuid, customerUuid);
	}
	
	/**
	 * 商户会员管理商户标签时、保存商户、标签、会员的关联关系
	 * 
	 * @param m
	 */
	public void deleteStoreTagsCustomerRel(String storeUuid, String customerUuid){
		this.myDao.deleteStoreTagsCustomerRel(storeUuid, customerUuid);
	}
}
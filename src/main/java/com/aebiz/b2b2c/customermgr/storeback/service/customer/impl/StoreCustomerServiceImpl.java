package com.aebiz.b2b2c.customermgr.storeback.service.customer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;
import com.aebiz.b2b2c.customermgr.storeback.dao.customer.StoreCustomerDAO;
import com.aebiz.b2b2c.customermgr.storeback.service.customer.StoreCustomerService;

@Service
@Transactional
public class StoreCustomerServiceImpl extends
		BaseServiceImpl<CustomerModel, CustomerQueryModel> implements
		StoreCustomerService {
	private StoreCustomerDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(StoreCustomerDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过会员编号查询该会员
	 */
	public CustomerModel getCustomerByCustomerUuid(String customerUuid) {
		return this.myDao.getCustomerByCustomerUuid(customerUuid);
	}

	/**
	 * 通过商户会员等级编号查询商户会员等级
	 */
	public CustomerStoreLevelModel getCustomerStoreLevelByUuid(
			String storeLevelUuid) {
		return this.myDao.getCustomerStoreLevelByUuid(storeLevelUuid);
	}

	/**
	 * 通过商户编号获取该商户的所有会员等级
	 */
	@Override
	public List<CustomerStoreLevelModel> getCustomerStoreLevelsByStoreUuid(
			String storeUuid) {
		return this.myDao.getCustomerStoreLevelsByStoreUuid(storeUuid);
	}

	/**
	 * 调价查询，通过商户编号获取该商户关联的所有的会员
	 */
	@Override
	public List<CustomerModel> getByCondition(CustomerQueryModel qm, int start,
			int pageShow) {
		// 需要调用商户接口得到当前登录的商户编号
		String storeUuid = "store01";
		//qm.setStoreUuid(storeUuid);
		return super.getByCondition(qm, start, pageShow);
	}

	/**
	 * 通过会员编号和商户编号获取该会员的商户会员等级
	 */
	@Override
	public CustomerStoreLevelModel getCustomerStoreLevel(String customerUuid,
			String storeUuid) {
		return this.myDao.getCustomerStoreLevel(customerUuid, storeUuid);
	}
}
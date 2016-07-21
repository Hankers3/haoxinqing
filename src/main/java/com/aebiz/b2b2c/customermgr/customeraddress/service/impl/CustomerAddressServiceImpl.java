package com.aebiz.b2b2c.customermgr.customeraddress.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customeraddress.dao.CustomerAddressDAO;
import com.aebiz.b2b2c.customermgr.customeraddress.service.CustomerAddressService;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressModel;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressQueryModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customershoplevel.service.CustomerShopLevelService;

@Service
@Transactional
public class CustomerAddressServiceImpl extends
		BaseServiceImpl<CustomerAddressModel, CustomerAddressQueryModel> implements
		CustomerAddressService {
	private CustomerAddressDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private CustomerShopLevelService customerShopLevelService;
	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	public void setMyDao(CustomerAddressDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}
	
	@Override
	public String create(CustomerAddressModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	/**
	 * 根据会员编号获取会员地址列表
	 */
	@Override
	public List<CustomerAddressModel> getListByCustomerUuid(String customerUuid) {
		 //根据会员编号获取会员地址列表
		List<CustomerAddressModel> list = myDao.getListByCustomerUuid(customerUuid);
		
		return list;
	}

	/**
	 * 根据用户地址的uuid获取对象
	 */
	@Override
	public CustomerAddressModel getCustomerAddressModelByUuid(String uuid) {
		return myDao.getCustomerAddressModelByUuid(uuid);
	}

	/**
	 * 根据会员id和地址默认状态获取会员地址列表
	 */
	@Override
	public List<CustomerAddressModel> getListByCustomerUuid(
			String customerUuid, String isDefault) {
		return myDao.getListByCustomerUuid(customerUuid, isDefault);
	}
	
	/**
	 * 根据会员编号及城市id获取会员地址列表
	 */
	@Override
	public List<CustomerAddressModel> getAddressListById(String customerUuid,
			String cityId) {
		return myDao.getAddressListById(customerUuid, cityId);
	}
	

}
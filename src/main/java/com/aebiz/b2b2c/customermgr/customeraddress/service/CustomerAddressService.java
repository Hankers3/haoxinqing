package com.aebiz.b2b2c.customermgr.customeraddress.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressModel;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressQueryModel;

public interface CustomerAddressService extends
		BaseService<CustomerAddressModel, CustomerAddressQueryModel> {
	
	@Override
	public List<CustomerAddressModel> getAll();
	
	//根据customerUuid 来获取其关联的地址列表
	public List<CustomerAddressModel> getListByCustomerUuid(String customerUuid);
	
	
	/**
	 * 根据用户地址的uuid获取对象
	 * @param uuid
	 * @return
	 */
	public CustomerAddressModel getCustomerAddressModelByUuid(String uuid);
	
	/**
	 * 根据会员id和地址默认状态获取会员地址列表
	 * @param customerUuid
	 * @param isDefault
	 * @author zdx
	 * @return
	 */
	public List<CustomerAddressModel> getListByCustomerUuid(String customerUuid,String isDefault);
	
	/**
	 * 根据会员编号及城市id获取会员地址列表
	 * @param customerUuid
	 * @param cityId
	 * @return
	 * @author zdx
	 */
	
	public List<CustomerAddressModel> getAddressListById(String customerUuid,String cityId);
}

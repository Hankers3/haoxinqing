package com.aebiz.b2b2c.customermgr.customeraddress.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressModel;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressQueryModel;

public interface CustomerAddressDAO extends BaseDAO<CustomerAddressModel, CustomerAddressQueryModel> {
	
	/**
	 * 根据会员编号获取会员地址列表
	 */
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
	/**
	 * 根据患者的id获取所有用户地址的ids
	 * @param customerUuid
	 * @return
	 */
	public  List<String> getAllCustomerAddressModelIdsByCustomerUuid(String customerUuid);
	
	/**
	 * 根据患者的id获取患者地址的uuids
	 * @param customerUuid
	 * @param isDefault
	 * @return
	 */
	public List<String> getAllCustomerAddressModelIdsByCustomerUuidAndIsDefault(String customerUuid,String isDefault);

	/**
	 * 根据患者的id和所在城市查询获取uuids
	 * @param customerUuid
	 * @param cityId
	 * @return
	 */
        public List<String> getAllCustomerAddressModelIdsByCustomerUuidAndCityId(String customerUuid,
            String cityId);
}
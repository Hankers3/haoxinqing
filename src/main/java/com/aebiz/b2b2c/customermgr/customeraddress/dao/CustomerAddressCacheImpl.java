package com.aebiz.b2b2c.customermgr.customeraddress.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressModel;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerAddressCacheImpl extends BaseMemcachedCache<CustomerAddressModel, CustomerAddressQueryModel> implements CustomerAddressDAO{

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;
	
	@Autowired
	private CustomerAddressDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}
	
	public CustomerAddressCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY);
	}
	/**
         * 根据患者的id获取所有用户地址的ids
         * @param customerUuid
         * @return
         */
	@Override
	public  List<String> getAllCustomerAddressModelIdsByCustomerUuid(String customerUuid) {
	    List<String>  uuids = (List<String>) this.mcc.get(CustomerCacheConstant.CUSTOMER_CONSULTRECORD_KEY_UUIDS+customerUuid);
            if(uuids ==null ||uuids.size()<0){
                uuids = this.dao.getAllCustomerAddressModelIdsByCustomerUuid(customerUuid);
                this.mcc.set(CustomerCacheConstant.CUSTOMER_CONSULTRECORD_KEY_UUIDS+customerUuid, uuids);
            }
             return uuids;
	    }
	/**
         * 根据会员编号获取会员地址列表
         */
	@Override
	public List<CustomerAddressModel> getListByCustomerUuid(String customerUuid) {
	    List<String> uuids = this.getAllCustomerAddressModelIdsByCustomerUuid(customerUuid);
            List<CustomerAddressModel> listM = new ArrayList<CustomerAddressModel>();
            List<String> noUuids = new ArrayList<String>();
            if (uuids != null && uuids.size() > 0) {
                    for (String uuid : uuids) {
                            Object obj = this.mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY + uuid);
                            if (obj != null) {
                                CustomerAddressModel m = (CustomerAddressModel) obj;
                                    listM.add(m);
                            } else {
                                    noUuids.add(uuid);
                            }
                    }
                    if (noUuids.size() > 0) {
                            for (String uuid : noUuids) {
                                CustomerAddressModel m = dao.getByUuid(uuid);
                                    if (m != null) {
                                            this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY + uuid, m);
                                            listM.add(m);
                                    }
                            }
                    }
            }
            return listM;
	}

	/**
         * 根据用户地址的uuid获取对象
         * @param uuid
         * @return
         */

        @Override
	public CustomerAddressModel getCustomerAddressModelByUuid(String uuid) {
           Object obj = this.mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY + uuid);
           CustomerAddressModel m =null;
           if (obj != null) {
                   m = (CustomerAddressModel) obj;
               } else {
                   m = dao.getCustomerAddressModelByUuid(uuid);
           if (m != null) {
                   this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY + uuid, m);
                   }
                 }
           return m;
	}
        /**
         * 根据患者的id获取患者地址的uuids
         * @param customerUuid
         * @param isDefault
         * @return
         */
        @Override
        public List<String> getAllCustomerAddressModelIdsByCustomerUuidAndIsDefault(String customerUuid,
                String isDefault) {
            List<String>  uuids = (List<String>) this.mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY+customerUuid);
            if(uuids ==null ||uuids.size()<0){
                uuids = this.dao.getAllCustomerAddressModelIdsByCustomerUuidAndIsDefault(customerUuid,isDefault);
                this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY+customerUuid, uuids);
            }
             return uuids;
        }
        
        /**
         * 根据会员id和地址默认状态获取会员地址列表
         * @param customerUuid
         * @param isDefault
         * @author zdx
         * @return
         */
	@Override
	public List<CustomerAddressModel> getListByCustomerUuid(
			String customerUuid, String isDefault) {
	    List<String> uuids = this.getAllCustomerAddressModelIdsByCustomerUuidAndIsDefault(customerUuid ,isDefault);
            List<CustomerAddressModel> listM = new ArrayList<CustomerAddressModel>();
            List<String> noUuids = new ArrayList<String>();
            if (uuids != null && uuids.size() > 0) {
                    for (String uuid : uuids) {
                            Object obj = this.mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY + uuid);
                            if (obj != null) {
                                CustomerAddressModel m = (CustomerAddressModel) obj;
                                    listM.add(m);
                            } else {
                                    noUuids.add(uuid);
                            }
                    }
                    if (noUuids.size() > 0) {
                            for (String uuid : noUuids) {
                                CustomerAddressModel m = dao.getByUuid(uuid);
                                    if (m != null) {
                                            this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY + uuid, m);
                                            listM.add(m);
                                    }
                            }
                    }
            }
            return listM;
	}
	
	
	/**
         * 根据患者的id和所在城市查询获取uuids
         * @param customerUuid
         * @param cityId
         * @return
         */
	 public  List<String> getAllCustomerAddressModelIdsByCustomerUuidAndCityId(String customerUuid,
	            String cityId) {
	     List<String>  uuids = (List<String>) this.mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY+customerUuid);
	            if(uuids ==null ||uuids.size()<0){
	                uuids = this.dao.getAllCustomerAddressModelIdsByCustomerUuidAndCityId(customerUuid,cityId);
	                this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY+customerUuid, uuids);
	            }
	             return uuids;
	    }
	
          /**
	   * 根据会员编号及城市id获取会员地址列表
	   * @param customerUuid
	   * @param cityId
	   * @return
	   * @author zdx
	   */
        @Override
	public List<CustomerAddressModel> getAddressListById(String customerUuid,
			String cityId) {
        List<String> uuids = this.getAllCustomerAddressModelIdsByCustomerUuidAndCityId(customerUuid ,cityId);
        List<CustomerAddressModel> listM = new ArrayList<CustomerAddressModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
                for (String uuid : uuids) {
                        Object obj = this.mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY + uuid);
                        if (obj != null) {
                            CustomerAddressModel m = (CustomerAddressModel) obj;
                                listM.add(m);
                        } else {
                                noUuids.add(uuid);
                        }
                }
                if (noUuids.size() > 0) {
                        for (String uuid : noUuids) {
                            CustomerAddressModel m = dao.getByUuid(uuid);
                                if (m != null) {
                                        this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERADDRESS_KEY + uuid, m);
                                        listM.add(m);
                                }
                        }
                }
        }
        return listM;
	}

   
	
}

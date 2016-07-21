package com.aebiz.b2b2c.customermgr.customeraddress.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressModel;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressQueryModel;

@Repository
public class CustomerAddressH4Impl extends BaseH4Impl<CustomerAddressModel, CustomerAddressQueryModel> implements CustomerAddressDAO {

	
	/**
	 * 根据会员编号获取会员地址列表
	 */
	@Override
	public List<CustomerAddressModel> getListByCustomerUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"from CustomerAddressModel as ca where ca.customerUuid=:customerUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		List<CustomerAddressModel> list = new ArrayList<CustomerAddressModel>();
		list = query.list();
		if(list != null && list.size()>0){
			return list;
		}else{
			return new ArrayList<CustomerAddressModel>();
		}
	}

	/**
	 * 根据用户地址的uuid获取对象
	 */
	@Override
	public CustomerAddressModel getCustomerAddressModelByUuid(String uuid) {
		StringBuffer hql = new StringBuffer(
				"from CustomerAddressModel as ca where ca.uuid=:uuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		List<CustomerAddressModel> list = query.list();
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return new CustomerAddressModel();
		}
	}

	
	/**
	 * 根据会员id和地址默认状态获取会员地址列表
	 */
	@Override
	public List<CustomerAddressModel> getListByCustomerUuid(String customerUuid, String isDefault) {
		StringBuffer hql = new StringBuffer(
				"from CustomerAddressModel as ca where 1=1 ");
		if(!StringUtil.isEmpty(customerUuid)){
			hql.append(" and ca.customerUuid=:customerUuid");
		}
		if(!StringUtil.isEmpty(isDefault)){
			hql.append(" and ca.isDefault=:isDefault");
		}
		Query query = this.getH4Session().createQuery(hql.toString());
		if(!StringUtil.isEmpty(customerUuid)){
			query.setString("customerUuid", customerUuid);
		}
		if(!StringUtil.isEmpty(isDefault)){
			query.setString("isDefault", isDefault);
		}
		List<CustomerAddressModel> list = new ArrayList<CustomerAddressModel>();
		list = query.list();
		return list;
	}
	
	
	/**
	 * 根据会员编号及城市id获取会员地址列表
	 */
	@Override
	public List<CustomerAddressModel> getAddressListById(String customerUuid,
			String cityId) {
		StringBuffer hql = new StringBuffer("from CustomerAddressModel as ca where ca.customerUuid=:customerUuid ");
		if(!StringUtil.isEmpty(cityId)){
			hql.append(" and ca.city =:city");
		}
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		if(!StringUtil.isEmpty(cityId)){
			query.setString("city", cityId);
		}
		List<CustomerAddressModel> list = new ArrayList<CustomerAddressModel>();
		list = query.list();
		if(list != null && list.size()>0){
			return list;
		}
		return null ;
	}
	/**
         * 根据患者的id获取所有用户地址的ids
         * @param customerUuid
         * @return
         */
        @Override
        public List<String> getAllCustomerAddressModelIdsByCustomerUuid(String customerUuid) {
            StringBuffer hql = new StringBuffer(
                    "select crm.uuid from CustomerAddressModel crm where  crm.customerUuid=:customerUuid ");
                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("customerUuid", customerUuid);
                List<String> list = query.list();
                if (list != null && list.size() > 0) {
                        return list;
                }
                return null;
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
               StringBuffer hql = new StringBuffer(
                    "select crm.uuid from CustomerAddressModel crm where  crm.customerUuid=:customerUuid and crm.isDefault =:isDefault ");
                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("customerUuid", customerUuid);
                query.setString("isDefault", isDefault);
                List<String> list = query.list();
                if (list != null && list.size() > 0) {
                        return list;
                }
                return null;
        }
        /**
         * 根据患者的id和所在城市查询获取uuids
         * @param customerUuid
         * @param cityId
         * @return
         */
        @Override
        public List<String> getAllCustomerAddressModelIdsByCustomerUuidAndCityId(String customerUuid,
                String cityId) {
            StringBuffer hql = new StringBuffer(
                    "select crm.uuid from CustomerAddressModel crm where  crm.customerUuid=:customerUuid and crm.city =:cityId ");
                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("customerUuid", customerUuid);
                query.setString("cityId", cityId);
                List<String> list = query.list();
                if (list != null && list.size() > 0) {
                        return list;
                }
                return null;
        }
	
	
	
	
	
	
	
	
	
	

	

}

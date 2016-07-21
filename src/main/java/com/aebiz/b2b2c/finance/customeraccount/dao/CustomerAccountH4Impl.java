package com.aebiz.b2b2c.finance.customeraccount.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountQueryModel;

@Repository
public class CustomerAccountH4Impl
		extends
			BaseH4Impl<CustomerAccountModel, CustomerAccountQueryModel>
		implements
			CustomerAccountDAO {

	/**
	 * 根据会员的uuid获取会员账户对象
	 * @param customerUuid
	 * @return 
	 * CustomerAccountModel
	 */
	public CustomerAccountModel getCustomerAccountModelByCustomerUuid(String customerUuid){
		StringBuffer hql = new StringBuffer(" from CustomerAccountModel  c where 1=1");
		hql.append("  and c.customerUuid =:customerUuid");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		
		Object object = query.uniqueResult();
		if(object != null){
			return (CustomerAccountModel)object;
		}
		
		return null;
	}
	
	/**
         * 
         * 根据会员Uuid获得会员帐号UUid
         * 
         * @param customerUuid
         * @return
         */
        public String getCustomerAccountUuidByCustomerUuid(String customerUuid) {
                StringBuffer hql = new StringBuffer(
                                "select c.uuid from CustomerAccountModel  c where 1=1");
                hql.append("  and c.customerUuid =:customerUuid");

                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("customerUuid", customerUuid);

                Object object = query.uniqueResult();
                if (object != null) {
                        return (String) object;
                }

                return "";
        }
}

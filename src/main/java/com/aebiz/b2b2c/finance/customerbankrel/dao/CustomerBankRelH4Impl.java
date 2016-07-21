package com.aebiz.b2b2c.finance.customerbankrel.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelModel;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelQueryModel;

@Repository
public class CustomerBankRelH4Impl extends BaseH4Impl<CustomerBankRelModel,CustomerBankRelQueryModel> implements CustomerBankRelDAO {

	/**
	 * 根据会员的uuid查询绑定申请对象,来判断该会员是否已经申请过绑定了
	 * @param uuid
	 * @return 
	 * CustomerBankRelModel
	 */
	public CustomerBankRelModel getCustomerBankRelModelByCustomerUuid(String uuid){
		StringBuffer hql = new StringBuffer(" from CustomerBankRelModel where customerUuid =:customerUuid");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", uuid);
		
		Object object = query.uniqueResult();
		if(object != null){
			return (CustomerBankRelModel)object;
		}
	
		return null;
	}
	 /**
         * 根据会员的编号获得会员和银行绑定的编号
         * 
         * @param customerUuid
         * @return
         */
	public String getCustomerBankRelUuidByCustomerUuid(String customerUuid) {
            StringBuffer hql = new StringBuffer(
                            "select o.uuid from CustomerBankRelModel o where o.customerUuid =:customerUuid");

            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("customerUuid", customerUuid);

            Object object = query.uniqueResult();
            if (object != null) {
                    return (String) object;
            }

            return "";
    }
}

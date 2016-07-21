package com.aebiz.b2b2c.finance.storefinanceaccount.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountModel;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountQueryModel;

@Repository
public class StoreFinanceAccountH4Impl
		extends
			BaseH4Impl<StoreFinanceAccountModel, StoreFinanceAccountQueryModel>
		implements
			StoreFinanceAccountDAO {

	/**
	 * 根据商户的uuid获取商户的财务账户信息
	 * @param accountUUid
	 * @return 
	 * StoreFinanceAccountModel
	 */
	public StoreFinanceAccountModel getStoreFinanceAccountModelByAccount(String accountUUid){
		StringBuffer hql = new StringBuffer(" from StoreFinanceAccountModel s where accountUuid =:accountUUid ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("accountUUid", accountUUid);
		
		Object object = query.uniqueResult();
		if(object != null){
			return (StoreFinanceAccountModel)object;
		}
		
		return null;
	}
	
	public String getStoreFinanceAccountUuidByAccount(String accountUUid) {
            StringBuffer hql = new StringBuffer(
                            "select s.uuid from StoreFinanceAccountModel s where accountUuid =:accountUUid ");

            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("accountUUid", accountUUid);

            Object object = query.uniqueResult();
            if (object != null) {
                    return (String) object;
            }

            return "";
    }
}

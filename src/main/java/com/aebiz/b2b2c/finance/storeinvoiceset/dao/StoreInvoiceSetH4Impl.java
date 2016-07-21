package com.aebiz.b2b2c.finance.storeinvoiceset.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetModel;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetQueryModel;

@Repository
public class StoreInvoiceSetH4Impl extends BaseH4Impl<StoreInvoiceSetModel,StoreInvoiceSetQueryModel> implements StoreInvoiceSetDAO {

	public StoreInvoiceSetModel getStoreInvoiceSetWebModelByAccoutUuid(
			String accountUuid) {
		StringBuffer hql = new StringBuffer("from StoreInvoiceSetModel si where 1=1");
		hql.append(" and si.accountUuid=:accountUuid");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("accountUuid", accountUuid);
		
		//获取唯一记录
		Object obj = query.uniqueResult();
		if(obj!=null){
			return (StoreInvoiceSetModel)obj;
		}
		return null;
	}
	
	public String getStoreInvoiceSetWebUuidByAccoutUuid(String accountUuid) {
            StringBuffer hql = new StringBuffer(
                            "select si.uuid from StoreInvoiceSetModel si where 1=1");
            hql.append(" and si.accountUuid=:accountUuid");
            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("accountUuid", accountUuid);

            // 获取唯一记录
            Object obj = query.uniqueResult();
            if (obj != null) {
                    return (String) obj;
            }
            return "";
    }
}

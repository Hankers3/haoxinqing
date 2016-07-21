package com.aebiz.b2b2c.finance.ledgerbystore.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreModel;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreQueryModel;

@Repository
public class LedgerByStoreH4Impl
		extends
			BaseH4Impl<LedgerByStoreModel, LedgerByStoreQueryModel>
		implements
			LedgerByStoreDAO {

	/**
	 * 根据商户uuid查询LedgerByStoreModel对象
	 * @param accountUuid
	 * @return 
	 * LedgerByStoreModel
	 */
	public LedgerByStoreModel getLedgerByStoreModelByAccountUuid(String accountUuid){
		StringBuffer hql = new StringBuffer(" from LedgerByStoreModel l where l.accountUuid =:accountUuid");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("accountUuid", accountUuid);
		
		Object object = query.uniqueResult();
		if(object != null){
			return (LedgerByStoreModel)object;
		}
		
		return null;
	}
	/**
	 * 获取所需的百分比总和
	 * 
	 * @param accountUuids
	 * @return
	 */
	public double sumLedgerRate(List<String> accountUuids) {
		StringBuffer hql = new StringBuffer(" select sum(ls.ledgerRate) from LedgerByStoreModel as ls where  ");
		hql.append(" ls.accountUuid in(:accountUuids)");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setParameterList("accountUuids", accountUuids.toArray());
		Object obj = q.uniqueResult();
		if(obj !=null ){
			return ((Number) q.uniqueResult()).doubleValue();
		}else{
			return 0;
		}
	}
	
	public String getLedgerByStoreUuidByAccountUuid(String accountUuid) {
            StringBuffer hql = new StringBuffer(
                            "select l.uuid from LedgerByStoreModel l where l.accountUuid =:accountUuid");

            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("accountUuid", accountUuid);

            Object object = query.uniqueResult();
            if (object != null) {
                    return (String) object;
            }

            return "";
    }
	
}

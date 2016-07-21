package com.aebiz.b2b2c.finance.storeeditbankbound.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundModel;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundQueryModel;

@Repository
public class EditBankBoundH4Impl extends BaseH4Impl<EditBankBoundModel,EditBankBoundQueryModel> implements EditBankBoundDAO {

	/**
	 * 通过商户的id等到一个商户银行卡绑定对象
	 * @param storeUuid	商户id
	 * @return	商户银行卡绑定对象
	 */
	public EditBankBoundModel getStoreEditBankBoundByStoreUuid(
			String storeUuid) {
		String hql = "select m from EditBankBoundModel m where storeUuid = :storeUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setParameter("storeUuid", storeUuid);
		return (EditBankBoundModel) query.uniqueResult();
	}
	
	public String getStoreEditBankBoundUuidByStoreUuid(String storeUuid) {
	    String hql = "select m.uuid from EditBankBoundModel m where storeUuid = :storeUuid";
	    Query query = this.getH4Session().createQuery(hql);
	    
	    query.setParameter("storeUuid", storeUuid);
	    Object obj = query.uniqueResult();
	    
	       if (obj != null) {
    	           return (String) obj;
    	        }
	         return "";
	}

}

package com.aebiz.b2b2c.finance.ledgerbystorecategory.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryModel;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryQueryModel;

@Repository
public class LedgerByStoreCategoryH4Impl
		extends
			BaseH4Impl<LedgerByStoreCategoryModel, LedgerByStoreCategoryQueryModel>
		implements
			LedgerByStoreCategoryDAO {

	/**
	 * 根据商户的uuid和商品分类的uuid获取LedgerByStoreCategoryModel对象
	 * 
	 * @param accountUuid
	 * @param categoryUuid
	 * @return LedgerByStoreCategoryModel
	 */
	public LedgerByStoreCategoryModel getLedgerByStoreCategoryModelByAccountUuidAndCategoryUuid(
			String accountUuid, String categoryUuid){
		StringBuffer hql = new StringBuffer(" from LedgerByStoreCategoryModel l where l.accountUuid =:accountUuid and l.categoryUuid =:categoryUuid ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("accountUuid", accountUuid);
		query.setString("categoryUuid", categoryUuid);
		
		Object object = query.uniqueResult();
		
		if(object != null){
			return (LedgerByStoreCategoryModel)object;
		}
		
		return null;
	}
	public String getLedgerByStoreCategoryUuidByAccountUuidAndCategoryUuid(
                String accountUuid, String categoryUuid) {
        StringBuffer hql = new StringBuffer(
                        "select l.uuid from LedgerByStoreCategoryModel l where l.accountUuid =:accountUuid and l.categoryUuid =:categoryUuid ");

        Query query = this.getH4Session().createQuery(hql.toString());
        query.setString("accountUuid", accountUuid);
        query.setString("categoryUuid", categoryUuid);

        Object object = query.uniqueResult();

        if (object != null) {
                return (String) object;
        }

        return "";
}
}

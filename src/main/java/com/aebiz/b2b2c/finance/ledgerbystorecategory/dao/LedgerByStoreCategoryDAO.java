package com.aebiz.b2b2c.finance.ledgerbystorecategory.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryModel;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryQueryModel;

public interface LedgerByStoreCategoryDAO
		extends
			BaseDAO<LedgerByStoreCategoryModel, LedgerByStoreCategoryQueryModel> {

	/**
	 * 根据商户的uuid和商品分类的uuid获取LedgerByStoreCategoryModel对象
	 * 
	 * @param accountUuid
	 * @param categoryUuid
	 * @return LedgerByStoreCategoryModel
	 */
	public LedgerByStoreCategoryModel getLedgerByStoreCategoryModelByAccountUuidAndCategoryUuid(
			String accountUuid, String categoryUuid);
	/**
	 * 获取LedgerByStoreCategoryUuid
	 * @param accountUuid
	 * @param categoryUuid
	 * @return
	 */
	public String getLedgerByStoreCategoryUuidByAccountUuidAndCategoryUuid(
                String accountUuid, String categoryUuid);
}
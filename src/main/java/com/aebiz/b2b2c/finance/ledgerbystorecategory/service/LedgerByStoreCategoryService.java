package com.aebiz.b2b2c.finance.ledgerbystorecategory.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryModel;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryQueryModel;

public interface LedgerByStoreCategoryService extends BaseService<LedgerByStoreCategoryModel,LedgerByStoreCategoryQueryModel>{

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
	 * 添加或者更新商户商品分类的分账比率和运费是否参与分账信息
	 * @param accountUuid 商户uuid
	 * @param ledgerRate 分账比率
	 * @param categoryUuid 分类uuid
	 * @return 
	 * boolean
	 */
	public boolean addOrUpdateLedgerRate(String accountUuid,String ledgerRate,String categoryUuid);
}

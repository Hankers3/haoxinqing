package com.aebiz.b2b2c.finance.ledgerbystore.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreModel;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreQueryModel;

public interface LedgerByStoreService
		extends
			BaseService<LedgerByStoreModel, LedgerByStoreQueryModel> {

	/**
	 * 根据accountUuid查询LedgerByStoreModel对象
	 * @param accountUuid
	 * @return 
	 * LedgerByStoreModel
	 */
	public LedgerByStoreModel getLedgerByStoreModelByAccountUuid(String accountUuid);
	
	/**
	 * 添加或者更新分账比率和运费是否参与分账信息
	 * @param accountUuid 
	 * @param ledgerRate 分账比率
	 * @param freightJoinRouting 运费是否参与分账
	 * @return  boolean
	 */
	public boolean addOrUpdateLedgerRate(String accountUuid,String ledgerRate,String freightJoinRouting);
	
	/**
	 * 获取所需的百分比总和
	 * 
	 * @param accountUuids
	 * @return
	 */
	public double sumLedgerRate(List<String> accountUuids);
}

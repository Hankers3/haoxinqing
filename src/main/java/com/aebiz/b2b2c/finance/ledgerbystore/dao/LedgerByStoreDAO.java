package com.aebiz.b2b2c.finance.ledgerbystore.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreModel;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreQueryModel;

public interface LedgerByStoreDAO
		extends
			BaseDAO<LedgerByStoreModel, LedgerByStoreQueryModel> {

	/**
	 * 根据商户uuid查询LedgerByStoreModel对象
	 * @param accountUuid
	 * @return 
	 * LedgerByStoreModel
	 */
	public LedgerByStoreModel getLedgerByStoreModelByAccountUuid(String accountUuid);
	
	/**
	 * 获取所需的百分比总和
	 * 
	 * @param accountUuids
	 * @return
	 */
	public double sumLedgerRate(List<String> accountUuids);
	
	

        /**
         * 根据商户uuid查询LedgerByStoreModel对象的uuid
         * 
         * @param accountUuid
         * @return LedgerByStoreModel
         */
        public String getLedgerByStoreUuidByAccountUuid(String accountUuid);
}
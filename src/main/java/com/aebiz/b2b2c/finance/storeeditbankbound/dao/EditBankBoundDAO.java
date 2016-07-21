package com.aebiz.b2b2c.finance.storeeditbankbound.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundModel;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundQueryModel;

public interface EditBankBoundDAO extends BaseDAO<EditBankBoundModel,EditBankBoundQueryModel>{

	/**
	 * 通过商户的id等到一个商户银行卡绑定对象
	 * @param storeUuid	商户id
	 * @return	商户银行卡绑定对象
	 */
	public EditBankBoundModel getStoreEditBankBoundByStoreUuid(String storeUuid);
	
	public String getStoreEditBankBoundUuidByStoreUuid(String storeUuid);
}
package com.aebiz.b2b2c.order.accountkey.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyQueryModel;

public interface AccountKeyService extends BaseService<AccountKeyModel,AccountKeyQueryModel>{

	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public AccountKeyModel getAccountKeyModelById(String id);
	
	
	/**
         * 根据出生日期获得age的大小
         * 
         * @param customerUuid
         * @param birthday
         * @return
         */
        public void updateAccountVlue();
}

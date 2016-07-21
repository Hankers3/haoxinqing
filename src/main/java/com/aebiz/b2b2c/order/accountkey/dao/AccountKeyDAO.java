package com.aebiz.b2b2c.order.accountkey.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyQueryModel;

public interface AccountKeyDAO extends BaseDAO<AccountKeyModel,AccountKeyQueryModel>{

	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public AccountKeyModel getAccountKeyModelById(String id);
	/**
	 * 
	 * @Description: (定时任务将值每天设置为0)    
	 * @author XP  
	 * @date 2016-1-26 下午1:08:46
	 */
	public void updateAccountVlue();

}
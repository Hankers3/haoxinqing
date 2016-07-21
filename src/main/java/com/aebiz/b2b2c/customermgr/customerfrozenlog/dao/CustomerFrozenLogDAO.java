package com.aebiz.b2b2c.customermgr.customerfrozenlog.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogModel;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogQueryModel;

public interface CustomerFrozenLogDAO extends
		BaseDAO<CustomerFrozenLogModel, CustomerFrozenLogQueryModel> {
	/**
	 * 根据会员编号获取会员最新冻结日志记录
	 * 
	 * @param customerUuid
	 * @return
	 */
	public CustomerFrozenLogModel getFrozenLog(String customerUuid);
	
	/**
	 * 根据会员编号获取会员最新解冻日志记录
	 * 
	 * @param customerUuid
	 * @return
	 */
	public CustomerFrozenLogModel getUnFrozenLog(String customerUuid);
	
	public String getFrozenLogUuid(String customerUuid);
	        
	public String getUnFrozenLogUuid(String customerUuid);
}
package com.aebiz.b2b2c.customermgr.customerfrozenlog.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogModel;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogQueryModel;

public interface CustomerFrozenLogService extends
		BaseService<CustomerFrozenLogModel, CustomerFrozenLogQueryModel> {
	/**
	 * 添加会员冻结日志记录
	 * 
	 * @param uuids
	 * @param frozenType
	 * @param frozenReason
	 * @param oper
	 */
	public void addFrozenLog(String customerUuid, String frozenType,
			String note, String oper);

	/**
	 * 添加会员解冻日志记录
	 * 
	 * @param uuids
	 * @param frozenReason
	 * @param oper
	 */
	public void addUnFrozenLog(String customerUuid, String note, String oper);

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

}

package com.aebiz.b2b2c.customermgr.customeraudit.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthQueryModel;

public interface CustomerAuditService extends
		BaseService<CustomerAuthModel, CustomerAuthQueryModel> {
	/**
	 * 会员实名认证审核通过
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param auditReason
	 *            审核原因
	 */
	public void doAuditPass(String customerUuid, String auditReason);
	
	/**
	 * 会员实名认证审核不通过
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param auditReason
	 *            审核原因
	 */
	public void doAuditUnPass(String customerUuid, String auditReason);

}

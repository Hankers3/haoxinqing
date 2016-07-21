package com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogQueryModel;

/**
 * 
 * 商户的虚拟账户日志
 * 
 * 1.生成提现申请，需要记录日志<br />
 * 2.提现成功，需要记录日志<br />
 * 3.充值申请,不需要记录日志(金额没有发生变动)<br />
 * 4.充值缴费成功，记录日志<br />
 * 
 * @author duandeyi
 * 
 */
public interface VirtualAccountStoreLogInteractive
		extends
		BaseService<VirtualAccountStoreLogModel, VirtualAccountStoreLogQueryModel> {

	/**
	 * 商户的虚拟账户日志
	 * @param storeUuid		商户uuid 
	 * @param operType		收支类型
	 * @param operAmount	操作金额
	 * @param nowBalance	当前余额 
	 * @param documentNo	单据编号
	 * @param description	备注
	 * @param frozenState	冻结状态
	 * @param rechargeType  类型
	 */
	public void saveVirtualAccountStoreLog(String storeUuid, String operType,
			double operAmount, double nowBalance, String documentNo,
			String description, String frozenState,String rechargeType);
}

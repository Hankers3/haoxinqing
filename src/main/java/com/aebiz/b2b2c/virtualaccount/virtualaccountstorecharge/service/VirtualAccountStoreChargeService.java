package com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo.VirtualAccountStoreChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo.VirtualAccountStoreChargeQueryModel;

public interface VirtualAccountStoreChargeService
		extends
		BaseService<VirtualAccountStoreChargeModel, VirtualAccountStoreChargeQueryModel> {

	/**
	 * 重置成功后,需要把重置状态置为成功,同时需要添加一条账户日志记录,操作类型为收入
	 * 
	 * @param m
	 *            void
	 */
	public void UpdateChargeStateToSuccess(VirtualAccountStoreChargeModel m);

	/**
	 * 充值失败后需要把重置状态修改为失败,不需要添加日志
	 * 
	 * @param m
	 *            void
	 */
	public void UpdateChargeStateToFail(VirtualAccountStoreChargeModel m);
}

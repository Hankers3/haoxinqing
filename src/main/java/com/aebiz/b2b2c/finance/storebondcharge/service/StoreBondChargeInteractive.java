package com.aebiz.b2b2c.finance.storebondcharge.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.storebondcharge.vo.StoreBondChargeModel;
import com.aebiz.b2b2c.finance.storebondcharge.vo.StoreBondChargeQueryModel;

public interface StoreBondChargeInteractive extends
		BaseService<StoreBondChargeModel, StoreBondChargeQueryModel> {

	/**
	 * 添加完合同后生成的商户保证金单据,供商户去支付
	 * @param accountUuid 商户uuid
	 * @param contract 合同uuid
	 * @param amount 保证金金额
	 * void
	 */
	public void createBondChargeByContract(String accountUuid,String contract,float amount);
	
	/**
	 * 违约处罚扣保证金时,缴保证金单据<br>
	 * 
	 * 这里fromSource的值为 1(违约生成保证金单据,扣减保证金)<br>
	 * 
	 * 这个时候需要把商户账户的已冻结保证金金额在原来的基础上加上这次扣的,同时把保证金余额减去扣的<br>
	 * 
	 * 当支付成功时把冻结金额的减去,<br>
	 * 
	 * 但是需要把保证金余额加上充值的金额,因为冻结的部分在支付成功后就相当于已经扣除了保证金,这样就能保证财务的的平衡
	 * @param model 
	 * void
	 */
	public void createBondChargeByBreak(StoreBondChargeModel model);
}

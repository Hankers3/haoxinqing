package com.aebiz.b2b2c.finance.customerbankrel.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelModel;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelQueryModel;

/**
 * 会员提出绑定申请,申请完,会员是不能立即去验证的,由平台给申请记录中的银行卡打一定的金额,<br>
 * 
 * 然后后台更新这个金额,并且把验证状态修改为待验证状态,<br>
 * 
 * 这时会员可以根据收到的金额去和会员中心验证,如果错误3次那么就不能再次验证了,需要联系平台
 *
 * @author tangyunkai
 *
 * @date 2014年12月20日 下午12:26:57 
 *
 */
public interface CustomerBankRelService extends BaseService<CustomerBankRelModel,CustomerBankRelQueryModel>{

	/**
	 * 更新验证的金额,把验证状态修改为待验证状态,<br>
	 * 
	 * 会员可以根据银行卡收到的金额去验证
	 * @param uuid
	 * @param vilidateMount 
	 * void
	 */
	public boolean updateVilidateMount(String uuid,String vilidateMount);
	
	/**
	 * 根据会员的uuid查询绑定申请对象,来判断该会员是否已经申请过绑定了
	 * @param uuid
	 * @return 
	 * CustomerBankRelModel
	 */
	public CustomerBankRelModel getCustomerBankRelModelByCustomerUuid(String uuid);
	
	/**
	 * 验证会员输入的金额,判定是否成功
	 * @param uuid
	 * @param amount
	 * @return 
	 * boolean
	 */
	public CustomerBankRelModel checkValidateAmount(String uuid,String amount);
	
	/**
	 * 在平台打款修改验证金额之前,会员自己可以修改绑定信息
	 * @param m 
	 * void
	 */
	public void updateBankRel(CustomerBankRelModel m);
}

package com.aebiz.b2b2c.finance.customeraccount.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountQueryModel;
/**
 * 会员账户信息表对外接口,在添加会员时需要添加进一条记录
 *
 * @author tangyunkai
 *
 * @date 2014年12月8日 下午8:33:52 
 *
 */
public interface CustomerAccountInteractive
		extends
			BaseService<CustomerAccountModel, CustomerAccountQueryModel> {
	
	/**
	 * 根据会员的uuid获取会员账户对象
	 * @param customerUuid
	 * @return 
	 * CustomerAccountModel
	 */
	public CustomerAccountModel getCustomerAccountModelByCustomerUuid(String customerUuid);

	/**
	 * 根据会员uuid获取虚拟账户的支付密码
	 * 
	 * @param customerUuid
	 * @return 
	 * String
	 */
	public String getPayPasswdByCustomerUuid(String customerUuid);
	
	/**
	 * 根据密码和会员的uuid校验输入的虚拟账户密码是否正确
	 * @param password
	 * @param customerUuid
	 * @return 
	 * boolean
	 */
	public boolean checkPassword(String password,String customerUuid);
	
	/**
	 * 增加会员可用积分
	 * @param customerUuid
	 * @param integral 
	 * void
	 */
	public void addIntegral(String customerUuid,int integral);
	
	/**
	 * 扣减会员可用积分
	 * @param customerUuid
	 * @param integral 
	 * void
	 */
	public void reduceIntegral(String customerUuid,int integral);
	
	/**
	 * 增加绑定礼品卡的数量
	 * @param customerUuid 
	 * void
	 */
	public void addGiftCardCount(String customerUuid);
	
	/**
	 * 增加会员虚拟账余额
	 * @param customerUuid
	 * @param accountBalance 
	 * void
	 */
	public void addAccountBalance(String customerUuid,double accountBalance);
	
	/**
	 * 扣减会员虚拟账户余额
	 * @param customerUuid
	 * @param accountBalance 
	 * void
	 */
	public void reduceAccountBalance(String customerUuid,double accountBalance);
	
	/**
	 * 订单取消返还虚拟账户余额
	 * @param customerUuid 会员uuid
	 * @param accountBalance 返还金额
	 * @param orderNo  订单号
	 */
	public void returnAccountBalance(String customerUuid,double accountBalance,String orderNo);
}

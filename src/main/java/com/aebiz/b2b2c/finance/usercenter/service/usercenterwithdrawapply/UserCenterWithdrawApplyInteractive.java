package com.aebiz.b2b2c.finance.usercenter.service.usercenterwithdrawapply;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;
/**
 * 会员提现申请。</br>
 * 
 * 会员提现申请，申请的金额会临时冻结。等待平台后台的审核。
 * 
 * @author tangyunkai
 *
 */
public interface UserCenterWithdrawApplyInteractive extends BaseService<WithdrawApplyModel,WithdrawApplyQueryModel>{

	/**
	 * 查询会员申请在某段时间内的总数
	 * @param customerUuid
	 * @param state 0:未处理  1:体现成功  2:体现失败
	 * @param timeType 1:3个月内  2:三个月前 3:一年内
	 * @return  int
	 */
	public void saveExtractionMoney(CustomerAccountModel caModel, WithdrawApplyModel paramM, CustomerModel cm, String userCenterUuid, String applyMoney, String note) ;
	
	/**
	 * 查询会员申请在某段时间内的总数
	 * @param customerUuid
	 * @param state 0:未处理  1:体现成功  2:体现失败
	 * @param timeType 0:3个月内  1:三个月前 2:一年内
	 * @return  int
	 */
	public int getCustomerWithdrawAppllyCount(String customerUuid,String state,String timeType);
	
	/**
	 * 查询会员在某段时间内的体现申请,带分页
	 * @param customerUuid
	 * @param state
	 * @param timeType
	 * @param fromPage
	 * @param pageShow
	 * @return 
	 * List<WithdrawApplyModel>
	 */
	public List<WithdrawApplyModel> getCustomerWithdrawAppllyList(String customerUuid,String state,String timeType,String fromPage,String pageShow);
}

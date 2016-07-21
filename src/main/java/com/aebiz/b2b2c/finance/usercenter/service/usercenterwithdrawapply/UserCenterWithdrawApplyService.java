package com.aebiz.b2b2c.finance.usercenter.service.usercenterwithdrawapply;

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
 * @author liuyongtao
 *
 */
public interface UserCenterWithdrawApplyService extends BaseService<WithdrawApplyModel,WithdrawApplyQueryModel>{

	/**
	 * 保存提现记录，更改会员帐户数据
	 * @param caModel	会员帐户
	 * @param paramM	提现申请
	 * @param cm	会员信息
	 * @param userCenterUuid	用户id
	 * @param applyMoney	提现金额
	 * @param note	备注
	 */
	public void saveExtractionMoney(CustomerAccountModel caModel, WithdrawApplyModel paramM, CustomerModel cm, String userCenterUuid, String applyMoney, String note) ;
	
}
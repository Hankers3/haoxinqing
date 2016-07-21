package com.aebiz.b2b2c.finance.usercenter.dao.usercenterwithdrawapply;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;

public interface UserCenterWithdrawApplyDAO extends
		BaseDAO<WithdrawApplyModel, WithdrawApplyQueryModel> {

	/**
	 * 查询会员申请在某段时间内的总数
	 * @param customerUuid
	 * @param state 0:未处理  1:体现成功  2:体现失败
	 * @param timeType 1:3个月内  2:三个月前 3:一年内
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
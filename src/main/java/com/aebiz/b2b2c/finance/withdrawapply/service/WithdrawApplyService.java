package com.aebiz.b2b2c.finance.withdrawapply.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;

/**
 * 
 * 提现申请
 * 
 * 1.买家发起提现申请，申请后，虚拟账户中该笔金额被临时冻结<br />
 * 
 * 2.平台审核：<br />
 * 2.1驳回:<br />
 * 平台不同意此提现申请，驳回后，申请单据的状态为关闭 <br />
 * 2.2已转帐:<br />
 * 转账需要更新当前的申请状态为成功，并且更新用户帐户信息表的虚拟帐户余额数值
 * 
 * @author liuyongtao
 * 
 */
public interface WithdrawApplyService extends
		BaseService<WithdrawApplyModel, WithdrawApplyQueryModel> {

	/**
	 * 平台后台驳回商户的提现申请
	 * 
	 * 1.将申请单状态修改为失败状态<br />
	 * 2.解冻申请时冻结的金额<br />
	 * 3.更新会员的帐户金额（将之前冻结时扣减的可用金额加回去）<br />
	 * 
	 * @param withdrawApplyUuid
	 * @param rejectReason
	 */
	public void reject(String withdrawApplyUuid, String rejectReason);
	
	/**
	 * 平台后台同意商户提现申请<br/>
	 * 
	 * 1.将申请单状态修改为已转帐状态
	 * 2.冻结金额减去申请提现金额
	 * @param withdrawApplyUuid
	 */
	public void transfer(String withdrawApplyUuid);
	
	/**
	 * 查询提现记录
	 * @param customerUuid  会员id
	 * @param time  要查询的时间段
	 * @param pageNo  
	 * @param pageCount 
	 * @return
	 */
	public List<WithdrawApplyModel> getApplyList(String customerUuid, String time,int pageNo,int pageCount);

	/**
	 * 通过医生查询
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<WithdrawApplyModel> getByDoctorUuid(String doctorUuid);
}

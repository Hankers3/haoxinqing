package com.aebiz.b2b2c.finance.withdrawapply.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;

public interface WithdrawApplyDAO extends
		BaseDAO<WithdrawApplyModel, WithdrawApplyQueryModel> {

	/**
	 * 查询提现记录
	 * 
	 * @param customerUuid
	 *            会员id
	 * @param time
	 *            要查询的时间段 0：最近一周 1：最近一个月 2：最近3个月 3：最近半年
	 * @param pageNo
	 * @param pageCount
	 * @return
	 */
	public List<WithdrawApplyModel> getApplyList(String customerUuid,
			String time, int pageNo, int pageCount);

	/**
	 * 通过医生查询
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<WithdrawApplyModel> getByDoctorUuid(String doctorUuid);
}
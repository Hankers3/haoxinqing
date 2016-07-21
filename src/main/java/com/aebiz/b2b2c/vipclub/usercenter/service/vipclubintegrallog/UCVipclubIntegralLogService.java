package com.aebiz.b2b2c.vipclub.usercenter.service.vipclubintegrallog;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;

public interface UCVipclubIntegralLogService extends BaseService<VipclubIntegralLogModel, VipclubIntegralLogQueryModel> {	
	/**
	 * 通过会员查询model，查询该类型会员积分总数
	 * 
	 * @param qm
	 * @return int
	 */
	public int getTypeIntegralSumByConditon(VipclubIntegralLogQueryModel qm);

}

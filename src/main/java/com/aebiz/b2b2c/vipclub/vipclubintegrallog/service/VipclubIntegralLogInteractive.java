package com.aebiz.b2b2c.vipclub.vipclubintegrallog.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;

public interface VipclubIntegralLogInteractive extends BaseService<VipclubIntegralLogModel, VipclubIntegralLogQueryModel> {
	/**
	 * 下订单增加积分，需要会员uuid，积分数量，订单uuid，商品uuid，描述
	 * @param customerUuid
	 * @param integralCount
	 * @param orderMainUuid
	 * @param productUuid
	 * @param descrption
	 */
	public void addIntegralByOrder(String customerUuid,int integralCount,String orderMainUuid,String productUuid,String descrption);
	
	/**
	 * 下订单减少积分,需要需要会员uuid，积分数量，订单uuid，商品uuid，描述
	 * @param customerUuid
	 * @param integralCount
	 * @param orderMainUuid
	 * @param productUuid
	 * @param descrption
	 */
	public void reduceIntegralByOrder(String customerUuid,int integralCount,String orderMainUuid,String productUuid,String descrption);
}

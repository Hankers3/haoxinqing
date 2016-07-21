package com.aebiz.b2b2c.cms.ordermainappraise.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseModel;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseQueryModel;

public interface OrderMainAppraiseService extends BaseService<OrderMainAppraiseModel,OrderMainAppraiseQueryModel>{
	
	/**
	 * 
	 * 获取商家的平均评分<br/>
	 * 1 商家评分；2发货速度评分；3如实描述评分；4服务态度评分
	 * 
	 * @param storeUuid
	 * @return
	 */
	public double getAverageScore(String storeUuid,String type);
	

	/**
	 * 
	 * 根据订单id获取订单评价
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAppraiseModel getOrderMainAppraiseByOrderUuid(String orderUuid);
	
}

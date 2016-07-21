package com.aebiz.b2b2c.cms.ordermainappraise.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseModel;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseQueryModel;

public interface OrderMainAppraiseDAO extends BaseDAO<OrderMainAppraiseModel,OrderMainAppraiseQueryModel>{
	/**
	 * 
	 * 获取商家的平均评分
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
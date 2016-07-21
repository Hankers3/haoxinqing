package com.aebiz.b2b2c.order.servicestaffcenter.service.ordermain;

import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;

public interface OrderMainUcenterService extends
		BaseService<OrderMainModel, OrderMainQueryModel> {

	/**
	 * 会员中心查询订单列表
	 * 
	 * @param paramQM
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List<OrderMainModel> getOrderListByCondition(
			OrderMainQueryModel paramQM, int paramInt1, int paramInt2);


	/**
	 * 会员中心订单查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm);

	/**
	 * 根据会员id和订单类型查询总数量
	 * @param OrderMainQueryModel
	 * @param state 订单状态
	 * @return
	 */
	public int getOrderCountByCustomerId(OrderMainQueryModel qm,String serviceStaffUuid,String state);
	
	/**
	 * 查询家政员收入
	 * @param id
	 * @return
	 * @2015-4-17
	 * @author :SZH
	 */
	public Map<String,Object> getServiceIncome(String id);
	/**
	 * 查询家政员1-12月份的收入
	 * @param id
	 * @return
	 * @2015-4-17
	 * @author :SZH
	 */
	public Map<String,Object> getALLMonthIncome(String id);
}

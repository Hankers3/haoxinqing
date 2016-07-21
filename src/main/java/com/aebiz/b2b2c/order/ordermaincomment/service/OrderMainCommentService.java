package com.aebiz.b2b2c.order.ordermaincomment.service;

import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentModel;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentQueryModel;

public interface OrderMainCommentService extends BaseService<OrderMainCommentModel,OrderMainCommentQueryModel>{

	
	/**
	 * 根据id修改处理意见
	 * hedongfei
	 * @param 
	 */
	public void updateconductIdea(String uuid,String conductIdea);
	
	/**
	 * 通过主订单编号获取订单评价信息表的服务态度评分
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	public String getOrderMainCommentServiceScoreByorderMainUuid(String orderMainUuid);
	/**
	 * 通过主订单号来获取订单信息及评价
	 * @param customerUuid 
	 * @param pageCount 
	 * @param pageNo 
	 * @return
	 */
	public List<Map<String,Object>> getOrderMainCommentAndOthers(String customerUuid,int pageCount,int pageNo);
	

	/**
	 * 根据订单id获取评价列表
	 * @param orderMainUuuid
	 * @author zdx
	 * @return
	 */
	public List<OrderMainCommentModel> getCommentByOrderId(String orderMainUuid);
	/**
	 * 通过主订单号来获取订单评价
	 * @param orderMainUuid
	 * @return 
	 * @2015-3-27:2015-3-27
	 * @author : SZH
	 */
	public OrderMainCommentModel getOrderMainCommentByOrderMainUuid(String orderMainUuid);
	
}

package com.aebiz.b2b2c.cms.ordershow.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowModel;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowQueryModel;
import com.aebiz.b2b2c.cms.usercenter.web.ordershow.vo.OrderShowWebModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;

/**
 * 
 * 订单明细晒单发布、查看<br/>
 * 
 * 保存晒单同时需要保存晒单图片<br/>
 * 
 * @author cj
 * 
 */

public interface OrderShowService extends
		BaseService<OrderShowModel, OrderShowQueryModel> {

	/**
	 * 通过订单明细的UUID查询对应的晒单记录
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public OrderShowModel getOrderShowModelByDetailUuid(String orderDetailUuid);

	/**
	 * 通过订单明细的UUID查询对应的晒单记录和晒单的图片
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public OrderShowModel getOrderShowModelAndPicsByDetailUuid(
			String orderDetailUuid);

	/**
	 * 
	 * 保存晒单、晒单图片
	 * 
	 * 调用会员系统提供的接口保存晒单人名称
	 * 
	 * @param odm
	 */
	public void saveShare(OrderDetailModel odm, OrderShowModel m);

	/**
	 * 
	 * 晒单审核、审核不通过需要保存审核不通过原因
	 * 
	 * @param m
	 */
	public void saveReview(OrderShowModel m);

	/**
	 * 
	 * 获取每个订单明细和商品图片的封装数据model集合
	 * 
	 * @param detailList
	 * @return
	 */
	public List<OrderShowWebModel> getOrderDetailWebModels(
			List<OrderDetailModel> detailList);

	/**
	 * 通过商品的UUID查询对应的晒单记录和晒单的图片
	 * 
	 * @param productUuid
	 * @return
	 */
	public List<OrderShowModel> getOrderShowModelAndPicsByProductUuid(
			String productUuid, int start, int showPage);

	/**
	 * 通过商品的UUID查询对应的晒单数量
	 * 
	 * @param productUuid
	 * @return
	 */
	public int getOrderShowModelCountAndPicsByProductUuid(String productUuid);
}

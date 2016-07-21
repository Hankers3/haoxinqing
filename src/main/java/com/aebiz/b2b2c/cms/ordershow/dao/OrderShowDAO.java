package com.aebiz.b2b2c.cms.ordershow.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowModel;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowQueryModel;

public interface OrderShowDAO extends
		BaseDAO<OrderShowModel, OrderShowQueryModel> {

	/**
	 * 通过订单明细的UUID查询对应的晒单记录
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public OrderShowModel getOrderShowModelByDetailUuid(String orderDetailUuid);

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
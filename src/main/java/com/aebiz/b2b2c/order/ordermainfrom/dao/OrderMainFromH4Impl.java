package com.aebiz.b2b2c.order.ordermainfrom.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.ordermainfrom.vo.OrderMainFromModel;
import com.aebiz.b2b2c.order.ordermainfrom.vo.OrderMainFromQueryModel;

@Repository
public class OrderMainFromH4Impl extends
		BaseH4Impl<OrderMainFromModel, OrderMainFromQueryModel> implements
		OrderMainFromDAO {

	/**
	 * 根据订单编号获得订单的促销信息
	 * 
	 * 订单的促销信息单独存储在这张表中，例如订单优惠的金额，参与活动的编号等促销信息
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	public OrderMainFromModel getOrderMainFromModelByOrderId(
			String orderMainUuid) {

		StringBuffer sb = new StringBuffer(
				"from OrderMainFromModel omfm where 1=1");
		sb.append(" and omfm.orderMainUuid=:orderMainUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderMainUuid);

		List<OrderMainFromModel> omamList = q.list();
		if (omamList != null && omamList.size() > 0) {
			return omamList.get(0);
		}
		return null;
	}
}

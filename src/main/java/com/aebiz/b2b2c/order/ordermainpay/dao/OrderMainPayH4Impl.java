package com.aebiz.b2b2c.order.ordermainpay.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayModel;
import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayQueryModel;

@Repository
public class OrderMainPayH4Impl extends
		BaseH4Impl<OrderMainPayModel, OrderMainPayQueryModel> implements
		OrderMainPayDAO {

	/**
	 * 获得订单的第一步支付信息
	 * 
	 * 如果是预售订单，则第一笔信息为首付款
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderMainPayModel getAdvanceOrderMainPayModel(String orderId) {

		StringBuffer sb = new StringBuffer(
				"from OrderMainPayModel ompm where 1=1");
		sb.append(" and ompm.orderMainUuid=:orderMainUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderId);

		List<OrderMainPayModel> list = q.list();

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
}

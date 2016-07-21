package com.aebiz.b2b2c.order.orderclose.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.orderclose.vo.OrderCloseModel;
import com.aebiz.b2b2c.order.orderclose.vo.OrderCloseQueryModel;

@Repository
public class OrderCloseH4Impl extends
		BaseH4Impl<OrderCloseModel, OrderCloseQueryModel> implements
		OrderCloseDAO {

	/**
	 * 根据订单编号查询订单的关闭消息
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderCloseModel getOrderCloseModelByOrderUuid(String orderUuid) {
		StringBuffer sb = new StringBuffer("from OrderCloseModel ocm where 1=1");
		sb.append(" and ocm.orderUuid=:orderMainUuid");
		sb.append(" order by ocm.cancelTime desc");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderUuid);

		List<OrderCloseModel> ocmList = q.list();
		if (ocmList != null && ocmList.size() > 0) {
			return (OrderCloseModel) ocmList.get(0);
		}

		return null;
	}
}

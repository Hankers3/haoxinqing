package com.aebiz.b2b2c.order.orderdetaildiscount.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.orderdetaildiscount.vo.OrderDetailDiscountModel;
import com.aebiz.b2b2c.order.orderdetaildiscount.vo.OrderDetailDiscountQueryModel;

@Repository
public class OrderDetailDiscountH4Impl extends
		BaseH4Impl<OrderDetailDiscountModel, OrderDetailDiscountQueryModel>
		implements OrderDetailDiscountDAO {

	/**
	 * 根据订单明细查询订单明细对应的促销信息
	 * 
	 * @param detailUuid
	 * @return
	 */
	public OrderDetailDiscountModel getOrderDetailDiscountModelByDetailUuid(
			String detailUuid) {

		StringBuffer sb = new StringBuffer(
				"from OrderDetailDiscountModel oddm where 1=1");
		sb.append(" and oddm.orderDetailUuid=:orderDetailUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderDetailUuid", detailUuid);

		List<OrderDetailDiscountModel> oddmList = q.list();

		if (oddmList != null && oddmList.size() > 0) {
			return oddmList.get(0);
		}

		return null;
	}
}

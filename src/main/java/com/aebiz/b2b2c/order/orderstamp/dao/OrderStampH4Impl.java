package com.aebiz.b2b2c.order.orderstamp.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampModel;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampQueryModel;

@Repository
public class OrderStampH4Impl extends
		BaseH4Impl<OrderStampModel, OrderStampQueryModel> implements
		OrderStampDAO {

	/**
	 * 
	 * 通过订单ID获得订单标记
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderStampModel getOrderStampModelByOrderUuid(String orderUuid) {
		StringBuffer sb = new StringBuffer("from OrderStampModel osm where 1=1");
		sb.append(" and osm.orderUuid=:orderUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderUuid", orderUuid);

		Object obj = q.uniqueResult();

		if (obj != null) {
			return (OrderStampModel) obj;
		} else {
			return null;
		}
	}
	/**
	 * 
	 * 通过confId获取录音信息
	 * 
	 * @param confId
	 * @return
	 */
	@Override
	public OrderStampModel getOrderStampByConfId(String confId) {
		StringBuffer sb = new StringBuffer("from OrderStampModel osm where 1=1");
		sb.append(" and osm.confId=:confId");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("confId", confId);
		
		List<OrderStampModel> list  = q.list();
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}

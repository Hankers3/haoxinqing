package com.aebiz.b2b2c.order.ordercomplain.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainModel;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainQueryModel;

@Repository
public class OrderComplainH4Impl extends
		BaseH4Impl<OrderComplainModel, OrderComplainQueryModel> implements
		OrderComplainDAO {
	/**
	 * 根据订单获取订单是否已投诉
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	@Override
	public boolean getComplainState(String orderMainUuid) {
		StringBuffer hql = new StringBuffer(
				"select count(o.uuid) from  OrderComplainModel as o where 1=1");
		hql.append(" and o.orderMainUuid =:orderMainUuid");
		
		Query q= this.getH4Session().createQuery(hql.toString());
		q.setString("orderMainUuid", orderMainUuid);
		
		Long num = (Long) q.uniqueResult();
		if(num>0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 获得订单投诉对象
	 * @param orderMainUuid
	 * @return
	 */
	@Override
	public OrderComplainModel getOrderComplain(String orderMainUuid) {
		StringBuffer hql = new StringBuffer(" from OrderComplainModel as o where 1=1 ");
		hql.append(" and o.orderMainUuid=:orderMainUuid");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("orderMainUuid",orderMainUuid);
		
		List<OrderComplainModel> list = q.list();
		
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据订单获取订单投诉状态
	 * @param orderMainUuid
	 * @return
	 */
	@Override
	public String getState(String orderMainUuid) {
		StringBuffer hql = new StringBuffer("select o.state from OrderComplainModel as o where 1=1 ");
		hql.append(" and o.orderMainUuid=:orderMainUuid");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("orderMainUuid",orderMainUuid);
		
		List<String> list = q.list();
		
		if(list.size()>0){
			return list.get(0);
		}
		return null;
		
	}

}

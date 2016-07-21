package com.aebiz.b2b2c.order.ordermainlog.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogModel;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogQueryModel;

@Repository
public class OrderMainLogH4Impl extends
		BaseH4Impl<OrderMainLogModel, OrderMainLogQueryModel> implements
		OrderMainLogDAO {

	/**
	 * 通过订单号获得订单日志列表 订单的日志记录 --客服增加的订单记录
	 * 
	 * 按照日志时间倒序排序
	 * 
	 * @param uuid
	 * @return
	 */
	public List<OrderMainLogModel> getOrderMainLogModelListByOrderId(
			String orderUuid) {

		StringBuffer sb = new StringBuffer(
				"from OrderMainLogModel omlm where 1=1");
		sb.append(" and omlm.orderMainUuid=:orderMainUuid");
		sb.append(" and omlm.operateType =:operateType");
		sb.append(" order by omlm.orderOpeTime desc");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderUuid);
		q.setString("operateType", "13");

		return q.list();
	}
	
	
	/**
	 * 通过订单号获得订单日志列表  订单的操作记录
	 * 
	 * 按照日志时间倒序排序 
	 * 
	 * @param uuid
	 * @return
	 */
	public List<OrderMainLogModel> getOrderMainLogModelListByOrderId(
			String orderUuid,String operateType) {

		StringBuffer sb = new StringBuffer(
				"from OrderMainLogModel omlm where 1=1");
		sb.append(" and omlm.orderMainUuid=:orderMainUuid");
		sb.append(" and omlm.operateType !=:operateType");
		sb.append(" order by omlm.orderOpeTime desc");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderUuid);
		q.setString("operateType", operateType);
		return q.list();
	}

	/**
	 * 获取当前订单是否已被客服抢单处理权
	 * 
	 * @param orderMainUuid
	 * @param ugencyState
	 * @return
	 */
	@Override
	public List<OrderMainLogModel> getOrderMainLogs(String orderMainUuid,
			String ugencyState) {
		StringBuffer sb = new StringBuffer(
				"from OrderMainLogModel omlm where 1=1");
		sb.append(" and omlm.orderMainUuid=:orderMainUuid");
		sb.append(" and omlm.ugencyState=:ugencyState");
		sb.append(" order by omlm.orderOpeTime desc");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderMainUuid);
		q.setString("ugencyState", ugencyState);
		return q.list();
	}
	
	/**
	 * 获得订单日志信息
	 * 
	 * @param orderMainUuid
	 * @param operateType
	 * @return
	 */
	@Override
	public OrderMainLogModel getLogModel(String orderMainUuid,
			String operateType) {
		StringBuffer sb = new StringBuffer(
				"from OrderMainLogModel omlm where 1=1");
		sb.append(" and omlm.orderMainUuid=:orderMainUuid");
		sb.append(" and omlm.operateType=:operateType");
		sb.append(" order by omlm.orderOpeTime desc");
		
		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderMainUuid);
		q.setString("operateType", operateType);
		
		List list = q.list();
		if(list !=null && list.size()>0){
			return (OrderMainLogModel) list.get(0);
		}
		return null;
	}
}

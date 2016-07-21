package com.aebiz.b2b2c.cms.ordermainappraise.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseModel;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseQueryModel;

@Repository
public class OrderMainAppraiseH4Impl extends
		BaseH4Impl<OrderMainAppraiseModel, OrderMainAppraiseQueryModel>
		implements OrderMainAppraiseDAO {

	/**
	 * 
	 * 获取商家的平均评分
	 * 
	 * @param storeUuid
	 * @return
	 */
	public double getAverageScore(String storeUuid, String type) {
		StringBuffer hql = new StringBuffer("select ");
		if (OrderMainAppraiseModel.SCORE_STORE.equals(type)) {
			hql.append(" avg(o.storeScore) ");
		} else if (OrderMainAppraiseModel.SCORE_SPEED.equals(type)) {
			hql.append(" avg(o.speedScore) ");
		} else if (OrderMainAppraiseModel.SCORE_DESC.equals(type)) {
			hql.append(" avg(o.descScore) ");
		} else if (OrderMainAppraiseModel.SCORE_SERVICE.equals(type)) {
			hql.append(" avg(o.serviceScore) ");
		}
		hql.append(" from OrderMainAppraiseModel o where o.storeUuid=:storeUuid ");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("storeUuid", storeUuid);

		Object obj = q.uniqueResult();
		if (obj != null) {
			return Double.parseDouble(obj.toString());
		}
		return 0;
	}

	/**
	 * 
	 * 根据订单id获取订单评价
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAppraiseModel getOrderMainAppraiseByOrderUuid(
			String orderUuid) {
		StringBuffer hql = new StringBuffer(
				"select o from OrderMainAppraiseModel o where o.orderMainUuid=:orderMainUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("orderMainUuid", orderUuid);

		Object obj = q.uniqueResult();
		if (obj != null) {
			return (OrderMainAppraiseModel) obj;
		}
		return null;
	}
}

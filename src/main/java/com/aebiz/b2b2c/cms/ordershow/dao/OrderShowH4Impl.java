package com.aebiz.b2b2c.cms.ordershow.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowModel;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowQueryModel;

@Repository
public class OrderShowH4Impl extends
		BaseH4Impl<OrderShowModel, OrderShowQueryModel> implements OrderShowDAO {

	/**
	 * 通过订单明细的UUID查询对应的晒单记录
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public OrderShowModel getOrderShowModelByDetailUuid(String orderDetailUuid) {

		StringBuffer sb = new StringBuffer("from OrderShowModel osm where 1=1");
		sb.append(" and osm.orderDetailUuid=:orderDetailUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderDetailUuid", orderDetailUuid);

		return (OrderShowModel) q.uniqueResult();
	}

	/**
	 * 通过商品的UUID查询对应的晒单记录和晒单的图片
	 * 
	 * @param productUuid
	 * @return
	 */
	public List<OrderShowModel> getOrderShowModelAndPicsByProductUuid(
			String productUuid, int start, int showPage) {
		StringBuffer sb = new StringBuffer("from OrderShowModel osm where 1=1");
		sb.append(" and osm.productUuid=:productUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("productUuid", productUuid);
		q.setFirstResult(start);
		q.setMaxResults(showPage);

		return q.list();
	}

	/**
	 * 通过商品的UUID查询对应的晒单数量
	 * 
	 * @param productUuid
	 * @return
	 */
	public int getOrderShowModelCountAndPicsByProductUuid(String productUuid) {
		StringBuffer sb = new StringBuffer(
				" select count(osm) from OrderShowModel osm where 1=1");
		sb.append(" and osm.productUuid=:productUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("productUuid", productUuid);

		return Integer.parseInt(q.uniqueResult().toString());
	}
}

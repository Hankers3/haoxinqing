package com.aebiz.b2b2c.cms.ordershowpic.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicModel;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicQueryModel;

@Repository
public class OrderShowPicH4Impl extends
		BaseH4Impl<OrderShowPicModel, OrderShowPicQueryModel> implements
		OrderShowPicDAO {

	/**
	 * 通过晒单的编号获得晒单所有的图片
	 * 
	 * @param orderShowUuid
	 * @return
	 */
	public List<OrderShowPicModel> getOrderShowPicModelByShowUuid(
			String orderShowUuid) {

		StringBuffer sb = new StringBuffer(
				"from OrderShowPicModel osp where 1=1");
		sb.append(" and osp.orderShowUuid=:orderShowUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderShowUuid", orderShowUuid);

		return q.list();
	}
}

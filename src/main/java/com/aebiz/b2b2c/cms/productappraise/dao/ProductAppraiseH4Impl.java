package com.aebiz.b2b2c.cms.productappraise.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseModel;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseQueryModel;

@Repository
public class ProductAppraiseH4Impl extends
		BaseH4Impl<ProductAppraiseModel, ProductAppraiseQueryModel> implements
		ProductAppraiseDAO {

	/**
	 * 
	 * 根据订单明细uuid获取商品评价
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public ProductAppraiseModel getProductAppByOrderDetailUuid(
			String orderDetailUuid) {
		StringBuffer hql = new StringBuffer(
				"select o from ProductAppraiseModel o where o.orderDetailUuid=:orderDetailUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("orderDetailUuid", orderDetailUuid);

		Object obj = q.uniqueResult();
		if (obj != null) {
			return (ProductAppraiseModel) obj;
		}
		return null;
	}

	/**
	 * 
	 * 平台后台商品列表搜索
	 * 
	 */
	@Override
	public String getAppendHql(ProductAppraiseQueryModel qm) {
		String hql = "";
		if (qm.getProductIds() != null) {
			hql += " and o.productUuid in(:productUuid) ";
		}
		hql += super.getAppendHql(qm);
		return hql;
	}

	@Override
	public void setAppendHqlValue(ProductAppraiseQueryModel qm, Query q) {
		if (qm.getProductIds() != null) {
			q.setParameterList("productUuid", qm.getProductIds());
		}
	}

	/**
	 * 获取评价数量
	 * 
	 * @param productUuid
	 * @param type
	 *            全部--0;好评--1;中评--2;差评--3
	 * @return
	 */
	public int getAppCountByType(String productUuid, String type) {
		StringBuffer hql = new StringBuffer(
				"select count(o) from ProductAppraiseModel o ");
		hql.append(" where o.productUuid=:productUuid and o.state=2 ");
		if ("1".equals(type)) {
			hql.append(" and (o.appScore=4 or o.appScore=5) ");
		} else if ("2".equals(type)) {
			hql.append(" and o.appScore=3 ");
		} else if ("3".equals(type)) {
			hql.append(" and (o.appScore=1 or o.appScore=2) ");
		}

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("productUuid", productUuid);

		return Integer.parseInt(q.uniqueResult().toString());
	}

	/**
	 * 获取评价
	 * 
	 * @param productUuid
	 * @param type
	 *            全部--0;好评--1;中评--2;差评--3
	 * @return
	 */
	public List<ProductAppraiseModel> getAppByType(String productUuid,
			String type, int start, int showPage) {
		StringBuffer hql = new StringBuffer(
				"select o from ProductAppraiseModel o ");
		hql.append(" where o.productUuid=:productUuid and o.state=2 ");
		if ("1".equals(type)) {
			hql.append(" and (o.appScore=4 or o.appScore=5) ");
		} else if ("2".equals(type)) {
			hql.append(" and o.appScore=3 ");
		} else if ("3".equals(type)) {
			hql.append(" and (o.appScore=1 or o.appScore=2) ");
		}
		hql.append(" order by o.appTime desc");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("productUuid", productUuid);
		q.setFirstResult(start);
		q.setMaxResults(showPage);

		return q.list();
	}

	/**
	 * 获得商品的综合评分
	 * 
	 * @param productUuid
	 * @return
	 */
	public double getAverageScore(String productUuid) {
		StringBuffer hql = new StringBuffer(
				"select avg(o.appScore) from ProductAppraiseModel o where o.productUuid=:productUuid and o.state=2 ");
		
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("productUuid", productUuid);
		
		Object obj = q.uniqueResult();
		if (obj != null) {
			return Double.parseDouble(obj.toString());
		}
		return 0;
	}
}

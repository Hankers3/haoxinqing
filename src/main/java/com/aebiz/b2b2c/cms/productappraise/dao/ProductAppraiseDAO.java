package com.aebiz.b2b2c.cms.productappraise.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseModel;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseQueryModel;

public interface ProductAppraiseDAO extends
		BaseDAO<ProductAppraiseModel, ProductAppraiseQueryModel> {

	/**
	 * 
	 * 根据订单明细uuid获取商品评价
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public ProductAppraiseModel getProductAppByOrderDetailUuid(
			String orderDetailUuid);

	/**
	 * 获取评价数量
	 * 
	 * @param productUuid
	 * @param type
	 *            全部--0;好评--1;中评--2;差评--3
	 * @return
	 */
	public int getAppCountByType(String productUuid, String type);

	/**
	 * 获取评价
	 * 
	 * @param productUuid
	 * @param type
	 *            全部--0;好评--1;中评--2;差评--3
	 * @return
	 */
	public List<ProductAppraiseModel> getAppByType(String productUuid,
			String type, int start, int showPage);
	
	/**
	 * 获得商品的综合评分
	 * 
	 * @param productUuid
	 * @return
	 */
	public double getAverageScore(String productUuid);
}
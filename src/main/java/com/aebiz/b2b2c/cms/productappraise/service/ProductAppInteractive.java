package com.aebiz.b2b2c.cms.productappraise.service;

import java.util.List;

import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseModel;

/**
 * 给商品详情页提供数据对外接口
 * 
 * @author cj
 * 
 */
public interface ProductAppInteractive {
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

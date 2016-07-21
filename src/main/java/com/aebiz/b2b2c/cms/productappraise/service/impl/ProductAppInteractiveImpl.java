package com.aebiz.b2b2c.cms.productappraise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.cms.productappraise.dao.ProductAppraiseDAO;
import com.aebiz.b2b2c.cms.productappraise.service.ProductAppInteractive;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseModel;

@Service
@Transactional
public class ProductAppInteractiveImpl implements ProductAppInteractive {
	private ProductAppraiseDAO myDao = null;

	@Autowired
	public void setMyDao(ProductAppraiseDAO dao) {
		this.myDao = dao;
	}

	/**
	 * 获取商品综合评分
	 * 
	 * @param productUuid
	 * @param type
	 *            全部--0;好评--1;中评--2;差评--3
	 * @return
	 */
	public int getAppCountByType(String productUuid, String type) {
		return this.myDao.getAppCountByType(productUuid, type);
	}

	/**
	 * 获取评价
	 * 
	 * @param productUuid
	 * @param type
	 *            全部--0;好评--1;中评--2;差评--3
	 * @return
	 */
	public List<ProductAppraiseModel> getAppByType(String productUuid,String type, int start, int showPage){
		return this.myDao.getAppByType(productUuid, type, start,  showPage);
	}
	
	/**
	 * 获得商品的综合评分
	 * 
	 * @param productUuid
	 * @return
	 */
	public double getAverageScore(String productUuid){
		return this.myDao.getAverageScore(productUuid);
	}
}

package com.aebiz.b2b2c.product.productmodifylog.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.product.productmodifylog.vo.ProductModifyLogModel;
import com.aebiz.b2b2c.product.productmodifylog.vo.ProductModifyLogQueryModel;

public interface ProductModifyLogService extends BaseService<ProductModifyLogModel,ProductModifyLogQueryModel>{
	/**
	 * 记录商品操作日志
	 * 
	 * @param oldObj 旧的数据
	 * @param newObj 新的数据
	 * @param oldPrice	旧价格
	 * @param newPrice	新价格
	 */
	public void createLog(Object oldObj,Object newObj,double oldPrice,double newPrice);
}

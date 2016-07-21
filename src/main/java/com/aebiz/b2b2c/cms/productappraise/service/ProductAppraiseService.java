package com.aebiz.b2b2c.cms.productappraise.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseModel;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseQueryModel;
import com.aebiz.b2b2c.cms.usercenter.web.productappraise.vo.ProductAppraiseWebModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;

/**
 * 
 * 订单评价
 * 
 * @author cj
 *
 */

public interface ProductAppraiseService extends
		BaseService<ProductAppraiseModel, ProductAppraiseQueryModel> {
	
	/**
	 * 
	 * 根据订单明细集合获取每条订单明细对应的标签集合<br/>
	 * 
	 * 然后把数据封装到ProductAppraiseWebModel中发送到页面
	 * 
	 * @param ods
	 * @return
	 */
	public List<ProductAppraiseWebModel> getOrderDetailAndTags(List<OrderDetailModel> ods);
	
	/**
	 * 
	 * 获取会员中心的评价列表数据
	 * 
	 * @return
	 */
	public List<ProductAppraiseWebModel> getProductAppList(ProductAppraiseQueryModel qm,int start,int page);
	
	/**
	 * 
	 * 保存平台审核商品评价的信息
	 * 
	 * @param m
	 */
	public void saveReview(ProductAppraiseModel m);
	
}

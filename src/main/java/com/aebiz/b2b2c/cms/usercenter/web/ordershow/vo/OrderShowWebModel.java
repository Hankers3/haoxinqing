package com.aebiz.b2b2c.cms.usercenter.web.ordershow.vo;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;

public class OrderShowWebModel extends BaseWebModel {
	/* 订单明细 */
	private OrderDetailModel odm = new OrderDetailModel();

	/* 商品图片 */
	private String productPicUrl = "";

	public OrderDetailModel getOdm() {
		return odm;
	}

	public void setOdm(OrderDetailModel odm) {
		this.odm = odm;
	}

	public String getProductPicUrl() {
		return productPicUrl;
	}

	public void setProductPicUrl(String productPicUrl) {
		this.productPicUrl = productPicUrl;
	}

}

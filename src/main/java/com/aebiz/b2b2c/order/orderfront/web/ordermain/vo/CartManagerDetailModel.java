package com.aebiz.b2b2c.order.orderfront.web.ordermain.vo;



/**
 * 前台购物车表
 * 
 * @author zdx
 * 
 */
public class CartManagerDetailModel {

	/*下单时所使用的积分*/
	private double useIntergral = 0;
	
	/*下单时所使用的优惠券*/
	private String couponNo = "";
	
	private CartManagerModel cartManagerModel ;
	
	
	public double getUseIntergral() {
		return useIntergral;
	}

	public void setUseIntergral(double useIntergral) {
		this.useIntergral = useIntergral;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public CartManagerModel getCartManagerModel() {
		return cartManagerModel;
	}

	public void setCartManagerModel(CartManagerModel cartManagerModel) {
		this.cartManagerModel = cartManagerModel;
	}

}

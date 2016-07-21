package com.aebiz.b2b2c.order.interactive.vo;

import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressModel;

/**
 * 订单接口的封装对象
 * 
 * 1.提供给发货系统用，待发货订单列表展示，需要调用（订单主表信息以及收货地址信息） <br />
 * 
 * @author duandeyi
 * 
 */
public class OrderInteractiveModel {

	/* 订单主表信息 */
	private OrderMainModel orderMain;

	/* 订单收货地址信息 */
	private OrderMainAddressModel orderAddress;

	public OrderMainModel getOrderMain() {
		return orderMain;
	}

	public void setOrderMain(OrderMainModel orderMain) {
		this.orderMain = orderMain;
	}

	public OrderMainAddressModel getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderMainAddressModel orderAddress) {
		this.orderAddress = orderAddress;
	}
}

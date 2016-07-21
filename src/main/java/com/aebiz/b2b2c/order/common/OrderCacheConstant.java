package com.aebiz.b2b2c.order.common;

/**
 * 订单系统缓存相关信息
 * @author zdx
 *
 */
public class OrderCacheConstant {

	/* 订单系统，名称 */
	public static final String ORDER_SYS_NAME = "order";

	/* 订单系统，缓存客户端名称 */
	public static final String ORDER_CLIENT_NAME = "memCachedClient";
	
	/* 订单的缓存key */
	public static final String ORDER_ORDERMAIN_KEY = ORDER_SYS_NAME
			+ "/ordermain";
}

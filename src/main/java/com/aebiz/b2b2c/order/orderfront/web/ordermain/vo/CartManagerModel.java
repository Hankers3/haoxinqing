package com.aebiz.b2b2c.order.orderfront.web.ordermain.vo;

import java.io.Serializable;


/**
 * 前台购物车表
 * 
 * @author zdx
 * 
 */
public class CartManagerModel  implements Serializable{

	public static String COOKIE_CART_NAME = "shoppingcart";

	/* 商品编号 */
	private String productUuid = "";
	
	/* 商品名称 */
	private String productName = "";
	
	/* 购买数量 */
	private String buyNum = "";

	/* 商品基础价格 */
	private String basePrice = "";
	
	/* 商品单位 */
	private String chargetype = "";
	
	/* 地图X坐标 */
	private String xcoordinate = "";
	
	/* 地图Y坐标 */
	private String ycoordinate = "";
	
	/* 服务地址 */
	private String serviceAddress = "";
	
	/* 订单总金额 */
	private double totalMoney = 0;
	
	/* 城市id */
	private String cityId = "";
	
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getXcoordinate() {
		return xcoordinate;
	}

	public void setXcoordinate(String xcoordinate) {
		this.xcoordinate = xcoordinate;
	}

	public String getYcoordinate() {
		return ycoordinate;
	}

	public void setYcoordinate(String ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public String getChargetype() {
		return chargetype;
	}

	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}
	
	
}

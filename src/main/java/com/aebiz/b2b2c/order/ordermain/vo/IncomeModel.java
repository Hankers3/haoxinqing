package com.aebiz.b2b2c.order.ordermain.vo;

import javax.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;

/**
 * 后台财务系统-收入管理帮助model
 * @author aebiz
 *
 */
@Component
public class IncomeModel {
	
	@Transient
	private static OrderMainService orderMainService;
	
	@Autowired
	public void setOrderMainService(OrderMainService om) {
		this.orderMainService = om;
	}

	/*收入时间*/
	private String date = "" ;
	
	/*订单总金额*/
	private double orderTotalMoney = 0 ;
	
	/*积分使用金额*/
	private double integralTotalMoney = 0 ;
	
	/*优惠券金额*/
	private double couponTotalMoney = 0 ;
	
	/*其他折扣金额*/
	private double otherTotalMoney = 0 ;
	
	/*实际收入金额*/
	private double paidTotalMoney = 0 ;
	
	/*支付宝收入*/
	private double alipayTotalMoney = 0 ;
	
	/*微信收入*/
	private double wxTotalMoney = 0 ;
	
	/*平台分账*/
	private double sysRoutingMoney = 0 ;
	
	
	public double getSysRoutingMoney() {
		return sysRoutingMoney;
	}

	public void setSysRoutingMoney(double sysRoutingMoney) {
		this.sysRoutingMoney = sysRoutingMoney;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getOrderTotalMoney() {
		return orderTotalMoney;
	}

	public void setOrderTotalMoney(double orderTotalMoney) {
		this.orderTotalMoney = orderTotalMoney;
	}

	public double getIntegralTotalMoney() {
		return integralTotalMoney;
	}

	public void setIntegralTotalMoney(double integralTotalMoney) {
		this.integralTotalMoney = integralTotalMoney;
	}

	public double getCouponTotalMoney() {
		return couponTotalMoney;
	}

	public void setCouponTotalMoney(double couponTotalMoney) {
		this.couponTotalMoney = couponTotalMoney;
	}

	public double getOtherTotalMoney() {
		return otherTotalMoney;
	}

	public void setOtherTotalMoney(double otherTotalMoney) {
		this.otherTotalMoney = otherTotalMoney;
	}

	public double getPaidTotalMoney() {
		return paidTotalMoney;
	}

	public void setPaidTotalMoney(double paidTotalMoney) {
		this.paidTotalMoney = paidTotalMoney;
	}

	public double getAlipayTotalMoney() {
		return orderMainService.getPayMoneyByReceiveTime(this.date, "1");
	}

	public void setAlipayTotalMoney(double alipayTotalMoney) {
		this.alipayTotalMoney = alipayTotalMoney;
	}

	public double getWxTotalMoney() {
		return orderMainService.getPayMoneyByReceiveTime(this.date, "2");
	}

	public void setWxTotalMoney(double wxTotalMoney) {
		this.wxTotalMoney = wxTotalMoney;
	}

}

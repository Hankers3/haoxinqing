package com.aebiz.b2b2c.order.orderdetail.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * update by 20151227 
 * 存放私人套餐信息  下次购买电话咨询时 
 * 如果该表信息中有满足电话咨询的信息 则在此处减去一次 直到电话预约的次数为0
 * @author xueli
 *
 */
@Entity
@Table(name = "order_detail")
@Component
public class OrderDetailModel extends BaseModel {
	
	/*患者编号*/
	private String customerUuid;
	
	/*医生编号*/
	private String doctorUuid;

	/* 订单编号 */
	private String orderMainUuid;
	
	/* 套餐编号*/
	private String productUuid;
	
	/* 套餐名称 */
	private String productName;

	/* 原价格 */
	private double basePrice;
	
	/* 应付金额 */
	private double money;
	
	/* 状态 */
	private String state;
	
	/* 最终购买价格 */
	private double finalPrice ;
	
	/* 预约加号次数 */
	private int plusTimes;
	
	/* 电话咨询次数 */
	private int phoneTimes;
	
	/* 预约时长 */
	private String duration;
	
	/*套餐到期时间*/
	private String dueTime;
	
	public String getDueTime() {
		return dueTime;
	}

	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String getDoctorUuid() {
		return doctorUuid;
	}

	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}

	public int getPlusTimes() {
		return plusTimes;
	}

	public void setPlusTimes(int plusTimes) {
		this.plusTimes = plusTimes;
	}

	public int getPhoneTimes() {
		return phoneTimes;
	}

	public void setPhoneTimes(int phoneTimes) {
		this.phoneTimes = phoneTimes;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setOrderMainUuid(String obj) {
		this.orderMainUuid = obj;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getOrderMainUuid() {
		return this.orderMainUuid;
	}

	public void setProductUuid(String obj) {
		this.productUuid = obj;
	}

	public String getProductUuid() {
		return this.productUuid;
	}

	public void setProductName(String obj) {
		this.productName = obj;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setBasePrice(double obj) {
		this.basePrice = obj;
	}

	public double getBasePrice() {
		return this.basePrice;
	}

	public void setMoney(double obj) {
		this.money = obj;
	}

	public double getMoney() {
		return this.money;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[orderMainUuid=" + this.getOrderMainUuid()
				+ ",productUuid=" + this.getProductUuid() + ",productName="
				+ this.getProductName() +  ",basePrice="
				+ this.getBasePrice() + 
				",money=" + this.getMoney() + ",state=" + this.getState()
				+ ",]";
	}
}

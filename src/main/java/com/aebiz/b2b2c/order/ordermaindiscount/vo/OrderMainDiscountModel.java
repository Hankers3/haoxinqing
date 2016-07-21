package com.aebiz.b2b2c.order.ordermaindiscount.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 订单优惠信息 
 * @author xueli
 *
 */
@Entity
@Table(name = "order_main_discount")
public class OrderMainDiscountModel extends BaseModel {

	/* 订单编号 */
	private String orderMainUuid;

	/* 优惠类型 */
	private String freeType;

	/* 优惠金额 */
	private double freeMoney;

	/* 促销活动编号 */
	private String freeEntityUuid;

	/* 优惠描述 */
	private String description;

	public void setFreeType(String obj) {
		this.freeType = obj;
	}

	public String getFreeType() {
		return this.freeType;
	}

	public void setFreeMoney(double obj) {
		this.freeMoney = obj;
	}

	public double getFreeMoney() {
		return this.freeMoney;
	}

	public void setFreeEntityUuid(String obj) {
		this.freeEntityUuid = obj;
	}

	public String getFreeEntityUuid() {
		return this.freeEntityUuid;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[freeType=" + this.getFreeType() + ",freeMoney="
				+ this.getFreeMoney() + ",freeEntityUuid="
				+ this.getFreeEntityUuid() + ",description="
				+ this.getDescription() + ",]";
	}
}
